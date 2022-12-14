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

		// set up initial queue
		LinkedList<Character> ll = new LinkedList<>();
		if (input.length() >= sequenceSize) {
			for (int i = 0; i < sequenceSize; i++) {
				ll.offer(input.charAt(i));
			}
		}
		if (distinctSequence(ll, sequenceSize)) return sequenceSize;

		// move the queue forward along input; if distinct sequence, return
		for (int i = sequenceSize; i < input.length(); i++) {
			if (distinctSequence(ll, sequenceSize)) {
				return i;
			} else {
				ll.poll();
				ll.offer(input.charAt(i));
			}
		}

		// we have exhausted the whole input and still not found a distinct sequence
		return input.length();
	}

	private boolean distinctSequence(LinkedList<Character> charList, int sequenceSize) {
		Set<Character> charSet = new HashSet<>(charList);
		return charSet.size() == sequenceSize;
	}
}
