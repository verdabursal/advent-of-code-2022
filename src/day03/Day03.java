package day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day03 {
	String filepath = "src/day03/data.txt";

	String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public int solvePuzzle1() {
		int prioritiesSum = 0;

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				Character shared = null;
				String firstHalf = currentLine.substring(0, currentLine.length() / 2);
				String secondHalf = currentLine.substring(currentLine.length() / 2);
				char[] firstHalfChars = firstHalf.toCharArray();

				for (char c : firstHalfChars) {
					if (secondHalf.indexOf(c) > -1) {
						shared = c;
						break;
					}
				}

				if (shared != null) {
					prioritiesSum += alphabet.indexOf(shared) + 1;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prioritiesSum;
	}

	public int solvePuzzle2() {
		int prioritiesSum = 0;

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			int idx = 0;
			String[] currentTrio = new String[3];

			while ((currentLine = br.readLine()) != null) {
				currentTrio[idx] = currentLine;

				if (idx == 2) {
					Character shared = null;
					char[] firstOfTrioChars = currentTrio[0].toCharArray();

					for (char c : firstOfTrioChars) {
						if (currentTrio[1].indexOf(c) > -1 && currentTrio[2].indexOf(c) > -1) {
							shared = c;
							break;
						}
					}

					if (shared != null) {
						prioritiesSum += alphabet.indexOf(shared) + 1;
					}

					idx = 0;
				} else {
					idx++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prioritiesSum;
	}
}
