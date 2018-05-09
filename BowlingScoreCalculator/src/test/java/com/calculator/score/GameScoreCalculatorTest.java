package com.calculator.score;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GameScoreCalculatorTest {
	GameScoreCalculator calc;

	@Test(dataProvider = "rollSequenceData")
	public void TestFrameCreation(int[] rollSequence, int expectedScore) {

		calc = new GameScoreCalculator(rollSequence);
		List<TenPinFrame> frames = calc.getFrameSequence();

		calc.calculateGameScore();
		int actualScore = calc.getGameScore();
		System.out.println(frames.toString());

		Assert.assertEquals(actualScore, expectedScore);
	}

	@DataProvider(name = "rollSequenceData")
	public Object[][] rollSequenceData() {
		Object[][] rollSequence = new Object[6][2];
		
		//Perfect game
		rollSequence[0][0] = new int[] { 10,10,10,10,10,10,10,10,10,10,10,10 }; 
		rollSequence[0][1] = 300;
		
		rollSequence[1][0] = new int[] { 10, 2, 0, 10, 7, 3, 2, 3, 4, 5, 1, 8, 9, 1, 10, 2, 4 };
		rollSequence[1][1] = 111;

		rollSequence[2][0] = new int[] { 8, 2, 0, 0, 10, 10, 7, 3, 2, 3, 4, 5, 1, 8, 9, 1, 10, 2 };
		rollSequence[2][1] = 124;

		//Strikes followed by 2 zeroes
		rollSequence[3][0] = new int[] { 10,0,0,10,0,0,10,0,0,10,0,0,10,0,0 };
		rollSequence[3][1] = 50;
		
		rollSequence[4][0] = new int[] { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
		rollSequence[4][1] = 0;
		
		//All spares followed by 0 pins downed
		rollSequence[5][0] = new int[] { 1,9,0,9,3,7,0,9,8,2,0,9,6,4,0,9,5,5,0,10,0 };
		rollSequence[5][1] = 96;

		return rollSequence;
	}
}
