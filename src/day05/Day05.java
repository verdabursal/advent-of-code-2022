package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Day05 {
	String filepath = "src/day05/data.txt";
	int rows = 8;
	int cols = 9;

	public String solvePuzzle1() {
		StringBuilder finalMessage = new StringBuilder();
		char[][] startingState = new char[rows][cols];
		List<int[]> steps = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			int idx = 0;

			while ((currentLine = br.readLine()) != null) {
				if (idx < rows) { // add to the starting state
					char[] row = new char[cols];
					for (int i = 0; i < cols; i++) {
						if (currentLine.length() > i * 4 + 1) {
							char charAt = currentLine.charAt(i * 4 + 1);
							if (charAt != ' ') {
								row[i] = charAt;
							}
						}
					}
					startingState[idx] = row;
				} else if (idx > rows + 1) { // add to the steps
					String[] strings = currentLine.split(" ");
					int[] amtFromTo = new int[3];
					amtFromTo[0] = Integer.parseInt(strings[1]);
					amtFromTo[1] = Integer.parseInt(strings[3]);
					amtFromTo[2] = Integer.parseInt(strings[5]);
					steps.add(amtFromTo);
				}

				idx++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// translate starting state into stacks representing columns
		Stack<Character>[] columns = new Stack[cols];
		for (int i = 0; i < cols; i++) {
			columns[i] = new Stack<Character>();
		}

		for (int i = rows - 1; i >= 0; i--) {
			for (int j = 0; j < cols; j++) {
				if (startingState[i][j] != '\u0000') {
					columns[j].push(startingState[i][j]);
				}
			}
		}

		// go thru list of steps and perform them all
		for (int[] step : steps) {
			int amount = step[0];
			int from = step[1] - 1;
			int to = step[2] - 1;

			for (int i = 0; i < amount; i++) {
				columns[to].push(columns[from].pop());
			}
		}

		// assemble final message
		for (int i = 0; i < cols; i++) {
			finalMessage.append(columns[i].pop());
		}

		return finalMessage.toString();
	}

	public String solvePuzzle2() {
		StringBuilder finalMessage = new StringBuilder();
		char[][] startingState = new char[rows][cols];
		List<int[]> steps = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			int idx = 0;

			while ((currentLine = br.readLine()) != null) {
				if (idx < rows) { // add to the starting state
					char[] row = new char[cols];
					for (int i = 0; i < cols; i++) {
						if (currentLine.length() > i * 4 + 1) {
							char charAt = currentLine.charAt(i * 4 + 1);
							if (charAt != ' ') {
								row[i] = charAt;
							}
						}
					}
					startingState[idx] = row;
				} else if (idx > rows + 1) { // add to the steps
					String[] strings = currentLine.split(" ");
					int[] amtFromTo = new int[3];
					amtFromTo[0] = Integer.parseInt(strings[1]);
					amtFromTo[1] = Integer.parseInt(strings[3]);
					amtFromTo[2] = Integer.parseInt(strings[5]);
					steps.add(amtFromTo);
				}

				idx++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// translate starting state into stacks representing columns
		Stack<Character>[] columns = new Stack[cols];
		for (int i = 0; i < cols; i++) {
			columns[i] = new Stack<Character>();
		}

		for (int i = rows - 1; i >= 0; i--) {
			for (int j = 0; j < cols; j++) {
				if (startingState[i][j] != '\u0000') {
					columns[j].push(startingState[i][j]);
				}
			}
		}

		// go thru list of steps and perform them all
		for (int[] step : steps) {
			Stack<Character> helperStack = new Stack<>();
			int amount = step[0];
			int from = step[1] - 1;
			int to = step[2] - 1;

			for (int i = 0; i < amount; i++) {
				helperStack.push(columns[from].pop());
			}

			for (int i = 0; i < amount; i++) {
				columns[to].push(helperStack.pop());
			}
		}

		// assemble final message
		for (int i = 0; i < cols; i++) {
			finalMessage.append(columns[i].pop());
		}

		return finalMessage.toString();
	}
}
