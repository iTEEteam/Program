package graph;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import sun.java2d.loops.DrawGlyphListAA.General;


public class Game implements IGame {
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
	
	private IView iview;
	
	/**
	 * Konstruktor
	**/
	public Game() {		

		
		enemiesIn = new ArrayList<Enemy>();
		enemiesOut = new ArrayList<Enemy>();
		towers = new ArrayList<Tower>();
		
		map = new Map(this);
		noEnemies=10;
		hazeTime=-1;
		
		iview = null;
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
		
		if(hazeTime<=0) {
			for(int i=0; i<towers.size(); i++) {
				hazeTime=20;
				towers.get(i).haze();
			}
		}
		
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
		
		if (enemiesIn.isEmpty() && enemiesOut.isEmpty()) {
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
	}
	/**
	 * Enemy-ket keszit. Random modon v�lasztunk tipust. A metodus ellenseghullamot general,
	 * vagyis csak idonkent hivjuk, ha elfogytak az ellensegek. Minden ujabb hivaskor egyre
	 * t�bbet general, amig vege nincs a jateknak, vagy elertuk az adott szamu ellenseghullamot.
	 */
	public void makeEnemies() {
		Random generator=new Random();
		for(int j=0; j<noEnemies; j++) {
			int i=generator.nextInt(3);
			
			Enemy e = null;
			GEnemy ge = null;
			
			switch(i) {
				case 0:
					e=new Hobbit(this);
					ge = new GHobbit(e);
					break;
				case 1:
					e=new Elf(this);
					ge = new GElf(e);
					break;
				case 2:
					e=new Dwarf(this);
					ge = new GDwarf(e);
					break;
				case 3:
					e=new Human(this);
					ge = new GHuman(e);
					break;
			}
			
			e.addGEnemy(ge);
			enemiesOut.add(e);
			
		}
		noEnemies++;
	}
	
	/**
	 * Inicializalo metodus. Betoltjuk a name nevu palyat, megadjuk az elso ut cimet.
	 */
	public void initialize(String name) throws FileNotFoundException {
		map.load(name);
		firstP = map.getFirstPath();
	}
	
	/**
	 * A manat csokkento/novelo fuggveny.
	**/
	public void changeMana(int value) {
		mana += value;
		iview.gNotify();
	}
	
	/**
	 * Noveli a succeededE erteket. Akkor kell, ha egy ellenseg bejut a vegzet hegyere. 
	 */
	public void incSucceeded() {
		succeededE++;
		iview.gNotify();
	}
	
	/**
	 * Toroljuk t-t a towersbol. Pl eladtak egy tornyot.
	 */
	public void removeTower(Tower t) {
		
		if(t != null){
			towers.remove(t);
		}
		
	}
	
	/**
	 * Hozzaadjuk t-t a towers-hez. Akkor kell, ha tornyot vettek.
	 */
	public void addTower(Tower t) {
		towers.add(t);
	}
	
	/**
	 * Make parancs miatt kell.
	 */
    public void addEnemyIn(String enemyType, Path p) {
        Enemy e = null;
    	if (enemyType.equals("elf")) {
        	e = new Elf(this);         
        } else if (enemyType.equals("hobbit")) {
        	e = new Hobbit(this);        	
        } else if (enemyType.equals("dwarf")) {
        	e = new Dwarf(this);        	
        } else if (enemyType.equals("human")) {
        	e = new Human(this);
        }
    	enemiesIn.add(e);
        p.registerIPathPlaceable(e);
    }
    
    
	/**
	 * Toroljuk e-t az enemiesIn-bol, alatta enemiesOut-bol
	 */
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
	
	/**
	 * Mana valtoztatasa
	 */
	@Override
	public int getMana() {
		
		return mana;
	}
	
	/**
	 * Map lekerese.
	 */
	public Map getMap(){
		return map;
	}
	
	/**
	 * bRandom lekerese. Teszteles miatt.
	 */
	@Override
	public boolean getRandom() {
		return true;
	}
	
	/**
	 * Hozaadjuk e-t az enemiesIn-hez.
	 */
	@Override
	public void addEnemyIn(Enemy e) {
		enemiesIn.add(e);		
	}
	
	/**
	 * Az ertesiteseket fogado objektumot allitja be. 
	 * 
	 * @param iview A grafikus jellegu esemenyek eseten ertesitendo objektum.
	 */
	public void setIView(IView iview) {
		this.iview = iview;
	}
	
	/**
	 * Az ertesiteseket fogado objektumot adja vissza. 
	 * 
	 * @return A grafikus jellegu esemenyek eseten ertesitendo objektum.
	 */
	public IView getIView() {
		return iview;
	}

	public int getSucceededE() {
		return succeededE;
	}

}
