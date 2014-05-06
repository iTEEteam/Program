package graph;

import java.awt.Dimension;

public abstract class GCell extends Graphic implements IView {
	
	/**
	 * Az Eclipse ragaszkodott hozza. :(
	 */
	private static final long serialVersionUID = 3086333592669475359L;
	/**
	 * A kiemeltseg allapotat tarolo valtozo.
	 */
	protected boolean highlighted = false;
	
	/**
	 * A cellak merete.
	 */	
	public static Dimension cellSize = new Dimension(100, 100);
	
	/**
	 * Konstruktor.
	 */
	public GCell() {
		this.setPreferredSize(cellSize);
		this.setMinimumSize(cellSize);
	}
	
	/**
	 * GetType :)
	 * 
	 * @return Igaz, ha a GCell GPath, egyébként hamis.
	 */
	public abstract boolean isGPath();
}
