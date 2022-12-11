package day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class Day11 {
	String filepath = "src/day11/data.txt";

	public BigInteger solvePuzzle1() {
		Map<String, BigInteger> biggies = new HashMap<>();
		biggies.putIfAbsent("3", new BigInteger("3"));
		List<Monkey> monkeys = readInputData(biggies);

		// 20 rounds of game
		for (int i = 0; i < 20; i++) {
			for (Monkey m : monkeys) {
				for (Item item : m.items) {
					// inspections++
					m.inspections = m.inspections.add(BigInteger.ONE);
					// perform operation, then divide by 3
					item.value = m.operation.operate(item.value).divide(biggies.get("3"));
					// decide where to send item
					if (item.value.mod(biggies.get(m.testDivBy.toString())).equals(BigInteger.ZERO)) {
						monkeys.get(m.ifTrueMonkey).items.add(item);
					} else {
						monkeys.get(m.ifFalseMonkey).items.add(item);
					}
					System.gc();
				}
				m.items = new ArrayList<>();
			}
		}

		// who inspected the most?
		List<BigInteger> allInspections = new ArrayList<>();
		for (Monkey m : monkeys) {
			allInspections.add(m.inspections);
		}
		allInspections.sort(Collections.reverseOrder());

		return allInspections.get(0).multiply(allInspections.get(1));
	}

	public BigInteger solvePuzzle2() {
		Map<String, BigInteger> biggies = new HashMap<>();
		List<Monkey> monkeys = readInputData(biggies);

		// 10000 rounds of game
		for (long i = 0L; i < 10000L; i++) {
			for (Monkey m : monkeys) {
				for (Item item : m.items) {
					// inspections++
					m.inspections = m.inspections.add(BigInteger.ONE);
					// perform operation
					item.value = m.operation.operate(item.value);
					if (i < 1000) {
						System.out.println(item.value);
					}
					// decide where to send item
					if (item.value.mod(biggies.get((m.testDivBy.toString()))).equals(BigInteger.ZERO)) {
						monkeys.get(m.ifTrueMonkey).items.add(item);
					} else {
						monkeys.get(m.ifFalseMonkey).items.add(item);
					}
				}
				m.items.clear();
			}

			if (Arrays.asList(0L, 19L, 99L, 499L, 999L, 1999L, 2999L, 3999L, 4999L, 5999L, 6999L, 7999L,
				8999L, 9999L).contains(i)) {
				System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!!" + i);
				for (Monkey m : monkeys) {
					System.out.println(m.inspections);
				}
			}
		}

		// who inspected the most?
		List<BigInteger> allInspections = new ArrayList<>();
		for (Monkey m : monkeys) {
			allInspections.add(m.inspections);
		}
		allInspections.sort(Collections.reverseOrder());

		return allInspections.get(0).multiply(allInspections.get(1));
	}

	List<Monkey> readInputData(Map<String, BigInteger> biggies) {
		List<Monkey> monkeys = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			Monkey currentMonkey = new Monkey();
			while ((currentLine = br.readLine()) != null) {
				String currentLineTrimmed = currentLine.trim();
				if (currentLineTrimmed.length() == 0) {
					monkeys.add(currentMonkey.copy());
				}
				String[] splitStr = currentLineTrimmed.split(" ");
				switch (splitStr[0]) {
					case "Monkey":
						currentMonkey = new Monkey();
						currentMonkey.name = Integer.parseInt(splitStr[1].substring(0, 1));
						break;
					case "Starting":
						List<Item> startingItems = new ArrayList<>();
						for (int i = 2; i < splitStr.length; i++) {
							int value = Integer.parseInt(splitStr[i].replace(",", ""));
							startingItems.add(new Item(BigInteger.valueOf(value)));
						}
						currentMonkey.items = startingItems;
						break;
					case "Operation:":
						switch (splitStr[4]) {
							case "+":
								biggies.putIfAbsent(splitStr[5], new BigInteger(splitStr[5]));
								currentMonkey.operation = old -> old.add(new BigInteger(splitStr[5]));
								break;
							case "*":
								if (splitStr[5].equals("old")) {
									currentMonkey.operation = old -> old.pow(2);
								} else {
									biggies.putIfAbsent(splitStr[5], new BigInteger(splitStr[5]));
									currentMonkey.operation = old -> old.multiply(new BigInteger(splitStr[5]));
								}
								break;
						}
						break;
					case "Test:":
						biggies.putIfAbsent(splitStr[3], new BigInteger(splitStr[3]));
						currentMonkey.testDivBy = biggies.get(splitStr[3]);
						break;
					case "If":
						switch (splitStr[1]) {
							case "true:":
								currentMonkey.ifTrueMonkey = Integer.parseInt(splitStr[5]);
								break;
							case "false:":
								currentMonkey.ifFalseMonkey = Integer.parseInt(splitStr[5]);
								break;
						}
						break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return monkeys;
	}
}

class Monkey {
	int name; // helpful for debugging
	BigInteger inspections = BigInteger.ZERO;
	List<Item> items;
	Operation operation;
	BigInteger testDivBy;
	int ifTrueMonkey;
	int ifFalseMonkey;

	Monkey copy() {
		Monkey copy = new Monkey();
		copy.name = this.name;
		copy.inspections = this.inspections;
		copy.items = this.items;
		copy.operation = this.operation;
		copy.testDivBy = this.testDivBy;
		copy.ifTrueMonkey = this.ifTrueMonkey;
		copy.ifFalseMonkey = this.ifFalseMonkey;
		return copy;
	}
}

class Item {
	BigInteger value;

	Item(BigInteger value) {
		this.value = value;
	}
}

interface Operation {
	BigInteger operate(BigInteger old);
}
