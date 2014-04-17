package proto;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Game implements IGame {
	public static boolean bRandom = false;
	public static boolean bHaze = false;
        public static boolean bCut = false;
	/**
	 * A terkep, amiben a cellak tarolodnak.
	**/
	private Map map;
	
	/**
	 * Az ellensegek listaja, akik az adott palyara belepnek.
	**/
	private ArrayList<Enemy> enemiesIn;
	
	private ArrayList<Enemy> enemiesOut;
	
	private ArrayList<Tower> towers;
	
	/**
	 * A jatekos varazsereje, amibol akadalyokat, tornyokat vehet, 
	 * ill. ami minden megolt ellenseg utan gyarapszik.
	**/
	private int mana = 1000;
	
	private Path firstP;
	
	private int noEnemies;
	
	private int succeededE;
	
	/**
	 * Konstruktor
	**/
	public Game() {		
		ProtoTester.addToObjectCatalog(this);

		enemiesIn = new ArrayList<Enemy>();
		enemiesOut = new ArrayList<Enemy>();
		towers = new ArrayList<Tower>();
		
		map = new Map();
	}
	
	/**
	 * Frissiti a GUI-t.
	**/
	public void update() {
		ProtoTester.safePrint("update");
		
		ProtoTester.safePrint("update return");
	}
	
	public void makeEnemies() {
	
	}
	
	public void initialize(String name) throws FileNotFoundException {
		map.load(name);
	}
	
	/**
	 * A manat csokkento/novelo fuggveny.
	**/
	public void changeMana(int value) {
		mana += value;
	}
	
	public void incSucceeded() {
	}
	
	public void addTower(Tower t) {
		
		if(towers != null){
			towers.add(t);
		}
		
	}
	
	public void removeTower(Tower t) {
	
	}
	
        public void addEnemy(String enemyType, Path p) {
            if (enemyType.equals("elf")) {
                enemiesIn.add(new Elf(this, p));
            } else if (enemyType.equals("hobbit")) {
                enemiesIn.add(new Hobbit(this, p));
            } else if (enemyType.equals("dwarf")) {
                enemiesIn.add(new Dwarf(this, p));
            } else if (enemyType.equals("human")) {
                enemiesIn.add(new Human(this, p));
            }
        }
        
	public void removeEnemy(Enemy e) {
	
	}

	@Override
	public int getMana() {
		
		return mana;
	}

	public Map getMap(){
		return map;
	}
}
