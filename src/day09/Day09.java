package day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Day09 {
	String filepath = "src/day09/data.txt";

	public int solvePuzzle1() {
		return solveHelper(2);
	}

	public int solvePuzzle2() {
		return solveHelper(10);
	}

	public int solveHelper(int knotCount) {
		Duple[] knots = new Duple[knotCount];
		for (int i = 0; i < knotCount; i++) {
			knots[i] = (new Duple(0, 0));
		}
		Set<Duple> tVisited = new HashSet<>();
		tVisited.add(new Duple(0, 0));

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				String[] splitStr = currentLine.split(" ");
				String direction = splitStr[0];
				int amount = Integer.parseInt(splitStr[1]);
				for (int i = 0; i < amount; i++) {
					// move head
					switch (direction) {
						case "U":
							knots[0].second++;
							break;
						case "R":
							knots[0].first++;
							break;
						case "D":
							knots[0].second--;
							break;
						case "L":
							knots[0].first--;
							break;
					}

					// move tails
					// don't move if they are adjacent including diagonally or atop
					for (int j = 1; j < knotCount; j++) {
						Duple former = knots[j - 1];
						Duple current = knots[j];
						int fx = former.first;
						int fy = former.second;
						int x = current.first;
						int y = current.second;

						if (fx - x >= 1 && fy - y >= 1 && (fx - x > 1 || fy - y > 1)) {
							current.first++;
							current.second++;
						} else if (x - fx >= 1 && fy - y >= 1 && (x - fx > 1 || fy - y > 1)) {
							current.first--;
							current.second++;
						} else if (fx - x >= 1 && y - fy >= 1 && (fx - x > 1 || y - fy > 1)) {
							current.first++;
							current.second--;
						} else if (x - fx >= 1 && y - fy >= 1 && (x - fx > 1 || y - fy > 1)) {
							current.first--;
							current.second--;
						} else if (Math.abs(fx - x) > 1) {
							current.first = (fx + x) / 2;
						} else if (Math.abs(fy - y) > 1) {
							current.second = (fy + y) / 2;
						}
					}

					tVisited.add(knots[knotCount - 1].copy());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tVisited.size();
	}
}

// new class for equality purposes for use in Set (int[] was not cutting it)
class Duple {
	int first;
	int second;

	Duple(int first, int second) {
		this.first = first;
		this.second = second;
	}

	Duple copy() {
		return new Duple(this.first, this.second);
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof Duple && ((Duple) o).first == this.first && ((Duple) o).second == this.second;
	}

	@Override
	public int hashCode() {
		return first * second + first + second;
	}

	// not necessary but useful for debugging
	@Override
	public String toString() {
		return "(" + first + "," + second + ")";
	}
}
