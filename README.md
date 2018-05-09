# TenPinBowlingScoreCalculator

#H3 1. Eclipse project:
---
BowlingScoreCalculator.zip

Instructions for it: instructions-eclipse.zip

Unit tests are TestNG tests and all libraries are included in the source zip

#H3 2. Runnable jar
---
BowlingScoreCalculator.jar

Download and run it as follows:

Use a string representing a "Valid" roll sequence like this: "10, 2, 0, 10, 7, 3, 2, 3, 4, 5, 1, 8, 9, 1, 10, 2, 4"

The program trims the extra spaces

Then invoke from commandline like this:

c:\temp>java -jar BowlingScoreCalculator.jar "10, 2, 0, 10, 7, 3, 2, 3, 4, 5, 1, 8, 9, 1, 10, 2, 4"

Sample program output with this roll sequence:

Calculated game score:111

Your frame by frame break down - [[pins downed]pin-score:bonus]

[[[10]10:2], [[2][0]2:0], [[10]10:10], [[7][3]10:2], [[2][3]5:0], [[4][5]9:0], [[1][8]9:0], [[9][1]10:10], [[10]10:6], [[2][4]6:0]]


#H3 3. For online viewing and general use
---
Source files are here:
BowlingScoreCalculator/src/main/java/com/calculator/score

Runnable Application is here:
BowlingScoreCalculator/src/main/java/com/scorecalculator/app/

TestNG tests are here:
BowlingScoreCalculator/src/test/java/com/calculator/score/
