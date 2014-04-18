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
	 * Flet�telek, ciklusok: 
	 * Ha lej�rt a k�d, kitiszt�tjuk a p�ly�t
	 * Ha nem j�rt le, cs�kketntj�k a h�tral�v� idej�t
	 * Ha sem v�rakoz� ellens�g, sem p�ly�n l�v� nincs, k�sz�t�nk
	 * Ha van v�rakoz� ellens�g, elind�tjuk a p�ly�n
	 * Minden ellens�get l�ptet�nk
	 * Minden toronnyal l�v�nk
	 * Ha random teljes�l �s �pp nincs k�d, gener�lunk
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
