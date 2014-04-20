package proto;
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
	

public ArrayList<Cell> getNeighbours() {
		return neighbours;
	}


	//	/**
//	 * Konstruktor.
//	 *
//	 * @param    cells
//	**/
//	
//	public void Cell(ArrayList<Cell> cells) {
//	
//	}
//	
	public void setNeighbours(ArrayList<Cell> neighbours_){
		
		neighbours = neighbours_;
	}
	
	
	/**
	 * Visszater egy bool ertekkel, ami megmondja, hogy az adott mezo Path-e vagy sem.
	**/
	public abstract boolean isPath();
}
