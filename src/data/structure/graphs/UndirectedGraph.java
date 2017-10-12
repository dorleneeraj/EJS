package data.structure.graphs;

import java.util.List;

public class UndirectedGraph<T> extends MyGraph<T> {

	public UndirectedGraph(int size, List<Vertex<T>> initialNodes) {
		super(size);
		if ((null != initialNodes) && (!initialNodes.isEmpty())) {
			for (Vertex<T> v : initialNodes) {
				this.vertices.add(v);
			}
		}
	}

	public void addEdge(Vertex<T> v1, Vertex<T> v2) {
		if (this.vertices.contains(v1) && this.vertices.contains(v2)) {
			v1.addEdge(v2);
			v2.addEdge(v1);
		}
	}
}
