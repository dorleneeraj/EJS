package data.structure.graphs;

import java.util.ArrayList;
import java.util.Arrays;

public class DepthFirstSearch {
	private final int COLOUR_WHITE = 0;
	private final int COLOUR_GRAY = 1;
	private final int COLOUR_BLACK = 2;

	private int colourArray[];
	private Integer predecessorArray[];
	private Integer d[];
	private Integer f[];

	private final int noOfVertices;
	private final SimpleGraph graph;

	private static Integer time = 0;

	public DepthFirstSearch(SimpleGraph graph) {
		this.graph = graph;
		this.noOfVertices = graph.getNumberOfVertices();
		this.colourArray = new int[noOfVertices];
		this.predecessorArray = new Integer[noOfVertices];
		this.d = new Integer[noOfVertices];
		this.f = new Integer[noOfVertices];
	}

	public void performDFS() {
		for (int i = 0; i < noOfVertices; i++) {
			colourArray[i] = COLOUR_WHITE;
			predecessorArray[i] = null;
			d[i] = Integer.MIN_VALUE;
			f[i] = Integer.MIN_VALUE;
		}

		int currentNode = 1;
		while (currentNode <= noOfVertices) {
			if (colourArray[currentNode - 1] == COLOUR_WHITE) {
				performDFSVisit(currentNode);
			}
			currentNode++;
		}

		System.out.println("Visited Array" + Arrays.toString(d));
		System.out.println("Left Array" + Arrays.toString(f));
		System.out.println("Colour Array" + Arrays.toString(colourArray));
		System.out.println("Predecessor Array"
				+ Arrays.toString(predecessorArray));
	}

	private void performDFSVisit(int currentNode) {
		colourArray[currentNode - 1] = COLOUR_GRAY;
		time = time + 1;
		d[currentNode - 1] = time;
		for (Integer i : (ArrayList<Integer>) this.graph
				.getAdjacentVertices(currentNode)) {
			if (colourArray[i - 1] == COLOUR_WHITE) {
				predecessorArray[i - 1] = currentNode;
				performDFSVisit(i);
			}
		}
		colourArray[currentNode - 1] = COLOUR_BLACK;
		time = time + 1;
		f[currentNode - 1] = time;
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

		DepthFirstSearch bFS = new DepthFirstSearch(sG);
		bFS.performDFS();
	}

}
