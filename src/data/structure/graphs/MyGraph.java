package data.structure.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyGraph<T> {
	protected List<Vertex<T>> vertices = Collections.emptyList();
	protected int size;

	public MyGraph(int size) {
		this.size = size;
		vertices = new ArrayList<Vertex<T>>(); // The list is initialized here.
	}

	public List<Vertex<T>> getVertices() {
		List<Vertex<T>> returnList = Collections.emptyList();
		Collections.copy(returnList, this.vertices);
		return returnList;
	}
}
