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
	
	protected IView iview;

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
		
		iview = null;
	}
	 
	public void setNeighbours(ArrayList<Cell> neighbours_){
		
		neighbours = neighbours_;
	}
	
	
	/**
	 * Visszater egy bool ertekkel, ami megmondja, hogy az adott mezo Path-e vagy sem.
	**/
	public abstract boolean isPath();
	
	/**
	 * Az ertesiteseket fogado objektumot allitja be. 
	 * 
	 * @param iview A grafikus jellegu esemenyek eseten ertesitendo objektum.
	 */
	public void setIView(IView iview) {
		this.iview = iview;
	}
	
	/**
	 * Az ertesiteseket fogado objektumot adja vissza. 
	 * 
	 * @return A grafikus jellegu esemenyek eseten ertesitendo objektum.
	 */
	public IView getIView() {
		return iview;
	}
}
