package day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day02 {
	String filepath = "src/day02/data.txt";

	String abc = "ABC";
	String xyz = "XYZ";
	public int solvePuzzle1() {
		int totalScore = 0;

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				int opponent = abc.indexOf(currentLine.charAt(0));
				int self = xyz.indexOf(currentLine.charAt(2));
				int score = self + 1;

				if (opponent == self) { // draw
					score += 3;
				} else if ((opponent + 1) % 3 == self % 3) { // win
					score += 6;
				}

				totalScore += score;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return totalScore;
	}

	public int solvePuzzle2() {
		int totalScore = 0;

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				int opponent = abc.indexOf(currentLine.charAt(0));
				int result = xyz.indexOf(currentLine.charAt(2));
				int score = 0;

				if (result == 0) { // lose
					score += (opponent + 2) % 3 + 1;
				} else if (result == 1) { // draw
					score += (opponent - 1) % 3 + 5;
				} else { // win
					score += (opponent + 1) % 3 + 7;
				}

				totalScore += score;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return totalScore;
	}
}
