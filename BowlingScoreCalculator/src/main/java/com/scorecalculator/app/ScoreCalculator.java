package com.scorecalculator.app;

import com.calculator.score.GameScoreCalculator;

public class ScoreCalculator {

	public static void main(String[] args) {
		int[] validRollsSequence = null;
		if (args.length == 1) {
			String rollsSequenceIn = args[0].trim();
			String[] tokens = rollsSequenceIn.split(",");
			validRollsSequence = new int[tokens.length];
			int ind = 0;
			for (String token : tokens) {
				validRollsSequence[ind++] = Integer.parseInt(token.trim());
			}
		}
		ScoreCalculator scoreCalculator = new ScoreCalculator();
		scoreCalculator.RrunApp(validRollsSequence);
	}

	public void RrunApp(int[] validRollsSequence) {
		GameScoreCalculator calc;
		
		
		if (validRollsSequence != null) {
			calc = new GameScoreCalculator(validRollsSequence);
			calc.calculateGameScore();
			System.out.format("Calculated game score:%d", calc.getGameScore());
			System.out.println("\nYour frame by frame break down - [[pins downed]pin-score:bonus]");
			System.out.println(calc.getFrameSequence());
		} else {
			int[] testRollSequence = new int[] { 10, 2, 0, 10, 7, 3, 2, 3, 4, 5, 1, 8, 9, 1, 10, 2, 4 };
			calc = new GameScoreCalculator(testRollSequence);
			calc.calculateGameScore();
			int expectedGameScore = 111;
			System.out.println("Practice game");
			System.out.format("Expected game score:%d :: calculated game score:%d", expectedGameScore,
					calc.getGameScore());

			testRollSequence = new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
			calc = new GameScoreCalculator(testRollSequence);
			calc.calculateGameScore();
			expectedGameScore = 300;
			System.out.println("\nPerfect game");
			System.out.format("Expected game score:%d :: calculated game score:%d", expectedGameScore,
					calc.getGameScore());
		}
	}
}
