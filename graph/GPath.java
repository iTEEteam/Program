package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * A GPath felel a Path osztaly megjeleniteseert, kirajzolasaert. Minden Path-hoz tartozik egy, es mindegyikhez
 * egy Path tartozik.
 * 
 * @author Seres
 *
 */
public class GPath extends GCell {

	/**
	 * Az Eclipse ragaszkodott hozza. :(
	 */
	private static final long serialVersionUID = 6584228015160996554L;
	
	/**
	 * Referencia arra az utra, amelyikhez tartozik. Ezen keresztul kerheti le a szukseges adatokat
	 * a modellbol.
	 */
	private Path path;
	
	/**
	 * Az path enemies-hez tartozó GEnemy-k listaja.
	 */
	ArrayList<GEnemy> genemies;
	
	/**
	 * Konstruktor.
	 * 
	 * @param path A modellbeli kapcsolatot biztosito Path.
	 */
	public GPath(Path path) {
		super();
		this.path = path;
		
		genemies = new ArrayList<>();
	}
	
	/**
	 * Getter path-hoz.
	 * 
	 * @return A tarolt Path.
	 */
	public Path getPath() {
		return path;
	}
	
	/**
	 * A Humanhoz tartozo rajzolofuggveny.
	 */
	public void drawGHuman() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * A Dwarfhoz tartozo rajzolofuggveny.
	 */
	public void drawGDwarf() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Az Elfhez tartozo rajzolofuggveny.
	 */
	public void drawGElf() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * A Hobbithoz tartozo rajzolofuggveny.
	 */
	public void drawGHobbit() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * A GPath-t ertesiti a grafikus jellegu valtozasokrol.
	 */
	@Override
	public void gNotify() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * A Path-re lepo ellenseghez tartozo GEnemy-t tarolja el.
	 * 
	 * @param ge A Hozzaadando GEnemy.
	 */
	@Override
	public void addGEnemy(GEnemy ge) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Egy adott Enemy-hez tartozo GEnemy objektumot adja vissza.
	 * 
	 * @param e Az Enemy, amelyiknek a GEnemy-jet kerdezzuk le.
	 * @return Az Enemy-hez tartozo GEnemy.
	 */
	@Override
	public GEnemy getGEnemy(Enemy e) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Egy adott Enemy-hez tartozo GEnemy objektumot torol.
	 * 
	 * @param e A torlendo GEnemyhez tartozo Enemy objektum.
	 */
	@Override
	public void deleteGEnemy(Enemy e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Az Path kirajzolasat vegzo fuggveny.
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
			
			g.setColor(Color.GRAY);
			g.fillRect(5, 5, getWidth() - 5, getHeight() - 5);
		} else {
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
	}
	
	/**
	 * Mindig igazzal ter vissza.
	 * 
	 * @return Igaz.
	 */
	@Override
	public boolean isGPath() {
		return true;
	}
}
