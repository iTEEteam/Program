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
	private static final int speed = 3;
	
	/**
	 * A maximalis eletero erteke.
	 */
	private static final int maxHP = 100;

	/**
	 * Az ellens�g bels� id�m�r�je. A setModSpeed v�ltoztathatja � jellemz�en negat�v ir�nyba, akad�lyokon.
	**/
	private int modSpeed = 0;
	
	/**
	 * �leterej�t t�rolja ebben. Hurt f�ggv�nyben cs�kkenti.
	**/
	protected int health;
	
	/**
	 * A Path cime, amin az Enemy eppen tartozkodik.
	**/
	protected Path myPath;
	
	/**
	 * A soron k�vetkez� path c�me.
	 * Nem csin�l semmit, csak itt hagytam.
	 */
	private Path nextPath;
	
	/**
	 * Ezen kereszt�l tudja m�dos�tani a man�t, amikor meghal, illetve ha el�r a v�gzet hegy�re m�dos�tani a sz�ml�l�t (Game.incSucceeded), hogy n�j�n egyel.
	 */
	protected IGame igame;
	
	/**
	 * Konstruktor.
	 *
	 * @param 	game Az IGame interf�sz, amivel a Game-et el�ri.
	 * @param 	p A l�trehoz�s helye. Felesleges, mert �gysem �ton hozzuk l�tre.
	**/
	public Enemy(IGame game, Path p) { //TODO Nem kell a p attrib�tum, nem �ton hozzuk l�tre az Enemyt
		//ez az�rt nem kell, mert absztrakt oszt�lyt nem p�ld�nyos�tunk, viszont �gy k�t bejegyz�s�nk is lenne a lesz�rmazottai miatt
		//ProtoTester.addToObjectCatalog(this); 
		igame = game;
		health = maxHP;
		p.registerIPathPlaceable(this); 
	}
	
	/**
	 * Sebz�d�st megval�s�t� met�dus, abstract, minden Enemy-t�pusban m�shogy implement�l�dik.
	 *
	 * @param 	b A sebz� Bullet objektum.
	**/
	public abstract void hurt(Bullet b);
	
	/**
	 * Mozog, a k�vetkez� path-ra l�p, cell�t v�lt.
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
	  * A modSpeed v�ltoz�t v�ltoztatja. Lass�tani lehet vele.
	  * 
	  * @param 	msp A lass�t�s m�rt�ke.
	  */
	public void setModSpeed(int msp) {
		modSpeed -= msp;
	}
	
	/**
	 * Az Enemyt t�rli az �tj�r�l �s a Game-b�l.
	 */
	public void eliminate() {
		igame.removeEnemyIn(this);
		igame.removeEnemyOut(this);
		
		myPath.deleteEnemy(this);
	}
	
	/**
	 * Az Enemyt regisztr�lja a param�terben �tadott �tra. Nem csak a move f�ggv�nyen kereszt�l m�k�dik, k�l�n is h�vhat�.
	 * 
	 * @param 	p Az �t, ahova regisztr�lni akarjuk az Enemyt.
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
	 * �j �leter�t �ll�t be.
	 * @param hp Az �j �leter� �rt�ke.
	 */
	public void setHealth(int hp) {
		health = hp;		
	}
	
	public abstract void cut();
}
