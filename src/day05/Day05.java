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

		List<int[]> amountFromTo = new ArrayList<>();
		char[][] startingState = new char[rows][cols];

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			int idx = 0;

			while ((currentLine = br.readLine()) != null) {
				if (idx < rows) {
					// add to the starting state
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
				} else if (idx > rows + 1) {
					// add to the steps
					String[] strings = currentLine.split(" ");
					int[] amtFromTo = new int[3];
					amtFromTo[0] = Integer.parseInt(strings[1]);
					amtFromTo[1] = Integer.parseInt(strings[3]);
					amtFromTo[2] = Integer.parseInt(strings[5]);
					amountFromTo.add(amtFromTo);
				}

				idx++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// revamp starting state -- read the rows/columns into stacks representing each column
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

		// go thru list of steps ("amountFromTo") and perform them all
		for (int[] arr : amountFromTo) {
			for (int a = 0; a < arr[0]; a++) { // amount
				char c = columns[arr[1] - 1].pop(); // from
				columns[arr[2] - 1].push(c); // to
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

		List<int[]> amountFromTo = new ArrayList<>();
		char[][] startingState = new char[rows][cols];

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			int idx = 0;

			while ((currentLine = br.readLine()) != null) {
				if (idx < rows) {
					// add to the starting state
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
				} else if (idx > rows + 1) {
					// add to the steps
					String[] strings = currentLine.split(" ");
					int[] amtFromTo = new int[3];
					amtFromTo[0] = Integer.parseInt(strings[1]);
					amtFromTo[1] = Integer.parseInt(strings[3]);
					amtFromTo[2] = Integer.parseInt(strings[5]);
					amountFromTo.add(amtFromTo);
				}

				idx++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// revamp starting state -- read the rows/columns into stacks representing each column
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

		// go thru list of steps ("amountFromTo") and perform them all
		for (int[] arr : amountFromTo) {
			Stack<Character> temp = new Stack<>();
			for (int a = 0; a < arr[0]; a++) { // amount
				char c = columns[arr[1] - 1].pop(); // from
				temp.push(c);
			}

			for (int i = 0; i < arr[0]; i++) {
				columns[arr[2] - 1].push(temp.pop()); // to
			}
		}

		// assemble final message
		for (int i = 0; i < cols; i++) {
			finalMessage.append(columns[i].pop());
		}

		return finalMessage.toString();
	}
}
