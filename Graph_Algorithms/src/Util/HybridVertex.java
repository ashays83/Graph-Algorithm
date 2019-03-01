/**
 * 
 */
package Util;

import java.util.Comparator;

/**
 * 
 * @author ashah
 * @author abalaji
 *
 */
public class HybridVertex implements Comparable<HybridVertex> {

	private Vertex vertex, parent;
	private int distance;

	/**
	 * @param parent
	 * @param distance
	 */
	public HybridVertex(Vertex vertex, Vertex parent, int distance) {
		this.setVertex(vertex);
		this.parent = parent;
		this.distance = distance;
	}

	/**
	 * @return the parent
	 */
	public Vertex getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(Vertex parent) {
		this.parent = parent;
	}

	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * @return the vertex
	 */
	public Vertex getVertex() {
		return vertex;
	}

	/**
	 * @param vertex
	 *            the vertex to set
	 */
	public void setVertex(Vertex vertex) {
		this.vertex = vertex;
	}

	@Override
	public int compareTo(HybridVertex o) {
		if (o instanceof HybridVertex) {
			HybridVertex hbv = (HybridVertex) o;
			return distance - hbv.getDistance();
		}
		return -1;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof HybridVertex) {
			HybridVertex hbv = (HybridVertex) o;
			if (vertex.equals(hbv.getVertex()))
				return true;
		}
		return false;
	}

	public String toString() {
		return vertex + "(" + parent + "," + distance + ")";
	}

}
