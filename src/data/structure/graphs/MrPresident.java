package data.structure.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MrPresident {

	public static List<PEdge<Integer>> getMST(PGraph<Integer> graph) {
		List<PEdge<Integer>> allEdges = graph.getAllEdges();
		Collections.sort(allEdges, new EdgeComparatorAscending());
		DisjointSetCompressed dSC = new DisjointSetCompressed();
		dSC.initialize(graph.getAllVertex().size());
		List<PEdge<Integer>> resultEdge = new ArrayList<PEdge<Integer>>();
		for (PEdge<Integer> edge : allEdges) {
			long root1 = dSC.root((int) edge.getVertex(1).id);
			long root2 = dSC.root((int) edge.getVertex(2).id);

			if (root1 == root2) {
				continue;
			} else {
				resultEdge.add(edge);
				dSC.union((int) edge.getVertex(1).id,
						(int) edge.getVertex(2).id);
			}
		}
		return resultEdge;
	}

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int cities = s.nextInt();
		int roads = s.nextInt();
		int maxCost = s.nextInt();
		PGraph<Integer> mGraph = new PGraph<Integer>(false);
		int totalCost = 0;
		int superRoads = 0;
		for (int i = 0; i < roads; i++) {
			int a = s.nextInt();
			int b = s.nextInt();
			int cost = s.nextInt();
			mGraph.addEdge(a, b, cost);
			totalCost += cost;
		}
		s.close();
		if (maxCost < cities || roads < cities) {
			System.out.println("-1");
			return;
		}

		if (totalCost <= maxCost) {
			System.out.println(0);
		} else {
			List<PEdge<Integer>> list = getMST(mGraph);
			if (null == list || list.isEmpty()) {
				System.out.println(-1);
			} else {
				EdgeComparatorDescending descComparator = new EdgeComparatorDescending();
				Collections.sort(list, descComparator);

				totalCost = 0;
				for (PEdge<Integer> e : list) {
					totalCost += e.getWeight();
				}

				if (totalCost <= maxCost) {
					System.out.println(0);
				} else {
					for (PEdge<Integer> e : list) {
						totalCost -= e.getWeight();
						superRoads++;
						if (totalCost <= maxCost) {
							break;
						}
					}
					System.out.println(superRoads);
				}
			}
		}
	}

}

class EdgeComparatorDescending implements Comparator<PEdge<Integer>> {

	@Override
	public int compare(PEdge<Integer> edge1, PEdge<Integer> edge2) {
		if (edge1.getWeight() <= edge2.getWeight()) {
			return 1;
		} else {
			return -1;
		}
	}
}

class EdgeComparatorAscending implements Comparator<PEdge<Integer>> {

	@Override
	public int compare(PEdge<Integer> edge1, PEdge<Integer> edge2) {
		if (edge1.getWeight() <= edge2.getWeight()) {
			return -1;
		} else {
			return 1;
		}
	}
}

class PGraph<T> {
	private List<PEdge<T>> allEdges = new ArrayList<PEdge<T>>();
	private Map<Long, PVertex<T>> allVertex = new HashMap<Long, PVertex<T>>();
	private boolean isDirected = false;

	public PGraph(boolean isDirected) {
		this.isDirected = isDirected;
	}

	public PVertex<T> addSingleVertex(long id) {
		if (allVertex.containsKey(id)) {
			return allVertex.get(id);
		}
		PVertex<T> v = new PVertex<T>(id);
		allVertex.put(id, v);
		return v;
	}

	public PVertex<T> getVertex(long id) {
		return allVertex.get(id);
	}

	public void addEdge(long id1, long id2) {
		addEdge(id1, id2, 0);
	}

	public void addEdge(long id1, long id2, int weight) {

		PVertex<T> v1 = null;
		if (allVertex.containsKey(id1)) {
			v1 = allVertex.get(id1);
		} else {
			v1 = new PVertex<T>(id1);
			this.allVertex.put(id1, v1);
		}

		PVertex<T> v2 = null;
		if (allVertex.containsKey(v2)) {
			v2 = allVertex.get(v2);
		} else {
			v2 = new PVertex<T>(id2);
			allVertex.put(id2, v2);
		}

		PEdge<T> edge = new PEdge<T>(v1, v2);
		edge.setWeight(weight);
		allEdges.add(edge);
		v1.addAdjacentVertex(edge, v2);
		if (!isDirected) {
			v2.addAdjacentVertex(edge, v1);
		}
	}

