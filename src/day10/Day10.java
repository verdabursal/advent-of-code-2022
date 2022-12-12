package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day10 {
	String filepath = "src/day10/data.txt";

	Instruction addx = new Instruction("addx", 2);
	Instruction noop = new Instruction("noop", 1);

	public int solvePuzzle1() {
		int cycleNumber = 1;
		int x = 1;
		int signalStrengthSum = 0;

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				String[] splitStr = currentLine.split(" ");
				String command = splitStr[0];

				if (command.equals("addx")) {
					int amount = Integer.parseInt(splitStr[1]);

					for (int i = 0; i < addx.cycles; i++) {
						if (cycleNumber % 40 == 20) {
							int signalStrength = cycleNumber * x;
							signalStrengthSum += signalStrength;
						}
						if (i == addx.cycles - 1) {
							x += amount;
						}
						cycleNumber++;
					}
				} else if (command.equals("noop")) {
					if (cycleNumber % 40 == 20) {
						int signalStrength = cycleNumber * x;
						signalStrengthSum += signalStrength;
					}
					cycleNumber++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return signalStrengthSum;
	}

	public String solvePuzzle2() {
		int crtPosition = 0;
		int x = 1;
		int height = 6;
		int length = 40;
		boolean[][] visiblePixels = new boolean[height][length];

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				String[] splitStr = currentLine.split(" ");
				String command = splitStr[0];

				if (command.equals("addx")) {
					int amount = Integer.parseInt(splitStr[1]);

					for (int i = 0; i < addx.cycles; i++) {
						if (Math.abs(x % 40 - crtPosition % 40) <= 1) {
							// draw sprite on visiblePixels
							visiblePixels[Math.floorDiv(crtPosition, 40)][(crtPosition) % 40] = true;
						}
						if (i == addx.cycles - 1) {
							x += amount;
						}
						if (crtPosition == 239) {
							crtPosition = 1;
						} else {
							crtPosition++;
						}
					}
				} else if (command.equals("noop")) {
					if (Math.abs(x % 40 - crtPosition % 40) <= 1) {
						// draw sprite on visiblePixels
						visiblePixels[Math.floorDiv(crtPosition, 40)][(crtPosition) % 40] = true;
					}
					if (crtPosition == 239) {
						crtPosition = 1;
					} else {
						crtPosition++;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (boolean[] row : visiblePixels) {
			StringBuilder r = new StringBuilder();
			for (boolean b : row) {
				if (b) {
					r.append("#");
				} else {
					r.append(".");
				}
			}
			System.out.println(r.toString());
		}
		return "";
	}
}

class Instruction {
	int cycles;

	String name;

	Instruction(String name, int cycles) {
		this.name = name;
		this.cycles = cycles;
	}
}
