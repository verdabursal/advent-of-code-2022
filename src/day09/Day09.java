package day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Day09 {
	String filepath = "src/day09/data.txt";

	public int solvePuzzle1() {
		Duple s = new Duple(0, 0);
		Duple h = new Duple(0, 0);
		Duple t = new Duple(0, 0);
		Set<Duple> tVisited = new HashSet<>();
		tVisited.add(s);

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
							h.second++;
							break;
						case "R":
							h.first++;
							break;
						case "D":
							h.second--;
							break;
						case "L":
							h.first--;
							break;
					}
					// move tail
					// don't move if they are adjacent including diagonally or atop
					if (h.first - t.first >= 1 && h.second - t.second >= 1
						&& (h.first - t.first > 1 || h.second - t.second > 1)) {
						t.first++;
						t.second++;
					} else if (t.first - h.first >= 1 && h.second - t.second >= 1
						&& (t.first - h.first > 1 || h.second - t.second > 1)) {
						t.first--;
						t.second++;
					} else if (h.first - t.first >= 1 && t.second - h.second >= 1
						&& (h.first - t.first > 1 || t.second - h.second > 1)) {
						t.first++;
						t.second--;
					} else if (t.first - h.first >= 1 && t.second - h.second >= 1
						&& (t.first - h.first > 1 || t.second - h.second > 1)) {
						t.first--;
						t.second--;
					} else if (Math.abs(h.first - t.first) > 1) {
						t.first = (h.first + t.first) / 2;
					} else if (Math.abs(h.second - t.second) > 1) {
						t.second = (h.second + t.second) / 2;
					}
					tVisited.add(t.copy());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tVisited.size();
	}

	public int solvePuzzle2() {
		Duple s = new Duple(0, 0);
		Duple[] knots = new Duple[10];
		Set<Duple> tVisited = new HashSet<>();
		tVisited.add(s);
		for (int i = 0; i < 10; i++) {
			knots[i] = (new Duple(0, 0));
		}

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
					for (int j = 1; j < 10; j++) {
						Duple former = knots[j - 1];
						Duple current = knots[j];
						if (former.first - current.first >= 1 && former.second - current.second >= 1
							&& (former.first - current.first > 1 || former.second - current.second > 1)) {
							current.first++;
							current.second++;
						} else if (current.first - former.first >= 1 && former.second - current.second >= 1
							&& (current.first - former.first > 1 || former.second - current.second > 1)) {
							current.first--;
							current.second++;
						} else if (former.first - current.first >= 1 && current.second - former.second >= 1
							&& (former.first - current.first > 1 || current.second - former.second > 1)) {
							current.first++;
							current.second--;
						} else if (current.first - former.first >= 1 && current.second - former.second >= 1
							&& (current.first - former.first > 1 || current.second - former.second > 1)) {
							current.first--;
							current.second--;
						} else if (Math.abs(former.first - current.first) > 1) {
							current.first = (former.first + current.first) / 2;
						} else if (Math.abs(former.second - current.second) > 1) {
							current.second = (former.second + current.second) / 2;
						}
					}
					tVisited.add(knots[9].copy());
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
