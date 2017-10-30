package data.structure.graphs;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BellmanFord<T> {

	class Node {
		NewVertex<T> parent;
		int shortestDistance;

		public Node(NewVertex<T> v, int distance) {
			this.parent = v;
			this.shortestDistance = distance;
		}

		public NewVertex<T> getParent() {
			return parent;
		}

		public void setParent(NewVertex<T> parent) {
			this.parent = parent;
		}

		public int getShortestDistance() {
			return shortestDistance;
		}

		public void setShortestDistance(int shortestDistance) {
			this.shortestDistance = shortestDistance;
		}

		@Override
		public String toString() {
			return "Node [parent=" + parent + ", shortestDistance="
					+ shortestDistance + "]";
		}

	}

	NewGraph<Integer> graph;
	Map<NewVertex<Integer>, Node> map;

	public Map<NewVertex<Integer>, Node> findShortestPaths(NewGraph<Integer> G) {
		this.graph = G;
		initialize();
		int size = graph.getAllVertex().size();
		// start from any source node.
		Collection<NewVertex<Integer>> vertices = graph.getAllVertex();

		NewVertex<Integer> startVertex = graph.getVertex(1);
		map.get(startVertex).setShortestDistance(0);
		List<NewEdge<Integer>> edges = graph.getAllEdges();
		System.out.println(edges.size());
		System.out.println("edges:");
		for (NewEdge<Integer> e : edges) {
			System.out.println(e);
		}

		for (int i = 1; i < size; i++) {
			for (NewEdge<Integer> edge : graph.getAllEdges()) {
				relaxEdge(edge);
			}
		}
		return map;
	}

	public void relaxEdge(NewEdge<Integer> edge) {
		NewVertex<Integer> v1 = edge.getVertex(1);
		NewVertex<Integer> v2 = edge.getVertex(2);
		int weight = edge.getWeight();
		Node node1 = map.get(v1);
		Node node2 = map.get(v2);
		int v1Distance = node1.getShortestDistance();
		int v2Distance = node2.getShortestDistance();

		if (v1Distance != Integer.MAX_VALUE) {

			if (v2Distance > v1Distance + weight) {
				node2.setShortestDistance(v1Distance + weight);
				node2.setParent((NewVertex<T>) v1);
			}
		}
		System.out.println(map);
	}

	public void initialize() {
		Collection<NewVertex<Integer>> vertices = graph.getAllVertex();
		map = new HashMap<NewVertex<Integer>, Node>();
		for (NewVertex<Integer> v : vertices) {
			map.put(v, new Node(null, Integer.MAX_VALUE));
		}
	}

	public static void main(String[] args) {
		NewGraph<Integer> graph = new NewGraph<Integer>(true);
		graph.addEdge(1, 2, 6);
		graph.addEdge(2, 3, 5);
		graph.addEdge(3, 2, -2);
		graph.addEdge(1, 4, 7);
		graph.addEdge(2, 4, 8);
		graph.addEdge(5, 1, 2);
		graph.addEdge(4, 5, 9);
		graph.addEdge(5, 3, 7);
		graph.addEdge(4, 3, -3);
		graph.addEdge(2, 5, -4);

		Map<NewVertex<Integer>, BellmanFord<Integer>.Node> map = new BellmanFord<Integer>()
				.findShortestPaths(graph);

		for (NewVertex<Integer> v : map.keySet()) {
			BellmanFord<Integer>.Node n = map.get(v);
			System.out.println("Vertex: " + v + " " + n.getParent() + " "
					+ n.getShortestDistance());
		}

	}

}
