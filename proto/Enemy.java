package proto;


/**
 * A kulonbozo ellensegek (Hobbit, Dwarf, Elf, Human) absztrakt ososztalya.
**/
public abstract class Enemy implements IPathPlaceable {
	/**
	 * Az Enemy normal sebessege.
	**/
	private static final int speed = 10;
	private static final int maxHP = 100;

	/**
	 * Az Enemy modositott sebessege, vagyis a lassitas m�rt�kovel megvaltoztatott ertek.
	**/
	private int modSpeed;
	
	/**
	 * Az Enemy eletereje.
	**/
	protected int health;
	
	/**
	 * A Path cime, amin az Enemy eppen tartozkodik.
	**/
	protected Path myPath;
	private Path nextPath;
	
	protected IGame igame;
	
	/**
	 * Konstruktor.
	 *
	 * @param    sp
	 * @param    msp
	 * @param    h
	 * @param    ig
	**/
	public Enemy(IGame game, Path p) {
		ProtoTester.addToObjectCatalog(this);
		igame = game;
		health = maxHP;
		myPath = p;
	}
	
	/**
	 * A kapott bullet alapjan megsebzi az Enemy-t. Minden Enemy-re mas a fuggvenytorzs.
	 *
	 * @param    b
	**/
	public abstract void hurt(Bullet b);
	
	/**
	 * Az Enemy mozgasat vegrehajto metodus.
	**/
	public void move() {
		
		nextPath = myPath.getNext();
		
		if(nextPath== null){
			igame.incSucceeded();
			eliminate(myPath);
		}else{		
			nextPath.registerIPathPlaceable(this);
		}
		
	}
	
	public void setModSpeed(int msp) {
	
	}
	
	public void eliminate(Path p) {
	}
	
	public void registerPath(Path p) {
		
		
		myPath.deleteEnemy(this);
		
		nextPath.registerEnemy(this);
		
	}
	
	public void setHealth(int hp){
		health = hp;		
	}
}
