package data.structure.graphs;

import java.util.List;

public abstract class Graph {
	public Graph() {
		System.out.println("***** The Graph: Super Constructor ***** ");
	}

	public abstract Graph buildGraph();

	public abstract List<?> getAdjacentVertices(int index);
}
