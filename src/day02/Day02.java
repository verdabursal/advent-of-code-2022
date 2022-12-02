package day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day02 {
	String filepath = "src/day02/day02data.txt";
	public int solvePuzzle1() {
		int totalScore = 0;

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				char opponent = currentLine.charAt(0);
				char self = currentLine.charAt(2);
				int score = 0;
				if (self == 'X') {
					score += 1;
					if (opponent == 'C') {
						score += 6;
					} else if (opponent == 'A') {
						score += 3;
					}
				} else if (self == 'Y') {
					score += 2;
					if (opponent == 'A') {
						score += 6;
					} else if (opponent == 'B') {
						score += 3;
					}
				} else if (self == 'Z') {
					score += 3;
					if (opponent == 'B') {
						score += 6;
					} else if (opponent == 'C') {
						score += 3;
					}
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
				char opponent = currentLine.charAt(0);
				char result = currentLine.charAt(2);
				int score = 0;

				if (opponent == 'A') {
					if (result == 'X') {
						// lose -- self=scissors
						score += 3;
					} else if (result == 'Y') {
						// draw -- self=rock
						score += 3 + 1;
					} else {
						// win -- self=paper
						score += 6 + 2;
					}
				} else if (opponent == 'B') {
					if (result == 'X') {
						// lose -- self=rock
						score += 1;
					} else if (result == 'Y') {
						// draw -- self=paper
						score += 3 + 2;
					} else {
						// win -- self=scissors
						score += 6 + 3;
					}
				} else if (opponent == 'C') {
					if (result == 'X') {
						// lose -- self=paper
						score += 2;
					} else if (result == 'Y') {
						// draw -- self=scissors
						score += 3 + 3;
					} else {
						// win -- self=rock
						score += 6 + 1;
					}
				}

				totalScore += score;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return totalScore;
	}
}
