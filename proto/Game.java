package proto;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;


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
	
	private int hazeTime;
	
	/**
	 * Konstruktor
	**/
	public Game() {		
		ProtoTester.addToObjectCatalog(this);

		enemiesIn = new ArrayList<Enemy>();
		enemiesOut = new ArrayList<Enemy>();
		towers = new ArrayList<Tower>();
		
		map = new Map(this);
		noEnemies=10;
		hazeTime=-1;
	}
	
	/**
	 * Frissiti a GUI-t. 
	 * Flet�telek, ciklusok: 
	 * Ha lej�rt a k�d, kitiszt�tjuk a p�ly�t
	 * Ha sem v�rakoz� ellens�g, sem p�ly�n l�v� nincs, k�sz�t�nk
	 * Ha van v�rakoz� ellens�g, elind�tjuk a p�ly�n
	 * Minden ellens�get l�ptet�nk
	 * Minden toronnyal l�v�nk
	 * Ha random teljes�l �s �pp nincs k�d, gener�lunk
	**/
	public void update() {
		ProtoTester.safePrint("update");
		
		if(hazeTime==0) {
			for(int i=0; i<towers.size(); i++) {
				towers.get(i).clearUp();
			}
			
			// eddig, ha lement a hazeTime 0-ra, minden korben ujrahivta a clearUp-t, null exeptiont dobott
			// lemegy -1-re amikor lefutott es varja hogy ujratoltsek
			hazeTime = -1;
		}
		
		if(hazeTime>0) {
			hazeTime--;
		}
		
		if (bRandom == true && enemiesIn.isEmpty() && enemiesOut.isEmpty()) {
			makeEnemies();
		}
		
		if (enemiesOut.size() != 0) {
			firstP.registerIPathPlaceable(enemiesOut.get(enemiesOut.size()-1));
			enemiesIn.add(enemiesOut.get(enemiesOut.size()-1));
			enemiesOut.remove(enemiesOut.size()-1);
		}
		
		for(int i=0; i<enemiesIn.size(); i++) {
			enemiesIn.get(i).move();
		}
		
		for(int i=0; i<towers.size(); i++) {
			towers.get(i).shoot();
		}
		
		if(bHaze && hazeTime<=0) {
			for(int i=0; i<towers.size(); i++) {
				hazeTime=20;
				towers.get(i).haze();
			}
		}
		
	}
	
	public void makeEnemies() {
		Random generator=new Random();
		for(int j=0; j<noEnemies; j++) {
			int i=generator.nextInt(3);
			switch(i) {
				case 0:
					Hobbit h=new Hobbit(this, map.getFirstPath());
					enemiesOut.add(h);
				case 1:
					Elf e=new Elf(this, map.getFirstPath());
					enemiesOut.add(e);
				case 2:
					Dwarf d=new Dwarf(this, map.getFirstPath());
					enemiesOut.add(d);
				case 3:
					Human hu=new Human(this, map.getFirstPath());
					enemiesOut.add(hu);
			}
		}
	}
	
	public void initialize(String name) throws FileNotFoundException {
		map.load(name);
		firstP = map.getFirstPath();
	}
	
	/**
	 * A manat csokkento/novelo fuggveny.
	**/
	public void changeMana(int value) {
		mana += value;
	}
	
	public void incSucceeded() {
		succeededE++;
	}
	
	public void removeTower(Tower t) {
		
		if(t != null){
			towers.remove(t);
		}
		
	}
	
	public void addTower(Tower t) {
		towers.add(t);
	}
	
    public void addEnemyIn(String enemyType, Path p) {
        Enemy e = null;
    	if (enemyType.equals("elf")) {
        	e = new Elf(this, p);         
        } else if (enemyType.equals("hobbit")) {
        	e = new Hobbit(this, p);        	
        } else if (enemyType.equals("dwarf")) {
        	e = new Dwarf(this, p);        	
        } else if (enemyType.equals("human")) {
        	e = new Human(this, p);
        }
    	enemiesIn.add(e);
        p.registerEnemy(e);
    }
        
	public void removeEnemyIn(Enemy e) {
		if(e!=null) {
			enemiesIn.remove(e);
		}
	}
	
	public void removeEnemyOut(Enemy e) {
		if(e!=null) {
			enemiesOut.remove(e);
		}
	}

	@Override
	public int getMana() {
		
		return mana;
	}

	public Map getMap(){
		return map;
	}

	@Override
	public boolean getRandom() {
		return bRandom;
	}

	@Override
	public void addEnemyIn(Enemy e) {
		enemiesIn.add(e);		
	}
}
