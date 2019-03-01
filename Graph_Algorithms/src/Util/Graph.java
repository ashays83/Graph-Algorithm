/**
 * 
 */
package Util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * 
 * @author ashah 
 * @author abalaji 
 *
 */
public class Graph {

	private int vertexCount, edgeCount; 
	private TreeSet<Vertex> vertices;
	/**
	 * @return the vertices
	 */
	public TreeSet<Vertex> getVertices() {
		return vertices;
	}

	/**
	 * @param vertices the vertices to set
	 */
	public void setVertices(TreeSet<Vertex> vertices) {
		this.vertices = vertices;
	}

	private LinkedList<Edge>[] adjList;
	
	@SuppressWarnings("unchecked")
	public Graph(int vertexCount, int edgeCount) {
		this.vertexCount = vertexCount;
		this.setEdgeCount(edgeCount);
		vertices = new TreeSet<Vertex>();
		
		adjList = new LinkedList[26];
		
		for(int i = 0; i < 26; i++) {
			adjList[i] = new LinkedList<Edge>();
		}
	}

	public void addEdge(Edge e) {
		Vertex v1 = e.getA();
		Vertex v2 = e.getB();
		int a = v1.getName() - 65;
		int b = v2.getName() - 65;

		adjList[a].addLast(e);
		adjList[b].addLast(new Edge(e.getB(), e.getA(), e.getWeight()));
		vertices.add(v1);
		vertices.add(v2);
		vertexCount = vertices.size();
	}
	
	public LinkedList<Edge>[] getGraph() {
		return adjList;
	}

	/**
	 * Prints the adjacency list for the graph 
	 */
	public void print() {
		for(int i = 0; i < 26; i++)
        {
			char v = (char) (i + 65);
            System.out.print("Adjacency list of vertex " + v);
            for(Edge current: adjList[i]){
                System.out.print(" -> "+ current.toString());
            }
            System.out.println("\n");
        }
	}

	/**
	 * @return the edgeCount
	 */
	public int getEdgeCount() {
		return edgeCount;
	}

	/**
	 * @param edgeCount the edgeCount to set
	 */
	public void setEdgeCount(int edgeCount) {
		this.edgeCount = edgeCount;
	}

	/**
	 * @return the vertexCount
	 */
	public int getVertexCount() {
		return vertexCount;
	}

	/**
	 * @param vertexCount the vertexCount to set
	 */
	public void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}

	public Edge getEdge(Vertex vertex, Vertex parent) {
		int idx = vertex.getName() - 65;
		LinkedList<Edge> edges = adjList[idx];
		for (Edge e : edges) {
			if (e.getB().getName() == parent.getName()) {
				return e;
			} 
		}
		return null;
	} 
	
	/**
	public Vertex getVertex(String name) {
		 Iterator<Vertex> iterator = vertices.iterator();
	        while(iterator.hasNext()) {
	            Vertex v = iterator.next();
	            if(v.getName().equals(name))             
	                return v;
	        }

	        return null;     
	}
	*/
}
