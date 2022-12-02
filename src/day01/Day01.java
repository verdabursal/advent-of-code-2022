package day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01 {
	String filepath = "src/day01/data.txt";

	public int solvePuzzle1() {
		List<Integer> calorieCounts = findCalorieCounts();
		int maxCalories = 0;
		for (int c : calorieCounts) {
			if (c > maxCalories) {
				maxCalories = c;
			}
		}

		return maxCalories;
	}

	public int solvePuzzle2() {
		List<Integer> calorieCounts = findCalorieCounts();
		Collections.sort(calorieCounts);
		int n = calorieCounts.size();

		return calorieCounts.get(n - 1) + calorieCounts.get(n - 2) + calorieCounts.get(n - 3);
	}

	private List<Integer> findCalorieCounts() {
		List<Integer> calorieCounts = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			int currentCalorieCount = 0;
			while ((currentLine = br.readLine()) != null) {
				if (!currentLine.equals("")) {
					currentCalorieCount += Integer.parseInt(currentLine);
				} else {
					calorieCounts.add(currentCalorieCount);
					currentCalorieCount = 0;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return calorieCounts;
	}
}
