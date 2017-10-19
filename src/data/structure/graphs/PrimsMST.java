package data.structure.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.structure.heaps.BinaryMinHeap;

public class PrimsMST {
	public List<NewEdge<Integer>> primsMST(NewGraph<Integer> graph) {
		BinaryMinHeap<NewVertex<Integer>> minHeap = new BinaryMinHeap<NewVertex<Integer>>();
		Map<NewVertex<Integer>, NewEdge<Integer>> vertexToEdge = new HashMap<NewVertex<Integer>, NewEdge<Integer>>();
		List<NewEdge<Integer>> result = new ArrayList<NewEdge<Integer>>();

		for (NewVertex<Integer> v : graph.getAllVertex()) {
			minHeap.add(Integer.MAX_VALUE, v);
		}

		NewVertex<Integer> startVertex = graph.getAllVertex().iterator().next();
		minHeap.decrease(startVertex, 0);

		while (!minHeap.empty()) {
			NewVertex<Integer> current = minHeap.extractMin();

			NewEdge<Integer> spanningTreeEdge = vertexToEdge.get(current);
			if (null != spanningTreeEdge) {
				result.add(spanningTreeEdge);
			}

			for (NewEdge<Integer> edge : current.getEdges()) {
				NewVertex<Integer> adjacent = getVertexForEdge(current, edge);
				if (minHeap.containsData(adjacent)
						&& minHeap.getWeight(adjacent) > edge.getWeight()) {
					minHeap.decrease(adjacent, edge.getWeight());
					vertexToEdge.put(adjacent, edge);
				}
			}
		}

		return result;
	}

	private NewVertex<Integer> getVertexForEdge(NewVertex<Integer> v,
			NewEdge<Integer> e) {
		return e.getVertex(1).equals(v) ? e.getVertex(2) : e.getVertex(1);
	}

	public static void main(String[] args) {
		NewGraph<Integer> graph = new NewGraph<>(false);
		/*
		 * graph.addEdge(0, 1, 4); graph.addEdge(1, 2, 8); graph.addEdge(2, 3,
		 * 7); graph.addEdge(3, 4, 9); graph.addEdge(4, 5, 10); graph.addEdge(2,
		 * 5, 4); graph.addEdge(1, 7, 11); graph.addEdge(0, 7, 8);
		 * graph.addEdge(2, 8, 2); graph.addEdge(3, 5, 14); graph.addEdge(5, 6,
		 * 2); graph.addEdge(6, 8, 6); graph.addEdge(6, 7, 1); graph.addEdge(7,
		 * 8, 7);
		 */

		graph.addEdge(1, 2, 3);
		graph.addEdge(2, 3, 1);
		graph.addEdge(3, 1, 1);
		graph.addEdge(1, 4, 1);
		graph.addEdge(2, 4, 3);
		graph.addEdge(4, 5, 6);
		graph.addEdge(5, 6, 2);
		graph.addEdge(3, 5, 5);
		graph.addEdge(3, 6, 4);

		PrimsMST prims = new PrimsMST();
		Collection<NewEdge<Integer>> edges = prims.primsMST(graph);
		for (NewEdge<Integer> edge : edges) {
			System.out.println(edge);
		}
	}

}
