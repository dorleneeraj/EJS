package data.structure.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KruskalsAlgorithmMST {

	public class EdgeComparator implements Comparator<NewEdge<Integer>> {

		@Override
		public int compare(NewEdge<Integer> edge1, NewEdge<Integer> edge2) {
			if (edge1.getWeight() <= edge2.getWeight()) {
				return -1;
			} else {
				return 1;
			}
		}

	}

	public List<NewEdge<Integer>> getMST(NewGraph<Integer> graph) {
		List<NewEdge<Integer>> allEdges = graph.getAllEdges();
		Collections.sort(allEdges, new EdgeComparator());
		DisjointSetCompressed dSC = new DisjointSetCompressed();
		dSC.initialize(graph.getAllVertex().size());
		List<NewEdge<Integer>> resultEdge = new ArrayList<NewEdge<Integer>>();
		for (NewEdge<Integer> edge : allEdges) {
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
		NewGraph<Integer> graph = new NewGraph<Integer>(false);
		graph.addEdge(1, 2, 4);
		graph.addEdge(1, 3, 1);
		graph.addEdge(2, 5, 1);
		graph.addEdge(2, 6, 3);
		graph.addEdge(2, 4, 2);
		graph.addEdge(6, 5, 2);
		graph.addEdge(6, 4, 3);
		graph.addEdge(4, 7, 2);
		graph.addEdge(3, 4, 5);
		graph.addEdge(3, 7, 8);
		System.out.println(graph);

		KruskalsAlgorithmMST mst = new KruskalsAlgorithmMST();
		List<NewEdge<Integer>> result = mst.getMST(graph);
		for (NewEdge<Integer> edge : result) {
			System.out.println(edge.getVertex(1) + " " + edge.getVertex(2));
		}
	}
}
