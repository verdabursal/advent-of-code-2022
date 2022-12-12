package day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day12 {
	String filepath = "src/day12/data.txt";
	String alphabet = "abcdefghijklmnopqrstuvwxyz";

	public int solvePuzzle1() {
		List<List<Integer>> matrix = new ArrayList<>();
		int[] start = new int[2];
		int[] end = new int[2];

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			int i = 0;
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				List<Integer> matrixRow = new ArrayList<>();
				for (int j = 0; j < currentLine.length(); j++) {
					switch (currentLine.charAt(j)) {
						case 'S':
							start[0] = i;
							start[1] = j;
							matrixRow.add(0);
							break;
						case 'E':
							end[0] = i;
							end[1] = j;
							matrixRow.add(25);
							break;
						default:
							matrixRow.add(alphabet.indexOf(currentLine.charAt(j)));
							break;
					}
				}
				matrix.add(matrixRow);
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// transform matrix into a graph
		Graph graph = new Graph();
		Set<Node> nodes = new HashSet<>();
		List<List<Node>> nodesMatrix = new ArrayList<>();
		for (int i = 0; i < matrix.size(); i++) {
			List<Node> nodesMatrixRow = new ArrayList<>();
			for (int j = 0; j < matrix.get(0).size(); j++) {
				Node node = new Node(i + "," + j);
				nodesMatrixRow.add(node);
			}
			nodesMatrix.add(nodesMatrixRow);
		}

		// assemble adjacencies
		for (int i = 0; i < matrix.size(); i++) {
			for (int j = 0; j < matrix.get(0).size(); j++) {
				Node node = nodesMatrix.get(i).get(j);
				if (i > 0 && matrix.get(i - 1).get(j) - matrix.get(i).get(j) <= 1) {
					node.getAdjacentNodes().put(nodesMatrix.get(i - 1).get(j), 1);
				}
				if (j > 0 && matrix.get(i).get(j - 1) - matrix.get(i).get(j) <= 1) {
					node.getAdjacentNodes().put(nodesMatrix.get(i).get(j - 1), 1);
				}
				if (i < matrix.size() - 1 && matrix.get(i + 1).get(j) - matrix.get(i).get(j) <= 1) {
					node.getAdjacentNodes().put(nodesMatrix.get(i + 1).get(j), 1);
				}
				if (j < matrix.get(0).size() - 1 && matrix.get(i).get(j + 1) - matrix.get(i).get(j) <= 1) {
					node.getAdjacentNodes().put(nodesMatrix.get(i).get(j + 1), 1);
				}
				nodes.add(node);
			}
		}
		graph.setNodes(nodes);

		Graph dijkstra = calculateShortestPathFromSource(graph, nodesMatrix.get(start[0]).get(start[1]));
		Node endNode = new Node("");
		for (Node n : dijkstra.getNodes()) {
			if (n.getName().equals(end[0] + "," + end[1])) {
				endNode = n;
			}
		}

		return endNode.getDistance();
	}

	public int solvePuzzle2() {
		List<List<Integer>> matrix = new ArrayList<>();
		List<int[]> starts = new ArrayList<>();
		int[] end = new int[2];

		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			int i = 0;
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				List<Integer> matrixRow = new ArrayList<>();
				for (int j = 0; j < currentLine.length(); j++) {
					switch (currentLine.charAt(j)) {
						case 'a':
							starts.add(new int[]{i, j});
							matrixRow.add(0);
							break;
						case 'E':
							end[0] = i;
							end[1] = j;
							matrixRow.add(25);
							break;
						default:
							matrixRow.add(alphabet.indexOf(currentLine.charAt(j)));
							break;
					}
				}
				matrix.add(matrixRow);
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// transform matrix into a graph
		Graph graph = new Graph();
		Set<Node> nodes = new HashSet<>();
		List<List<Node>> nodesMatrix = new ArrayList<>();
		for (int i = 0; i < matrix.size(); i++) {
			List<Node> nodesMatrixRow = new ArrayList<>();
			for (int j = 0; j < matrix.get(0).size(); j++) {
				Node node = new Node(i + "," + j);
				nodesMatrixRow.add(node);
			}
			nodesMatrix.add(nodesMatrixRow);
		}

		// assemble adjacencies
		for (int i = 0; i < matrix.size(); i++) {
			for (int j = 0; j < matrix.get(0).size(); j++) {
				Node node = nodesMatrix.get(i).get(j);
				if (i > 0 && matrix.get(i - 1).get(j) - matrix.get(i).get(j) <= 1) {
					node.getAdjacentNodes().put(nodesMatrix.get(i - 1).get(j), 1);
				}
				if (j > 0 && matrix.get(i).get(j - 1) - matrix.get(i).get(j) <= 1) {
					node.getAdjacentNodes().put(nodesMatrix.get(i).get(j - 1), 1);
				}
				if (i < matrix.size() - 1 && matrix.get(i + 1).get(j) - matrix.get(i).get(j) <= 1) {
					node.getAdjacentNodes().put(nodesMatrix.get(i + 1).get(j), 1);
				}
				if (j < matrix.get(0).size() - 1 && matrix.get(i).get(j + 1) - matrix.get(i).get(j) <= 1) {
					node.getAdjacentNodes().put(nodesMatrix.get(i).get(j + 1), 1);
				}
				nodes.add(node);
			}
		}
		graph.setNodes(nodes);

		int shortestDistance = Integer.MAX_VALUE;
		for (int[] start : starts) {
			Graph dijkstra = calculateShortestPathFromSource(graph, nodesMatrix.get(start[0]).get(start[1]));
			Node endNode = new Node("");
			for (Node n : dijkstra.getNodes()) {
				if (n.getName().equals(end[0] + "," + end[1])) {
					endNode = n;
				}
			}
			int dist = endNode.getDistance();
			if (dist < shortestDistance) {
				shortestDistance = dist;
			}
		}

		return shortestDistance;
	}

	Graph calculateShortestPathFromSource(Graph graph, Node source) {
		source.setDistance(0);

		Set<Node> settledNodes = new HashSet<>();
		Set<Node> unsettledNodes = new HashSet<>();

		unsettledNodes.add(source);

		while (unsettledNodes.size() != 0) {
			Node currentNode = getLowestDistanceNode(unsettledNodes);
			unsettledNodes.remove(currentNode);
			for (Map.Entry<Node, Integer> adjacencyPair:
				currentNode.getAdjacentNodes().entrySet()) {
				Node adjacentNode = adjacencyPair.getKey();
				Integer edgeWeight = adjacencyPair.getValue();
				if (!settledNodes.contains(adjacentNode)) {
					calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
					unsettledNodes.add(adjacentNode);
				}
			}
			settledNodes.add(currentNode);
		}
		return graph;
	}

	Node getLowestDistanceNode(Set < Node > unsettledNodes) {
		Node lowestDistanceNode = null;
		int lowestDistance = Integer.MAX_VALUE;
		for (Node node: unsettledNodes) {
			int nodeDistance = node.getDistance();
			if (nodeDistance < lowestDistance) {
				lowestDistance = nodeDistance;
				lowestDistanceNode = node;
			}
		}
		return lowestDistanceNode;
	}

	void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
		Integer sourceDistance = sourceNode.getDistance();
		if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
			evaluationNode.setDistance(sourceDistance + edgeWeigh);
			LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
			shortestPath.add(sourceNode);
			evaluationNode.setShortestPath(shortestPath);
		}
	}
}

class Graph {
	private Set<Node> nodes = new HashSet<>();

	public void addNode(Node nodeA) {
		nodes.add(nodeA);
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

	public Set<Node> getNodes() {
		return this.nodes;
	}
}

class Node {
	private String name;

	private List<Node> shortestPath = new LinkedList<>();

	private Integer distance = Integer.MAX_VALUE;

	Map<Node, Integer> adjacentNodes = new HashMap<>();

	public void addDestination(Node destination, int distance) {
		adjacentNodes.put(destination, distance);
	}

	public Node(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setShortestPath(List<Node> shortestPath) {
		this.shortestPath = shortestPath;
	}

	public List<Node> getShortestPath() {
		return this.shortestPath;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Integer getDistance() {
		return this.distance;
	}

	public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}

	public Map<Node, Integer> getAdjacentNodes() {
		return this.adjacentNodes;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof Node && ((Node) o).name.equals(this.name);
	}

	@Override
	public int hashCode() {
		String[] splitStr = name.split(",");
		return name.hashCode() + Integer.parseInt(splitStr[0]) + Integer.parseInt(splitStr[1]);
	}
}
