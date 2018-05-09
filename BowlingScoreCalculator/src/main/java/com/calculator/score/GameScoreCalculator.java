package com.calculator.score;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameScoreCalculator {
	// Maybe have a game_type member variable to pick the matching rules engine??
	// Otherwise this is hard-coded for a 10 pin game score

	private int gameScore;
	int[] rollSequence;
	List<TenPinFrame> frameSequence = new ArrayList<TenPinFrame>();

	public GameScoreCalculator(int[] rollSequence) {
		this.rollSequence = rollSequence;
		createFrameSequence();
	}

	private void createFrameSequence() {
		Stack<Integer> rollStack = new Stack<>();// MAXINT size by default so it'll do
		// load up a stack with rollSequence in reverse to facilitate processing
		for (int i = rollSequence.length - 1; i >= 0; i--) {
			Integer roll = rollSequence[i];
			rollStack.push(roll);
		}

		for (int i = 0; i < TenPinRulesEngine.NUM_FRAMES; i++) {
			int roll = rollStack.peek();

			// special case: last frame will have 2 to 3 rolls - get all in frame
			if (i == TenPinRulesEngine.NUM_FRAMES - 1) {
				int leftOvers = rollStack.size();
				int[] rolls = new int[leftOvers];

				for (int j = 0; j < rolls.length; j++) {
					rolls[j] = rollStack.pop();
				}
				frameSequence.add(new TenPinFrame(i, rolls));

			} else if (roll == TenPinRulesEngine.STRIKE) {
				//just get one roll for the STRIKE
				frameSequence.add(new TenPinFrame(i, new int[] { rollStack.pop() }));
			} else {
				//Otherwise, SPARE or not, put 2 rolls in the frame
				int[] rolls = new int[TenPinRulesEngine.NUM_FRAME_ROLLS];

				for (int j = 0; j < rolls.length; j++) {
					rolls[j] = rollStack.pop();
				}
				frameSequence.add(new TenPinFrame(i, rolls));
			}
		}
	}

	public void calculateGameScore() {

		int secondLastFrame = TenPinRulesEngine.NUM_FRAMES - 2;

		for (Frame frame : frameSequence) {
			int[] rolls = frame.getRolls();
			int frameSeqNum = frame.getSequenceNumber();

			boolean isStrike = rolls.length == 1;
			boolean isSpare = (rolls.length == 2 && frame.getPinScore() == TenPinRulesEngine.NUM_PINS);

			int numRollsForBonus = 0;
			int frameBonus = 0;

			if (isStrike) {
				numRollsForBonus = TenPinRulesEngine.STRIKE_BONUS_ROLLS;
			} else if (isSpare) {
				numRollsForBonus = TenPinRulesEngine.SPARE_BONUS_ROLLS;
			}
			// only process bonus for upto 2nd last frame
			// last frame will potentially have bonus throws
			// and pin score already contains bonus score
			if (frameSeqNum <= secondLastFrame) {
				frameBonus = processFrameForBonus(frameSeqNum + 1, numRollsForBonus);
				frame.setBonusScore(frameBonus);
			}
			gameScore += frame.getFinalScore();
		}
	}

	// recurse if required to calculate bonus
	private int processFrameForBonus(int frameSequenceNum, int numRollsToGet) {

		if (numRollsToGet == 0) {
			return 0;
		}

		Frame frame = frameSequence.get(frameSequenceNum);
		int[] rolls = frame.getRolls();
		int bonus = rolls[0];

		if (numRollsToGet == 1) {
			return bonus;
		} else {
			// we are here because we need two rolls to calculate bonus
			if (rolls.length == 1) {
				// STRIKE detected; recurse to get one roll from the next frame
				int nextFrameSeqNum = frameSequenceNum + 1;
				bonus += processFrameForBonus(nextFrameSeqNum, 1);
			} else {
				// get both rolls from this frame
				bonus += rolls[1];
			}
		}
		return bonus;
	}

	public int getGameScore() {
		return this.gameScore;
	}

	public List<TenPinFrame> getFrameSequence() {
		return frameSequence;
	}
}
