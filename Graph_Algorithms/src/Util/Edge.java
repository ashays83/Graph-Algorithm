/**
 * 
 */
package Util;

/**
 * Edge class
 * 
 * @author ashah 
 * @author abalaji 
 *
 */
public class Edge {

	private Vertex a, b = null; 
	private int weight = 0; 
	
	public Edge(Vertex a, Vertex b, int weight) {
		// TODO Auto-generated constructor stub
		this.a = a;
		this.b = b; 
		this.weight = weight;
	}

	/**
	 * @return the a
	 */
	public Vertex getA() {
		return a;
	}

	/**
	 * @param a the a to set
	 */
	public void setA(Vertex a) {
		this.a = a;
	}

	/**
	 * @return the b
	 */
	public Vertex getB() {
		return b;
	}

	/**
	 * @param b the b to set
	 */
	public void setB(Vertex b) {
		this.b = b;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String toString() {
		return "[" + a.toString() + "," + b.toString() + "] w=" + weight;
	}
}
