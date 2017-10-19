package data.structure.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewGraph<T> {
	private List<NewEdge<T>> allEdges = new ArrayList<NewEdge<T>>();
	private Map<Long, NewVertex<T>> allVertex = new HashMap<Long, NewVertex<T>>();
	private boolean isDirected = false;

	public NewGraph(boolean isDirected) {
		this.isDirected = isDirected;
	}

	public NewVertex<T> addSingleVertex(long id) {
		if (allVertex.containsKey(id)) {
			return allVertex.get(id);
		}
		NewVertex<T> v = new NewVertex<T>(id);
		allVertex.put(id, v);
		return v;
	}

	public NewVertex<T> getVertex(long id) {
		return allVertex.get(id);
	}

	public void addEdge(long id1, long id2) {
		addEdge(id1, id2, 0);
	}

	public void addEdge(long id1, long id2, int weight) {

		NewVertex<T> v1 = null;
		if (allVertex.containsKey(id1)) {
			v1 = allVertex.get(id1);
		} else {
			v1 = new NewVertex<T>(id1);
			this.allVertex.put(id1, v1);
		}

		NewVertex<T> v2 = null;
		if (allVertex.containsKey(v2)) {
			v2 = allVertex.get(v2);
		} else {
			v2 = new NewVertex<T>(id2);
			allVertex.put(id2, v2);
		}

		NewEdge<T> edge = new NewEdge<T>(v1, v2);
		edge.setWeight(weight);
		allEdges.add(edge);
		v1.addAdjacentVertex(edge, v2);
		if (!isDirected) {
			v2.addAdjacentVertex(edge, v1);
		}
	}

	public List<NewEdge<T>> getAllEdges() {
		return allEdges;
	}

	public Collection<NewVertex<T>> getAllVertex() {
		return allVertex.values();
	}

	public void setDataForVertex(long id, T data) {
		if (allVertex.containsKey(id)) {
			NewVertex<T> vertex = allVertex.get(id);
			vertex.setData(data);
		}
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (NewEdge<T> edge : getAllEdges()) {
			buffer.append(edge.getVertex(1) + " " + edge.getVertex(2) + " "
					+ edge.getWeight());
			buffer.append("\n");
		}
		return buffer.toString();
	}

}

class NewVertex<T> {
	long id;
	private T data;
	private List<NewEdge<T>> edges = new ArrayList<NewEdge<T>>();
	private List<NewVertex<T>> adjacentVertex = new ArrayList<NewVertex<T>>();

	public NewVertex(long id) {
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

	public void addAdjacentVertex(NewEdge<T> edge, NewVertex<T> vertex) {
		edges.add(edge);
		adjacentVertex.add(vertex);
	}

	public List<NewVertex<T>> getAdjacentVertices() {
		return adjacentVertex;
	}

	public List<NewEdge<T>> getEdges() {
		return edges;
	}

	public int getDegree() {
		return edges.size();
	}

	@Override
	public String toString() {
		return "NewVertex [id=" + id + ", data=" + data + "]";
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
		NewVertex<?> other = (NewVertex<?>) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

class NewEdge<T> {
	private boolean isDirected = false;
	private NewVertex<T> vertex1;
	private NewVertex<T> vertex2;
	private int weight;

	public NewEdge(NewVertex<T> v1, NewVertex<T> v2) {
		this.vertex1 = v1;
		this.vertex2 = v2;
	}

	public NewVertex<T> getVertex(int which) {
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
		NewEdge<?> other = (NewEdge<?>) obj;
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
		return "NewEdge [vertex1=" + vertex1 + ", vertex2=" + vertex2
				+ ", weight=" + weight + "]";
	}

}