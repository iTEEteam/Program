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
		this.setSize(cellSize);
	}
	
	/**
	 * Az cella kijeloleset vegzo fuggveny.
	 */
	@Override
	public void highlight() {
		highlighted = true;
	}

	/**
	 * A highlight inverze.
	 */
	@Override
	public void deHighlight() {
		highlighted = false;
	}
	
	/**
	 * Ertesiti a cellat a valtozasrol.
	 */
	@Override
	public void gNotify() {
		this.draw();
	}
	
	/**
	 * Az GCell kirajzolasat vegzo fuggveny.
	 */
	@Override
	protected void draw() {
		this.repaint();
	}
	
	/**
	 * GetType :)
	 * 
	 * @return Igaz, ha a GCell GPath, egyébként hamis.
	 */
	public abstract boolean isGPath();
}
