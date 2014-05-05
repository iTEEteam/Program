package graph;

import javax.swing.JComponent;

/**
 * Az IGraphic a rajzolo ososztaly. Kulonbozo rajzolasokat tesz lehetove.
 * 
 * @author Seres
 *
 */
public abstract class Graphic extends JComponent {
	
	/**
	 * Az Eclipse ragaszkodott hozzá. :(
	 */
	private static final long serialVersionUID = 8170279701981113075L;

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
