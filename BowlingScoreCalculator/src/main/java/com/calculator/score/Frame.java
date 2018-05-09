package com.calculator.score;

public abstract class Frame {
	private int sequenceNumber;
	protected int[] rolls;
	protected int pinScore;
	protected int bonusScore;

	public abstract int getPinScore();
	public abstract int[] getRolls();
	public abstract void setBonusScore(int bonusScore);
	public abstract int getFinalScore();
	
	public Frame(int sequenceNumber) {
		this.sequenceNumber =sequenceNumber;
	}
	
	public int getSequenceNumber() {
		return this.sequenceNumber;
	}
}
