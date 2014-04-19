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
	
	private int hazeTime;
	
	/**
	 * Konstruktor
	**/
	public Game() {		
		ProtoTester.addToObjectCatalog(this);

		enemiesIn = new ArrayList<Enemy>();
		enemiesOut = new ArrayList<Enemy>();
		towers = new ArrayList<Tower>();
		
		map = new Map();
		noEnemies=10;
		hazeTime=0;
	}
	
	/**
	 * Frissiti a GUI-t. 
	 * Fletételek, ciklusok: 
	 * Ha lejárt a köd, kitisztítjuk a pályát
	 * Ha nem járt le, csökketntjük a hátralévõ idejét
	 * Ha sem várakozó ellenség, sem pályán lévõ nincs, készítünk
	 * Ha van várakozó ellenség, elindítjuk a pályán
	 * Minden ellenséget léptetünk
	 * Minden toronnyal lövünk
	 * Ha random teljesül és épp nincs köd, generálunk
	**/
	public void update() {
		ProtoTester.safePrint("update");
		
		if(hazeTime==0) {
			for(Tower t : towers) {
				t.clearUp();
			}
		}
		
		if(hazeTime!=0) {
			hazeTime--;
		}
		
		if (enemiesIn==null && enemiesOut==null) {
			makeEnemies();
		}
		
		if (enemiesOut!=null) {
			firstP.registerIPathPlaceable(enemiesOut.get(enemiesOut.size()-1));
			enemiesIn.add(enemiesOut.get(enemiesOut.size()-1));
			enemiesOut.remove(enemiesOut.size()-1);
		}
		
		for(Enemy e : enemiesIn) {
			e.move();
		}
		
		for(Tower t : towers) {
			t.shoot();
		}
		
		if(bHaze && hazeTime==0) {
			for(Tower t : towers) {
				hazeTime=20;
				t.haze();
			}
		}
		
			
		ProtoTester.safePrint("update return");
	}
	
	public void makeEnemies() {
		Random generator=new Random();
		for(int j=0; j<noEnemies; j++) {
			int i=generator.nextInt(3);
			switch(i) {
				case 0:
					Hobbit h=new Hobbit();
					enemiesOut.add(h);
				case 1:
					Elf e=new Elf();
					enemiesOut.add(e);
				case 2:
					Dwarf d=new Dwarf();
					enemiesOut.add(d);
				case 3:
					Human hu=new Human();
					enemiesOut.add(hu);
			}
		}
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
		succeededE++;
	}
	
	public void removeTower(Tower t) {
		
		if(towers != null){
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
		if(enemiesIn!=null) {
			enemiesIn.remove(e);
		}
	}
	
	public removeEnemyOut(Enemy e) {
		if(enemiesOut!=null) {
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
}
