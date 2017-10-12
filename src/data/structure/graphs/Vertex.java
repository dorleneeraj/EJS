package data.structure.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vertex<T> {
	private final T value; // Because according to me, value of the vertex
							// shouldn't change
	private List<Vertex<T>> neighbours;
	private boolean isVisited;

	public Vertex(T value) {
		this.value = value;
		isVisited = false;
		neighbours = Collections.emptyList();
	}

	public List<Vertex<T>> getNeighbours() {
		// Return the copy of the List and not the actual List?
		List<Vertex<T>> returnList = Collections.emptyList();
		Collections.copy(returnList, this.neighbours);
		return returnList;
	}

	public void addEdge(Vertex<T> vertex) {
		if (neighbours.isEmpty()) {
			neighbours = new ArrayList<Vertex<T>>();
		}
		neighbours.add(vertex);
	}

	public void visit() {
		isVisited = true;
	}

	public boolean isVisited() {
		return this.isVisited;
	}

	public void addNeighbours(List<Vertex<T>> neighboursList) {
		for (Vertex<T> v : neighboursList) {
			if (!this.neighbours.contains(v)) {
				this.neighbours.add(v);
			}
		}
	}

	public void removeEdge(Vertex<T> v) {
		this.neighbours.remove(v);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Vertex other = (Vertex) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vertex [value=" + value + ", neighbours=" + neighbours
				+ ", isVisited=" + isVisited + "]";
	}

}
