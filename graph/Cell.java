package graph;
import java.util.ArrayList;


public abstract class Cell {
	/**
	 * A cella 4 szomszedja.
	**/
	/*
		 * 0.: up
		 * 1.: right
		 * 2.: down
		 * 3.: left
		 */
	protected ArrayList<Cell> neighbours;
	
	protected IGame igame;
	
public ArrayList<Cell> getNeighbours() {
		return neighbours;
	}


		/**
	 * Konstruktor.
	 *
	 * @param    cells
	*/
	
//
//public Cell(ArrayList<Cell> cells) {
//}
 
	public Cell(IGame _igame) {
		igame = _igame;
	}
	 
	public void setNeighbours(ArrayList<Cell> neighbours_){
		
		neighbours = neighbours_;
	}
	
	
	/**
	 * Visszater egy bool ertekkel, ami megmondja, hogy az adott mezo Path-e vagy sem.
	**/
	public abstract boolean isPath();
}
