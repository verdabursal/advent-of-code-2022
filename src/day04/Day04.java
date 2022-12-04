package day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day04 {
	String filepath = "src/day04/data.txt";

	public int solvePuzzle1() {
		int count = 0;

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				String[] assns = currentLine.split(",");
				String[] firstAssn = assns[0].split("-");
				String[] secondAssn = assns[1].split("-");
				int firstLow = Integer.parseInt(firstAssn[0]);
				int firstHigh = Integer.parseInt(firstAssn[1]);
				int secondLow = Integer.parseInt(secondAssn[0]);
				int secondHigh = Integer.parseInt(secondAssn[1]);

				if ((firstLow <= secondLow && firstHigh >= secondHigh)
					|| (firstHigh <= secondHigh && firstLow >= secondLow)) {
					count++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return count;
	}

	public int solvePuzzle2() {
		int count = 0;

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				String[] assns = currentLine.split(",");
				String[] firstAssn = assns[0].split("-");
				String[] secondAssn = assns[1].split("-");
				int firstLow = Integer.parseInt(firstAssn[0]);
				int firstHigh = Integer.parseInt(firstAssn[1]);
				int secondLow = Integer.parseInt(secondAssn[0]);
				int secondHigh = Integer.parseInt(secondAssn[1]);

				if ((firstLow <= secondLow && firstHigh >= secondLow)
					|| (firstLow <= secondHigh && firstHigh >= secondHigh)
					|| (firstHigh <= secondHigh && firstLow >= secondHigh)
					|| (firstHigh <= secondLow && firstLow >= secondLow)
					|| (firstLow <= secondLow && firstHigh >= secondHigh)
					|| (firstHigh <= secondHigh && firstLow >= secondLow)) {
					count++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return count;
	}
}
