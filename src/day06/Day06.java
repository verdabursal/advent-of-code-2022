package day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Day06 {
	String filepath = "src/day06/data.txt";

	public int solvePuzzle1() {
		return solveHelper(4);
	}

	public int solvePuzzle2() {
		return solveHelper(14);
	}

	private int solveHelper(int sequenceSize) {
		String input = "";

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				input = currentLine;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		LinkedList<Character> ll = new LinkedList<>();
		if (input.length() >= sequenceSize) {
			for (int i = 0; i < sequenceSize; i++) {
				ll.offer(input.charAt(i));
			}
		}

		if (allDistinct(ll, sequenceSize)) return sequenceSize;

		for (int i = sequenceSize; i < input.length(); i++) {
			if (allDistinct(ll, sequenceSize)) {
				return i;
			} else {
				ll.poll();
				ll.offer(input.charAt(i));
			}
		}

		return input.length();
	}

	private boolean allDistinct(LinkedList<Character> charList, int sequenceSize) {
		Set<Character> charSet = new HashSet<>(charList);
		return charSet.size() == sequenceSize;
	}
}
