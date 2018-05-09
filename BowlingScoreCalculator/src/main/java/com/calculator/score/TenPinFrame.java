package com.calculator.score;

public class TenPinFrame extends Frame {

	public TenPinFrame(int sequenceNumber, int[] rolls) {
		super(sequenceNumber);
		
		this.rolls = rolls;
		
		for(int i=0; i<rolls.length; i++) {
			pinScore+=rolls[i];
		}
	}
	
	public int[] getRolls() {
		return this.rolls;
	}
	
	public void setBonusScore(int bonusScore) {
		this.bonusScore = bonusScore;
	}

	public int getFinalScore() {
		return pinScore + bonusScore;
	}

	public String toString() {
		String frame = "[";
		for(int i =0; i < rolls.length ;i++) {
			frame+="[" + rolls[i] + "]";
		}
		frame += pinScore + ":" + bonusScore + "]";
		return frame;
	}

	public int getPinScore() {
		return pinScore;
	}
}