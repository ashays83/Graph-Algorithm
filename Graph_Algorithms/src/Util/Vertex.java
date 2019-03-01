package Util;

/**
 * 
 * 
 * @author ashah
 * @author abalaji
 *
 */
public class Vertex implements Comparable<Vertex> {

	private char name;
	
	public Vertex(String name) {
		this.name = name.charAt(0);
	}

	/**
	 * @return the name
	 */
	public char getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(char name) {
		this.name = name;
	}

	public String toString() {
		return name + "";
	}

	@Override
	public int compareTo(Vertex v) {
		return name - v.getName();
	}
	
	@Override 
	public boolean equals(Object o) {
		if (o instanceof Vertex) {
			Vertex temp = (Vertex) o;
			if (name == temp.getName()) {
				return true;
			}
		}
		return false;
	}
	
}
