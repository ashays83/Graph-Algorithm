/**catf-8"
 * 
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

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
public class SimplifyGraph {

	public static void main(String[] args) {

		// parse file index to open each input file and parse each into a graph
		File fileIndex = new File("fileIndex.txt");
		Scanner scan = null;
		try {
			scan = new Scanner(fileIndex);
			
			File graphFile = null;
			while (scan.hasNextLine()) {
				// opening and parsing one file at a time
				String fileName = "dataFiles/" + scan.nextLine();
				System.out.println("----------------------------------------------\n\tFile: " + fileName);
				graphFile = new File(fileName);
				Scanner graphScan = new Scanner(graphFile);
				Graph g = null;
				MSTPrim mst = null;
				ShortestPathDijkstra sp = null;

				// 1. first line of graph file
				if (graphScan.hasNextLine()) {
					int n = Integer.parseInt(graphScan.next()); // vertex count
					int m = Integer.parseInt(graphScan.next()); // edge count
					graphScan.next(); // skip the "U" token
					g = new Graph(n, m);
				}

				Vertex a = null;
				// 2. read and build graph - adding edges
				while (graphScan.hasNextLine()) {
					// parse each line - could be edge or just the start vertex
					a = new Vertex(graphScan.next());

					if (graphScan.hasNext()) { // edge line
						Vertex b = new Vertex(graphScan.next());
						int weight = Integer.parseInt(graphScan.next());
						Edge e = new Edge(a, b, weight);
						g.addEdge(e);

						if (!graphScan.hasNext()) {
							// MST without the last line (aka source vertex) in
							// file
							mst = new MSTPrim(g);
							break;
						}
					} else { // last line with source vertex
						// 'a' is the start vertex as we just parsed the last
						// line
						sp = new ShortestPathDijkstra(g, a);

						// MST with source vertex line in file
						mst = new MSTPrim(g);
						break;
					}
				}
				graphScan.close(); // free file scanner for current graph
				
				long start = System.nanoTime();
				// calculate and display the shortest path and MST
				ArrayList<Edge> MST = mst.calculateMST();
				long mstTime = System.nanoTime();
				System.out.println(
						"----------------------------------------------\n--------------------------------\nPrim's Minimum Spanning Tree\n--------------------------------");
				int cost = 0;
				for (int i = 0; i < MST.size(); i++) {
					cost += MST.get(i).getWeight();
					System.out.print(MST.get(i) + "\n");
				}
				System.out.println("Final cost for MST: " + cost);
				System.out.println("Runtime: " + (mstTime - start) + " ns\n");
				// Shortest Path!
				start = System.nanoTime();
				ArrayList<HybridVertex> path = sp.calculateShortestPath();
				long shortestPathTime = System.nanoTime();
				Stack<Vertex> parents = new Stack<Vertex>();
				for (int i = 0; i < path.size(); i++) {
					HybridVertex hbv = path.get(i);
					int pathCost = hbv.getDistance();
					if (i == 0) {
						System.out.println("--------------------------------\nShortest path from " + hbv.getVertex() + "\n--------------------------------");
						continue;
					}
					System.out.print(hbv.getVertex().getName() + ": ");
					parents.push(hbv.getVertex());
					while (hbv.getParent() != null) {
						parents.push(hbv.getParent());
						int parentIdx = path.indexOf(new HybridVertex(hbv.getParent(), null, 0));
						hbv = path.get(parentIdx);
					}
					while (!parents.isEmpty()) {
						System.out.print(parents.pop());
						if (parents.isEmpty())
							break;
						System.out.print(" -> ");
					}
					System.out.println(" (cost: " + pathCost + ")\n");
				}
				System.out.println("Runtime: " + (shortestPathTime - start) + " ns\n"); 
			} // end of file index file

			scan.close(); // free scanner for file index

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
