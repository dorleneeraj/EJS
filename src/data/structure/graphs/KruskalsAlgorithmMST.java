package data.structure.graphs;

import java.util.ArrayList;
import java.util.List;

import data.structure.disjointset.*;

public class KruskalsAlgorithmMST {
	public static void main(String[] args) {
		// Build A graph
		List<Vertex<Node>> vertices = new ArrayList<Vertex<Node>>();
		vertices.add(new Vertex<Node>(new Node('a', 0)));
		vertices.add(new Vertex<Node>(new Node('b', 0)));
		vertices.add(new Vertex<Node>(new Node('c', 0)));
		vertices.add(new Vertex<Node>(new Node('d', 0)));
		vertices.add(new Vertex<Node>(new Node('e', 0)));
		vertices.add(new Vertex<Node>(new Node('f', 0)));
		vertices.add(new Vertex<Node>(new Node('g', 0)));
		vertices.add(new Vertex<Node>(new Node('h', 0)));
		vertices.add(new Vertex<Node>(new Node('i', 0)));

		UndirectedGraph<Node> myGraph = new UndirectedGraph<Node>(9, vertices);

		// add weighted edges.

	}
}
