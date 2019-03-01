/**
 * 
 */
package Util;

import java.util.Comparator;

/**
 * @author anuba
 *
 */
public class SortByAncestors implements Comparator<HybridVertex> {

	@Override
	public int compare(HybridVertex v1, HybridVertex v2) {
		/** sort by parent 
		if (v1.getParent() == null) {
			return -1; // v1 is smaller - so no swap 
		} else if (v2.getParent() == null) {
			return 1; // v2 is smaller 
		} else {
			return v1.getVertex().getName() - v2.getParent().getName();
		}
		*/
		if (v1.getParent() == null) {
			return -1;
		} else if (v2.getParent() == null) { 
			return 1;
		} else if (v1.getVertex().getName() == v2.getParent().getName()) {
			return -1; // don't swap them (v1 is parent)  
		} else if (v2.getVertex().getName() == v1.getParent().getName()) {
			return 1; // v2 is parent of v1 (v2 goes first)  
		} else {
			System.out.println("v1: " + v1 + "\tv2: " + v2);
			return v1.getVertex().getName() - v2.getVertex().getName();
		}
	}

}
