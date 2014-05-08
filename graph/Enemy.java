package graph;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * A kulonbozo ellensegek (Hobbit, Dwarf, Elf, Human) absztrakt ososztalya.
 * @author Seres
**/
public abstract class Enemy implements IPathPlaceable {
	/**
	 * Az Enemy normal sebessege.
	**/
	private static final int speed = 0;
	
	/**
	 * A maximalis eletero erteke.
	 */
	private static final int maxHP = 100;

	/**
	 * Az ellenseg belso idomeroje. A setModSpeed valtoztathatja, jellemzoen negatav iranyba, akadalyokon.
	**/
	private int modSpeed = 0;
	
	/**
	 * Eleterejet tarolja ebben. Hurt fuggvenyben csokkenti.
	**/
	protected int health;
	
	/**
	 * A Path cime, amin az Enemy eppen tartozkodik.
	**/
	protected Path myPath;
	
	/**
	 * A soron kovetkezo path cime.
	 * Nem csinal semmit, csak itt hagytam, mert benne van a dokumentacioban.
	 */
	@SuppressWarnings("unused")
	private Path nextPath;
	
	/**
	 * Ezen keresztul tudja modositani a manat, amikor meghal, illetve ha eler a vegzet hegyere, modosotani a szamlalot (Game.incSucceeded), hogy nojon egyel.
	 */
	protected IGame igame;
	
	private GEnemy genemy;
	
	private Path forcedNextPath = null;
	
	public void setForcedNextPath(int direction) {
		forcedNextPath = null;
		ArrayList<Cell> neighbours = myPath.getNeighbours();
		
        switch(direction){
        case 0:
        	if(neighbours.get(0).isPath()){
        		forcedNextPath = (Path) neighbours.get(0);
        	}
        	break;
        case 1:
        	if(neighbours.get(1).isPath()){
        		forcedNextPath = (Path) neighbours.get(1);
        	}
        	break;
        case 2:
        	if(neighbours.get(2).isPath()){
        		forcedNextPath = (Path) neighbours.get(2);
        	}
        	break;
        case 3:
        	if(neighbours.get(3).isPath()){
        		forcedNextPath = (Path) neighbours.get(3);
        	}
        	break;
        default:
        	break;
        	
        }
	
	
	}

	/**
	 * Konstruktor.
	 *
	 * @param 	game Az IGame interfesz, amivel a Game-et eleri.
	**/
	public Enemy(IGame game) { 
		//ez azert nem kell, mert absztrakt osztalyt nem peldanyositunk, viszont igy ket bejegyzesunk is lenne a leszarmazottai miatt
		//ProtoTester.addToObjectCatalog(this); 
		igame = game;
		health = maxHP;
	}
	
	/**
	 * Sebzodest megvalasito metodus, abstract, minden Enemy-tipusban mashogy implementalodik.
	 *
	 * @param 	b A sebzo Bullet objektum.
	**/
	public abstract void hurt(Bullet b);
	
	/**
	 * Mozog, a kovetkezo path-ra lep, cellat valt.
	**/
	public void move() {
		
		if(modSpeed < speed) {
			modSpeed++;
		} else {
			ProtoTester.safePrint(ProtoTester.getKeyByValue(this) + " moves");
			
			Path nextPath = null;
			
			if(forcedNextPath != null){
				nextPath = forcedNextPath;
				ProtoTester.safePrint(ProtoTester.getKeyByValue(this) + " crossroad");
			}else{
				nextPath = myPath.getNext();
			}
			
			
			
			if(nextPath == null){
				ProtoTester.safePrint(ProtoTester.getKeyByValue(this) + " reached Mount Doom");
				igame.incSucceeded();
				eliminate();
			}else{		
				nextPath.registerIPathPlaceable(this);
			}
		}
		forcedNextPath = null;
	}
	 /**
	  * A modSpeed valtozot valtoztatja. Lassitani lehet vele. (vagy adott esetben gyorsitani)
	  * 
	  * @param 	msp A lassitas merteke.
	  */
	public void setModSpeed(int msp) {
		modSpeed -= msp;
	}
	
	/**
	 * Az Enemyt torli az utjarol es a Game-bol.
	 */
	public void eliminate() {
		
		igame.removeEnemyIn(this);
		
		myPath.deleteEnemy(this);
	}
	
	/**
	 * Az Enemyt regisztralja a parameterben atadott utra. Nem csak a move fuggvenyen keresztul mukodik, kulon is hivhato.
	 * 
	 * @param 	p Az ut, ahova regisztralni akarjuk az Enemyt.
	 */
	public void registerPath(Path p) {
		
		if(myPath != null) {
			myPath.deleteEnemy(this);
		}
		
		if(p != null) {
			myPath = p;
			myPath.registerEnemy(this);
		} else {
			throw new InvalidParameterException();
		}
	}
	
	/**
	 * Uj eleterot allit be.
	 * @param hp Az uj eletero erteke.
	 */
	public void setHealth(int hp) {
		health = hp;		
	}
	
	/**
	 * Az ellenseg kettevagasat vegzo fuggveny. Az ososztalyban csak a kiiratast vegezzuk, a tobbit a leszarmazottakban kell implementalni.
	 */
	public void cut() {
		ProtoTester.safePrint(ProtoTester.getKeyByValue(this) + " cut in half");
	}

	public GEnemy getGEnemy() {
		return genemy;
	}

	public void addGEnemy(GEnemy genemy) {
		this.genemy = genemy;
	}
}
