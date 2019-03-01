/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import Util.Edge;
import Util.Graph;
import Util.HybridVertex;
import Util.Vertex;

/**
 * 
 * 
 * @author ashah 
 * @author abalaji 
 *
 */
public class ShortestPathDijkstra {

	private Graph graph; 
	private Vertex source; 
	private ArrayList<HybridVertex> path; 
	
	public ShortestPathDijkstra(Graph g, Vertex src) {
		this.graph = g;
		this.source = src;
		path = new ArrayList<HybridVertex>();
	}

	public ArrayList<HybridVertex> calculateShortestPath() {
		Vertex v1 = source; 
		LinkedList<Edge>[] adjList = graph.getGraph();
		
		// add all vertices to heap, update distances to infinity (9999) 
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		vertices.addAll(graph.getVertices());
		PriorityQueue<HybridVertex> heap = new PriorityQueue<HybridVertex>();
		for (int i = 0; i < graph.getVertexCount(); i++) {
			HybridVertex hbv = new HybridVertex(vertices.get(i), null, 9999);
			heap.add(hbv);
		}
		
		// set vertex 1 distance to 0 
		HybridVertex hbv1 = new HybridVertex(v1, null, 0);
		heap.remove(new HybridVertex(v1, null, 9999));
		heap.add(hbv1);
		
		ArrayList<HybridVertex> path = new ArrayList<HybridVertex>();
		int n = graph.getVertexCount();
		
		// computing shortest path 
		while (!heap.isEmpty()) {
			HybridVertex u = heap.poll();
			n--;
			path.add(u);
			Vertex v = u.getVertex();
			int v_index = v.getName() - 65;
			LinkedList<Edge> adjEdges = adjList[v_index];
			HybridVertex[] elems = new HybridVertex[n];
			elems = heap.toArray(elems);
			
			for (int i = 0; i< adjEdges.size(); i++) {
				Vertex z = adjEdges.get(i).getB();
				HybridVertex tmp = new HybridVertex(z, null, 0);
				
				// getting the z node in heap to compare distances 
				for (int j = 0; j < elems.length; j++) {
					if (elems[j].getVertex().getName() == tmp.getVertex().getName()) {
						tmp = elems[j];
						break;
					}
				}
				
				// comparing distances/weights 
				if ((adjEdges.get(i).getWeight() + u.getDistance()) < tmp.getDistance()) {
					// updating tmp node in heap with the lower distance and parent 
					tmp.setDistance(adjEdges.get(i).getWeight() + u.getDistance());
					heap.remove(tmp);
					tmp.setParent(v);
					heap.add(tmp);
				}
			}
		}
		
		return path;
	}
	
	/**
	 * @return the graph
	 */
	public Graph getGraph() {
		return graph;
	}

	/**
	 * @param graph the graph to set
	 */
	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	/**
	 * @return the source
	 */
	public Vertex getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(Vertex source) {
		this.source = source;
	}

	/**
	 * @return the path
	 */
	public ArrayList<HybridVertex> getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(ArrayList<HybridVertex> path) {
		this.path = path;
	}

}
