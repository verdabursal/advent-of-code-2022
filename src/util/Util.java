package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Util {
	public static ArrayList<String> readStringsFromFile(String filepath) {
		ArrayList<String> stringList = new ArrayList<>();

		// read the txt file and populate the array
		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				stringList.add(currentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stringList;
	}

	public static ArrayList<Integer> readIntsFromStrings(ArrayList<String> strings) {
		ArrayList<Integer> intList = new ArrayList<>();

		for (String s : strings) {
			intList.add(Integer.parseInt(s));
		}

		return intList;
	}

	public static ArrayList<Integer> readIntsFromFile(String filepath) {
		return readIntsFromStrings(readStringsFromFile(filepath));
	}

	public static ArrayList<int[]> readIntArraysFromStrings(ArrayList<String> strings) {
		ArrayList<int[]> intArrayList = new ArrayList<>();

		for (String s : strings) {
			String[] splitCurrentLine = s.split(" ");
			int direction = 0;
			switch (splitCurrentLine[0]) {
				case "forward":
					direction = 1;
					break;
				case "down":
					direction = 2;
					break;
				case "up":
					direction = 3;
					break;
				default:
					// do nothing
					break;
			}
			int amount = Integer.parseInt(splitCurrentLine[1]);
			intArrayList.add(new int[]{direction, amount});
		}

		return intArrayList;
	}

	public static ArrayList<int[]> readIntArraysFromFile(String filepath) {
		return readIntArraysFromStrings(readStringsFromFile(filepath));
	}

	public static ArrayList<int[]> readBinaryFromStrings(ArrayList<String> strings) {
		ArrayList<int[]> binaries = new ArrayList<>();
		int binaryLength = strings.get(0).length();

		for (String s : strings) {
			int[] asdf = new int[binaryLength];

			for (int i = 0; i < binaryLength; i++) {
				asdf[i] = Integer.parseInt(String.valueOf(s.charAt(i)));
			}

			binaries.add(asdf);
		}

		return binaries;
	}

	public static ArrayList<int[]> readBinariesFromFile(String filepath) {
		return readBinaryFromStrings(readStringsFromFile(filepath));
	}
}