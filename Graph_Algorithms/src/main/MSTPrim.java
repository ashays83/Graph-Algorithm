/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.Iterator;
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
public class MSTPrim {

	private Graph graph; 
	private LinkedList<Edge>[] adjList;
	
	public MSTPrim(Graph g) {
		graph = g;
		adjList = g.getGraph();
		
	}

	public ArrayList<Edge> calculateMST() {
		Vertex v1 = null; 
		for (int i = 0; i < graph.getVertexCount(); i++) {
			if (!adjList[i].isEmpty()) {
				v1 = adjList[i].getFirst().getA();
				break;
			}
		}
		
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
		
		ArrayList<Edge> MST = new ArrayList<Edge>(graph.getVertexCount() - 1);
		
		// building our MST! 
		while (!heap.isEmpty()) {
			HybridVertex hbv = heap.poll();
			if (hbv.getParent() != null) {
				Edge temp = graph.getEdge(hbv.getParent(), hbv.getVertex());
				MST.add(temp);
			}
			n--;
			path.add(hbv);
			Vertex v = hbv.getVertex();
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
				if (adjEdges.get(i).getWeight() < tmp.getDistance()) {
					// updating tmp node in heap with the lower distance and parent 
					tmp.setDistance(adjEdges.get(i).getWeight());
					heap.remove(tmp);
					tmp.setParent(v);
					heap.add(tmp);
					
				}
			}
		}
		
		return MST;
	}
}
