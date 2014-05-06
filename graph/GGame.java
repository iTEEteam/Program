package graph;

import java.awt.Graphics;
import java.util.ArrayList;

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
		
		genemies = new ArrayList<>();
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
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * A Game-ben letrehozott Enemy-khez tartozo GEnemy-k hozzaadasa.
	 * 
	 * @param ge A hozzaadando GEnemy referencia.
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
	 * A Game kirajzolasat vegzo fuggveny.
	 */
	@Override
	protected void draw() {
		// TODO Auto-generated method stub
		
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
		//TODO
	}
	

}
