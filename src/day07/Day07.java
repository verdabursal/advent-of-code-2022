package day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day07 {
	String filepath = "src/day07/data.txt";
	String exec = "$";
	String cd = "cd";
	String ls = "ls";
	String dir = "dir";
	String back = "..";
	String home = "/";

	public int solvePuzzle1() {
		Stack<Directory> activeStack = new Stack<>();
		Directory homeDirectory = new Directory();
		homeDirectory.name = "/";
		activeStack.push(homeDirectory);
		solveHelper(activeStack);
		int totalTotalSizes = 0;

		// DFS traverse homeDirectory
		Stack<Directory> dfsStack = new Stack<>();
		dfsStack.push(homeDirectory);
		while (!dfsStack.isEmpty()) {
			// pop the head
			Directory currentDirectory = dfsStack.pop();
			// if its totalSize <=10,000, add its totalSize to totalTotalSizes
			int totalSize = currentDirectory.getTotalSize();
			if (totalSize <= 100000) totalTotalSizes += totalSize;
			// add all its child directories to dfsStack
			for (Directory d : currentDirectory.directories) {
				dfsStack.push(d);
			}
		}

		return totalTotalSizes;
	}

	public int solvePuzzle2() {
		int totalAvailDiskSpace = 70000000;
		int freeSpaceNeeded = 30000000;
		int maxTotalTotalSize = totalAvailDiskSpace - freeSpaceNeeded;

		Stack<Directory> activeStack = new Stack<>();
		Directory homeDirectory = new Directory();
		homeDirectory.name = "/";
		activeStack.push(homeDirectory);
		solveHelper(activeStack);

		// how much space do we need to free?
		int totalTotalSize = homeDirectory.getTotalSize();
		int minimumSpaceToFree = totalTotalSize - maxTotalTotalSize;
		int smallestDeletableDirectorySize = totalTotalSize;

		// DFS traverse homeDirectory
		Stack<Directory> dfsStack = new Stack<>();
		dfsStack.push(homeDirectory);
		while (!dfsStack.isEmpty()) {
			// pop the head
			Directory currentDirectory = dfsStack.pop();
			// if its totalSize < smallestDeletableDirectorySize, replace
			int totalSize = currentDirectory.getTotalSize();
			if (totalSize >= minimumSpaceToFree
				&& totalSize <= smallestDeletableDirectorySize) {
				smallestDeletableDirectorySize = totalSize;
			}
			// add all its child directories to dfsStack
			for (Directory d : currentDirectory.directories) {
				dfsStack.push(d);
			}
		}

		return smallestDeletableDirectorySize;
	}

	private void solveHelper(Stack<Directory> activeStack) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				String[] command = currentLine.split(" ");
				if (command[0].equals(exec)) {
					// starts with $
					if (command[1].equals(cd)) { // ignoring the ls case for now
						if (command[2].equals(home)) {
							// do nothing, home is already initialized
						} else if (command[2].equals(back)) {
							activeStack.pop();
						} else {
							Directory newHead = activeStack.peek().getOrCreateChildDirectoryNamed(command[2]);
							activeStack.push(newHead);
						}
					}
				} else if (command[0].equals(dir)) {
					// starts with dir
					// activeStack.peek().add new directory with name command[1]
					activeStack.peek().getOrCreateChildDirectoryNamed(command[1]); // createIfAbsent?
				} else {
					// starts with a number, meaning file size
					File file = new File();
					file.size = Integer.parseInt(command[0]);
					file.name = command[1];
					activeStack.peek().files.add(file);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Directory {
	String name;
	Set<File> files = new HashSet<>();
	Set<Directory> directories = new HashSet<>();

	int getTotalSize() {
		int totalSize = 0;
		for (File f : files) {
			totalSize += f.size;
		}
		for (Directory d : directories) {
			totalSize += d.getTotalSize();
		}
		return totalSize;
	}

	public Directory getOrCreateChildDirectoryNamed(String name) {
		for (Directory d : directories) {
			if (d.name.equals(name)) {
				return d;
			}
		}

		Directory newChild = new Directory();
		newChild.name = name;
		directories.add(newChild);
		return newChild;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof Directory && this.name.equals(((Directory)o).name);
	}

	@Override
	public int hashCode() {
		return this.name.hashCode() + this.name.length();
	}
}

class File {
	String name;
	int size;

	@Override
	public boolean equals(Object o) {
		return o instanceof File && this.name.equals(((File)o).name);
	}

	@Override
	public int hashCode() {
		return this.name.hashCode() + this.name.length() * 2;
	}
}
