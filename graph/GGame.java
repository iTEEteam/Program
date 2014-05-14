package graph;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A GGame felel a Game osztaly megjeleniteseert. Elsosorban a felso savert, vagyis a Game azon attributumainak
 * megjeleneseert, amiket a jatekosnak celszeru latnia: mana, bejutott ellensegek szama, palya
 * szintje stb.
 * 
 * @author Seres
 *
 */
public class GGame extends Graphic implements IView {

	/**
	 * Az Eclipse ragaszkodott hozza. :(
	 */
	private static final long serialVersionUID = 4315392664606420956L;
	
	/**
	 * A Game referencia, ami ahhoz kell, hogy meg tudja jeleniteni az adatokat.
	 */
	private Game game;
	
	/**
	 * A Game enemiesIn es enemiesOut-ban levo Enemy-khez tartozo megjelenitok listaja.
	 */
	private ArrayList<GEnemy> genemies;
	
	public GGame(Game game) {
		this.game = game;
		
		genemies = new ArrayList<GEnemy>();
	}
	
	/**
	 * Getter game-hez.
	 * @return A tartolt Game objektum.
	 */
	public Game getGame() {
		return game;
	}
	
	/**
	 * A GField-et ertesiti a grafikus jellegu valtozasokrol.
	 */
	@Override
	public void gNotify() {
		this.draw();
	}
	
	/**
	 * A Game-ben letrehozott Enemy-khez tartozo GEnemy-k hozzaadasa.
	 * 
	 * @param ge A hozzaadando GEnemy referencia.
	 */
	@Override
	public void addGEnemy(GEnemy ge) {
		genemies.add(ge);
	}
	
	/**
	 * Egy adott Enemy-hez tartozo GEnemy objektumot adja vissza.
	 * 
	 * @param e Az Enemy, amelyiknek a GEnemy-jet kerdezzuk le.
	 * @return Az Enemy-hez tartozo GEnemy.
	 */
	@Override
	public GEnemy getGEnemy(Enemy e) {
		GEnemy genemy = null;
		
		for(GEnemy ge : genemies) {
			if(ge.getEnemy().equals(e)) {
				genemy = ge;
			}
		}
		return genemy;
	}
	
	/**
	 * Egy adott Enemy-hez tartozo GEnemy objektumot torol.
	 * 
	 * @param e A torlendo GEnemyhez tartozo Enemy objektum.
	 */
	@Override
	public void deleteGEnemy(Enemy e) {
		
		Iterator<GEnemy> iter = genemies.iterator();
		
		while(iter.hasNext()) {
			GEnemy genemy = iter.next();
			
			if(genemy.getEnemy().equals(e)) {
				iter.remove();
			}
		}
	}
	
	/**
	 * A Game kirajzolasat vegzo fuggveny.
	 */
	@Override
	protected void draw() {
		this.repaint();		
	}
	
	/**
	 * Ures metodus. A modell kialakitasa miatt muszaj megvalositani, de szukseg nincs ra.
	 */
	@Override
	public void highlight() {}
	
	/**
	 * Ures metodus. A modell kialakitasa miatt muszaj megvalositani, de szukseg nincs ra.
	 */
	@Override
	public void deHighlight() {}
	
	/**
	 * Ezzel a fuggvennyel valositjuk meg a konkret rajzolast.
	 */
	@Override
	public void paint(Graphics g) {
		int mana = game.getMana();
		int enemies = game.getSucceededE();
		int wave = game.getWaveCounter();
		
		Font font = getFont().deriveFont(Font.PLAIN, this.getSize().height - 15);
		g.setFont(font);
		g.drawString("Mana: " + mana + "   Bejutott: " + enemies + "   Hullam: " + wave + "/5", 10, 35);
	}
	

}