	public List<PEdge<T>> getAllEdges() {
		return allEdges;
	}

	public Collection<PVertex<T>> getAllVertex() {
		return allVertex.values();
	}

	public void setDataForVertex(long id, T data) {
		if (allVertex.containsKey(id)) {
			PVertex<T> vertex = allVertex.get(id);
			vertex.setData(data);
		}
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (PEdge<T> edge : getAllEdges()) {
			buffer.append(edge.getVertex(1) + " " + edge.getVertex(2) + " "
					+ edge.getWeight());
			buffer.append("\n");
		}
		return buffer.toString();
	}

}

class PVertex<T> {
	long id;
	private T data;
	private List<PEdge<T>> edges = new ArrayList<PEdge<T>>();
	private List<PVertex<T>> adjacentVertex = new ArrayList<PVertex<T>>();

	public PVertex(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void addAdjacentVertex(PEdge<T> edge, PVertex<T> vertex) {
		edges.add(edge);
		adjacentVertex.add(vertex);
	}

	public List<PVertex<T>> getAdjacentVertices() {
		return adjacentVertex;
	}

	public List<PEdge<T>> getEdges() {
		return edges;
	}

	public int getDegree() {
		return edges.size();
	}

	@Override
	public String toString() {
		return "PVertex [id=" + id + ", data=" + data + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		PVertex<?> other = (PVertex<?>) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

class PEdge<T> {
	private boolean isDirected = false;
	private PVertex<T> vertex1;
	private PVertex<T> vertex2;
	private int weight;

	public PEdge(PVertex<T> v1, PVertex<T> v2) {
		this.vertex1 = v1;
		this.vertex2 = v2;
	}

	public PVertex<T> getVertex(int which) {
		if (which == 1) {
			return this.vertex1;
		} else if (which == 2) {
			return this.vertex2;
		}
		return null;
	}

	public boolean isDirected() {
		return isDirected;
	}

	public void setDirected(boolean isDirected) {
		this.isDirected = isDirected;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vertex1 == null) ? 0 : vertex1.hashCode());
		result = prime * result + ((vertex2 == null) ? 0 : vertex2.hashCode());
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
		PEdge<?> other = (PEdge<?>) obj;
		if (vertex1 == null) {
			if (other.vertex1 != null)
				return false;
		} else if (!vertex1.equals(other.vertex1))
			return false;
		if (vertex2 == null) {
			if (other.vertex2 != null)
				return false;
		} else if (!vertex2.equals(other.vertex2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PEdge [vertex1=" + vertex1 + ", vertex2=" + vertex2
				+ ", weight=" + weight + "]";
	}
}

class DisjointSetCompressed {
	private int[] Array;
	private int ARRAY_LENGTH;
	private int[] Size;

	public void initialize(int arrayLength) {
		this.ARRAY_LENGTH = arrayLength;
		Array = new int[this.ARRAY_LENGTH + 1];
		Size = new int[this.ARRAY_LENGTH + 1];

		for (int i = 0; i <= this.ARRAY_LENGTH; i++) {
			Array[i] = i;
			Size[i] = 1;
		}
	}

	public int root(int A) {
		while (Array[A] != A) {
			Array[A] = Array[Array[A]]; // This is how we set the value to its
										// GrandParent.
			A = Array[A];
		}
		return A;
	}

	public void union(int A, int B) {
		int root_A = root(A);
		int root_B = root(B);
		if (root_A != root_B) {
			if (Size[root_A] < Size[root_B]) {
				Array[root_A] = Array[root_B];
				Size[root_B] = Size[root_B] + Size[root_A];
			} else {
				Array[root_B] = Array[root_A];
				Size[root_A] = Size[root_B] + Size[root_A];
			}
		}
	}

	public boolean find(int A, int B) {
		boolean areConnected = false;
		if (root(A - 1) == root(B - 1)) {
			areConnected = true;
		}
		return areConnected;
	}

	public int[] getArray() {
		return Arrays.copyOf(this.Array, this.ARRAY_LENGTH + 1);
	}

	public int getARRAY_LENGTH() {
		return this.ARRAY_LENGTH;
	}

	public int[] getSize() {
		return Arrays.copyOf(this.Size, this.ARRAY_LENGTH + 1);
	}

}
