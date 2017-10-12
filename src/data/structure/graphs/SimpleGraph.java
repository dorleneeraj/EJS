package data.structure.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// initially creating for undirected graph

public class SimpleGraph extends Graph {

	private List<Integer>[] Adj;
	private int numberOfVertices;

	public SimpleGraph(int numberOfVertices) {
		this.numberOfVertices = numberOfVertices;
		this.Adj = (List<Integer>[]) new List[this.numberOfVertices];
	}

	public int getNumberOfVertices() {
		return numberOfVertices;
	}

	public void setNumberOfVertices(int numberOfVertices) {
		this.numberOfVertices = numberOfVertices;
	}

	@Override
	public Graph buildGraph() {
		return null;
	}

	public Graph buildGraph(int[][] edges) {
		int counter = 0;
		while (counter < edges.length) {
			int[] a = edges[counter];
			if ((a[0] <= this.numberOfVertices)
					&& (a[1] <= this.numberOfVertices)) {
				if (null != Adj[a[0] - 1]) {
					Adj[a[0] - 1].add(a[1]);
				} else {
					List<Integer> l = new ArrayList<Integer>();
					Adj[a[0] - 1] = l;
					l.add(a[1]);
				}
				if (null != Adj[a[1] - 1]) {
					Adj[a[1] - 1].add(a[0]);
				} else {
					List<Integer> l = new ArrayList<Integer>();
					Adj[a[1] - 1] = l;
					l.add(a[0]);
				}
			}
			counter++;
		}
		return this;
	}

	@Override
	public List<?> getAdjacentVertices(int vertex) {
		return Adj[vertex - 1];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(Adj);
		result = prime * result + numberOfVertices;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleGraph other = (SimpleGraph) obj;
		if (!Arrays.equals(Adj, other.Adj))
			return false;
		if (numberOfVertices != other.numberOfVertices)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SimpleGraph [Adj=" + Arrays.toString(Adj)
				+ ", numberOfVertices=" + numberOfVertices + "]";
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
	}
}
