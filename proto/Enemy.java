package proto;

import java.security.InvalidParameterException;

/**
 * A kulonbozo ellensegek (Hobbit, Dwarf, Elf, Human) absztrakt ososztalya.
 * @author Seres
**/
public abstract class Enemy implements IPathPlaceable {
	/**
	 * Az Enemy normal sebessege.
	**/
	private static final int speed = 10;
	
	/**
	 * A maximális életerõ értéke.
	 */
	private static final int maxHP = 100;

	/**
	 * Az ellenség belsõ idõmérõje. A setModSpeed változtathatja – jellemzõen negatív irányba, akadályokon.
	**/
	private int modSpeed;
	
	/**
	 * Életerejét tárolja ebben. Hurt függvényben csökkenti.
	**/
	protected int health;
	
	/**
	 * A Path cime, amin az Enemy eppen tartozkodik.
	**/
	protected Path myPath;
	
	/**
	 * A soron következõ path címe.
	 * Nem csinál semmit, csak itt hagytam.
	 */
	private Path nextPath;
	
	/**
	 * Ezen keresztül tudja módosítani a manát, amikor meghal, illetve ha elér a végzet hegyére módosítani a számlálót (Game.incSucceeded), hogy nõjön egyel.
	 */
	protected IGame igame;
	
	/**
	 * Konstruktor.
	 *
	 * @param 	game Az IGame interfész, amivel a Game-et eléri.
	 * @param 	p A létrehozás helye. Felesleges, mert úgysem úton hozzuk létre.
	**/
	public Enemy(IGame game, Path p) { //TODO Nem kell a p attribútum, nem úton hozzuk létre az Enemyt
		//ez azért nem kell, mert absztrakt osztályt nem példányosítunk, viszont így két bejegyzésünk is lenne a leszármazottai miatt
		//ProtoTester.addToObjectCatalog(this); 
		igame = game;
		health = maxHP;
		myPath = null; 
	}
	
	/**
	 * Sebzõdést megvalósító metódus, abstract, minden Enemy-típusban máshogy implementálódik.
	 *
	 * @param 	b A sebzõ Bullet objektum.
	**/
	public abstract void hurt(Bullet b);
	
	/**
	 * Mozog, a következõ path-ra lép, cellát vált.
	**/
	public void move() {
		
		if(modSpeed < speed) {
			modSpeed++;
		} else {
			Path nextPath = myPath.getNext();
			
			if(nextPath == null){
				igame.incSucceeded();
				eliminate();
			}else{		
				nextPath.registerIPathPlaceable(this);
			}
		}
		
	}
	 /**
	  * A modSpeed változót változtatja. Lassítani lehet vele.
	  * 
	  * @param 	msp A lassítás mértéke.
	  */
	public void setModSpeed(int msp) {
		modSpeed -= msp;
	}
	
	/**
	 * Az Enemyt törli az útjáról és a Game-bõl.
	 */
	public void eliminate() {
		igame.removeEnemyIn(this);
		
		myPath.deleteEnemy(this);
	}
	
	/**
	 * Az Enemyt regisztrálja a paraméterben átadott útra. Nem csak a move függvényen keresztül mûködik, külön is hívható.
	 * 
	 * @param 	p Az út, ahova regisztrálni akarjuk az Enemyt.
	 */
	public void registerPath(Path p) {
		
		if(myPath != null) {
			myPath.deleteEnemy(this);
		}
		
		if(p != null) {
			p.registerEnemy(this);
		} else {
			throw new InvalidParameterException();
		}
	}
	
	/**
	 * Új életerõt állít be.
	 * @param hp Az új életerõ értéke.
	 */
	public void setHealth(int hp) {
		health = hp;		
	}
	
	public abstract void cut();
}
