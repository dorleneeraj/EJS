package data.structure.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//This is the algorithm for BFS. It visits each and every reachable vertex of the graph
//starting from some source vertex 's' and finds the shortest distance from 's' to 
// every reachable vertex 'v'.

public class BreadthFirstSearch {
	private final int COLOUR_WHITE = 0;
	private final int COLOUR_GRAY = 1;
	private final int COLOUR_BLACK = 2;

	private int colourArray[];
	private int distanceArray[];
	private Integer predecessorArray[];

	private final int noOfVertices;
	private final SimpleGraph graph;

	public BreadthFirstSearch(SimpleGraph graph) {
		this.graph = graph;
		this.noOfVertices = graph.getNumberOfVertices();
		this.colourArray = new int[noOfVertices];
		this.distanceArray = new int[noOfVertices];
		this.predecessorArray = new Integer[noOfVertices];

	}

	public void performBFS(int startingVertex) {
		// initialize the arrays

		for (int i = 0; i < noOfVertices; i++) {
			colourArray[i] = COLOUR_WHITE;
			distanceArray[i] = Integer.MAX_VALUE;
			predecessorArray[i] = null;
		}

		// Now set the color of the source/ starting index = white
		// distance = 0
		// predecessor will as it is be NULL.
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(startingVertex);
		colourArray[startingVertex - 1] = COLOUR_GRAY;
		distanceArray[startingVertex - 1] = 0;

		while (!queue.isEmpty()) {
			int currentNode = queue.poll();
			ArrayList<Integer> adjacencyList = (ArrayList<Integer>) graph
					.getAdjacentVertices(currentNode);
			for (Integer i : adjacencyList) {
				if (colourArray[i - 1] == COLOUR_WHITE) {
					colourArray[i - 1] = COLOUR_GRAY;
					distanceArray[i - 1] = distanceArray[currentNode - 1] + 1;
					predecessorArray[i - 1] = currentNode;
					queue.add(i);
				}
			}
			colourArray[currentNode - 1] = COLOUR_BLACK;
		}

		System.out.println("Distance Array" + Arrays.toString(distanceArray));
		System.out.println("Colour Array" + Arrays.toString(colourArray));
		System.out.println("Predecessor Array"
				+ Arrays.toString(predecessorArray));

	}

	public static void main(String[] args) {
		SimpleGraph sG = new SimpleGraph(5);
		int[][] edges = new int[6][];
		edges[0] = new int[] { 1, 2 };
		edges[1] = new int[] { 1, 3 };
		edges[2] = new int[] { 2, 3 };
		edges[3] = new int[] { 2, 4 };
		edges[4] = new int[] { 3, 5 };
		edges[5] = new int[] { 4, 5 };
		sG.buildGraph(edges);
		System.out.println(sG.toString());

		BreadthFirstSearch bFS = new BreadthFirstSearch(sG);
		bFS.performBFS(1);
	}

}
