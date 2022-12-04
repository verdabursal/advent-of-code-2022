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
				if ((Integer.parseInt(firstAssn[0]) <= Integer.parseInt(secondAssn[0])
					&& Integer.parseInt(firstAssn[1]) >= Integer.parseInt(secondAssn[1]))
					||
					(Integer.parseInt(firstAssn[1]) <= Integer.parseInt(secondAssn[1])
						&& Integer.parseInt(firstAssn[0]) >= Integer.parseInt(secondAssn[0]))
				) {
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
				if ((Integer.parseInt(firstAssn[0]) <= Integer.parseInt(secondAssn[0])
					&& Integer.parseInt(firstAssn[1]) >= Integer.parseInt(secondAssn[0]))
					||
					(Integer.parseInt(firstAssn[0]) <= Integer.parseInt(secondAssn[1])
						&& Integer.parseInt(firstAssn[1]) >= Integer.parseInt(secondAssn[1]))
					||
					(Integer.parseInt(firstAssn[1]) <= Integer.parseInt(secondAssn[1])
						&& Integer.parseInt(firstAssn[0]) >= Integer.parseInt(secondAssn[1]))
					||
					(Integer.parseInt(firstAssn[1]) <= Integer.parseInt(secondAssn[0])
						&& Integer.parseInt(firstAssn[0]) >= Integer.parseInt(secondAssn[0]))
					||
					(Integer.parseInt(firstAssn[0]) <= Integer.parseInt(secondAssn[0])
						&& Integer.parseInt(firstAssn[1]) >= Integer.parseInt(secondAssn[1]))
					||
					(Integer.parseInt(firstAssn[1]) <= Integer.parseInt(secondAssn[1])
						&& Integer.parseInt(firstAssn[0]) >= Integer.parseInt(secondAssn[0]))
				) {
					count++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return count;
	}
}
