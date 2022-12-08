package day08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day08 {
	String filepath = "src/day08/data.txt";


	public int solvePuzzle1() {
		List<List<Integer>> forest = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				List<Integer> row = new ArrayList<>();
				for (int i = 0; i < currentLine.length(); i++) {
					row.add(Integer.parseInt(currentLine.substring(i, i + 1)));
				}
				forest.add(row);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		int rows = forest.size();
		int cols = forest.get(0).size();
		boolean[][] visibilities = new boolean[rows][cols];

		// from the north facing southward
		for (int i = 0; i < rows; i++) {
			int lastHighest = 0;
			for (int j = 0; j < cols; j++) {
				int currentTree = forest.get(i).get(j);
				if (j == 0 || currentTree > lastHighest) {
					visibilities[i][j] = true;
					lastHighest = currentTree;
				}
			}
		}

		// from the east facing westward
		for (int i = 0; i < cols; i++) {
			int lastHighest = 0;
			for (int j = rows - 1; j >= 0; j--) {
				int currentTree = forest.get(j).get(i);
				if (j == rows - 1 || currentTree > lastHighest) {
					visibilities[j][i] = true;
					lastHighest = currentTree;
				}
			}
		}

		// from the south facing northward
		for (int i = 0; i < rows; i++) {
			int lastHighest = 0;
			for (int j = cols - 1; j >= 0; j--) {
				int currentTree = forest.get(i).get(j);
				if (j == cols - 1 || currentTree > lastHighest) {
					visibilities[i][j] = true;
					lastHighest = currentTree;
				}
			}
		}

		// from the west facing eastward
		for (int i = 0; i < cols; i++) {
			int lastHighest = 0;
			for (int j = 0; j < rows; j++) {
				int currentTree = forest.get(j).get(i);
				if (j == 0 || currentTree > lastHighest) {
					visibilities[j][i] = true;
					lastHighest = currentTree;
				}
			}
		}

		int countVisible = 0;
		for (boolean[] bArr : visibilities) {
			for (boolean b : bArr) {
				if (b) countVisible++;
			}
		}

		return countVisible;
	}

	public int solvePuzzle2() {
		List<List<Integer>> forest = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				List<Integer> row = new ArrayList<>();
				for (int i = 0; i < currentLine.length(); i++) {
					row.add(Integer.parseInt(currentLine.substring(i, i + 1)));
				}
				forest.add(row);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		int rows = forest.size();
		int cols = forest.get(0).size();
		int highestScenicScore = 0;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int currentTree = forest.get(i).get(j);

				// look up -- keep j the same, decrease i
				int upScore = 0;
				for (int k = i - 1; k >= 0; k--) {
					upScore++;
					if (forest.get(k).get(j) >= currentTree) break;
				}
				// look right -- keep i the same, increase j
				int rightScore = 0;
				for (int k = j + 1; k < cols; k++) {
					rightScore++;
					if (forest.get(i).get(k) >= currentTree) break;
				}
				// look down -- keep j the same, increase i
				int downScore = 0;
				for (int k = i + 1; k < rows; k++) {
					downScore++;
					if (forest.get(k).get(j) >= currentTree) break;
				}
				// look left -- keep i the same, decrease j
				int leftScore = 0;
				for (int k = j - 1; k >= 0; k--) {
					leftScore++;
					if (forest.get(i).get(k) >= currentTree) break;
				}
				// multiply it all
				int scenicScore = upScore * rightScore * downScore * leftScore;
				// compare to highestScenicScore
				if (scenicScore >= highestScenicScore)  {
					highestScenicScore = scenicScore;
				}
			}
		}

		return highestScenicScore;
	}
}
