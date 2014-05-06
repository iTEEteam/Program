package graph;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A GField felel a Field osztaly megjeleniteseert, kirajzolasaert. Minden Field-hez tartozik egy, es mindegyikhez
 * egy Field tartozik.
 * 
 * @author Seres
 *
 */
public class GField extends GCell {

	/**
	 * Az Eclipse ragaszkodott hozza. :(
	 */
	private static final long serialVersionUID = 8151525822402323058L;
	
	/**
	 * Referencia arra mezore, amelyikhez tartozik. Ezen keresztul kerheti le a szukseges adatokat
	 * a modellbol.
	 */
	private Field field;
	
	/**
	 * Konstruktor. 
	 * 
	 * @param field A modellbeli kapcsolatot biztosito Field.
	 */ 
	public GField(Field field) {
		super();
		this.field = field;
	}
	
	/**
	 * Getter fieldhez.
	 * 
	 * @return A tarolt Field referenciaja.
	 */
	public Field getField() {
		return field;
	}
	
	/**
	 * A GField-et ertesiti a grafikus jellegu valtozasokrol.
	 */
	@Override
	public void gNotify() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Ures metodus. A modell kialakitasa miatt muszaj megvalositani, de szukseg nincs ra.
	 */
	@Override
	public void addGEnemy(GEnemy ge) {}
	
	/**
	 * Ures metodus. A modell kialakitasa miatt muszaj megvalositani, de szukseg nincs ra.
	 */
	@Override
	public GEnemy getGEnemy(Enemy e) {
		return null;
	}
	
	/**
	 * Ures metodus. A modell kialakitasa miatt muszaj megvalositani, de szukseg nincs ra.
	 */
	@Override
	public void deleteGEnemy(Enemy e) {}
	
	/**
	 * Az Field kirajzolasat vegzo fuggveny.
	 */
	@Override
	protected void draw() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Az ut kijeloleset vegzo fuggveny.
	 */
	@Override
	public void highlight() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * A highlight inverze.
	 */
	@Override
	public void deHighlight() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Ezzel a fuggvennyel valositjuk meg a konkret rajzolast.
	 */
	@Override
	public void paint(Graphics g) {
		if(highlighted) {
			g.setColor(Color.RED);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			g.setColor(Color.GREEN);
			g.fillRect(5, 5, getWidth() - 5, getHeight() - 5);
		} else {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
	}
	
	/**
	 * Mindig hamissal ter vissza.
	 * 
	 * @return Hamis.
	 */
	@Override
	public boolean isGPath() {
		return false;
	}

}
