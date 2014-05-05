package graph;

/**
 * Az IGraphic a rajzolo ososztaly. Kulonbozo rajzolasokat tesz lehetove.
 * 
 * @author Seres
 *
 */
public abstract class Graphic {
	
	/**
	 * Alap rajzolo metodus.
	 */
	protected abstract void draw();
	
	/**
	 * Kijelolo metodus, pl.: mezore kattintashoz.
	 */
	public abstract void highlight();
	
	/**
	 * Highlight inverze.
	 */
	public abstract void deHighlight();
}
