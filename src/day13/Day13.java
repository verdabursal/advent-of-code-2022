package day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day13 {
	String filepath = "src/day13/data.txt";

	public int solvePuzzle1() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				//
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public int solvePuzzle2() {
		return 0;
	}
}
