package proto;
import java.awt.Dimension;
import java.util.ArrayList;


public class Tower implements ITower, IFieldPlaceable {
	/**
	 * A torony ara. Az az ertek, amit a manabol le kell vonni ahhoz, 
	 * hogy a jatekos megvehesse a tornyot. Statikus, mert minden toronyra ugyanaz.
	**/
	public static final int price = 100;
	
	/**
	 * A torony hatosugara, hogy hany cellat er el a szomszedos cellak kozul.
	**/
	private int range;
	private int speedCtr;
	private int speed;
	/**
	 * A lovedek amit a torony kilo.
	**/
	private Bullet bullet;
	
	/**
	 * Fejleszteseket tarolo valtozo. Ebbol derul ki, hogy az adott toronyra milyen Gem-eket vett mar meg a jatekos.
	**/
	private ArrayList<ITGem> gems;
	
	/**
	 * Annak a mezonek a referenciaja, amelyiken az adott torony all.
	**/
	private Field myField;
	
	/**
	 * Azon cellak listaja, amelyek beleesnek a lotavba (range).
	**/
	private ArrayList<Path> paths;
	private IGame igame;
	public Game towers;
	
	/**
	 * Konstruktor
	 *
	 * @param    rang
	 * @param    pr
	**/
	public Tower(IGame game) {
		ProtoTester.addToObjectCatalog(this);
		igame = game;
		gems = new ArrayList<ITGem>();
		paths = new ArrayList<Path>();
		bullet = new Bullet();
	}
	
	public void upgradeSpeed(int sp) {
		speed += sp;
	}
	
	public void upgradeRange(int rng) {
		range += rng;
		setPaths();
	}
	
	public void upgradeEnemy(String e) {
		bullet.setEnemy(e);
	}
	
	public void upgradeDamage(int dmg) {
		bullet.setDamage(dmg);
	}
	
	// vegigmegy a tarolt utakon
	// az elsorol, amin van ellenseg,visszaadja a 0. elemet
	public Enemy chooseEnemy() {
		for(Path p: paths){  			
			if(p.hasEnemy()){
				return p.getEnemies().get(0);
			}
		}
		return null;
	}
	
	// kivalaszt egy ellenseget es megsebzi a bullet-tel
	public void shoot() {

		Enemy target = chooseEnemy();
		if(target==null){
			return;
		}
		target.hurt(bullet);
	}
	
	// ez a fv csak teszteleshez kell
	public void addPath(Path path){
		paths.add(path);		
	}
	
	
	/*
	 * algoritmus futasa:
	 * megkeresi a hatosugar bal felso sarkat. mivel ez kiloghat a palyarol
	 * ezert az utolso bal felso ervenyes cellat keresi meg
	 * ekkor megvan a top sor. egyesevel lefele vegig megy a cellakon (range vagy fal-on belul)
	 * beregisztralja a Path-okat
	 */
	public void setPaths() {
		// eloszor a bal felso cella lesz, majd jobbra lepked.
		Cell top;
		// maximalis oldalhossz
		int sideLength = 2*range+1;
		// hanyadik oszlopnal tartunk
		int columnCount = 0;
		// hanyadik sornal tartunk
		int rowCount = 0;
		
		// bal felso sarok megkeresese
		top = myField; // TODO ez ertek szerinti masolas?
		// bal oldal megkeresese
		for(int i = 0; i<range && top.neighbours.get(3)!=null; ++i){
			top = top.neighbours.get(3);
		}
		// felso oldal megkeresese
		for(int i = 0; i<range && top.neighbours.get(0)!=null; ++i){
			top = top.neighbours.get(0);
		}
		
		// felso soron viszi vegig a top-ot
		while(columnCount<sideLength && top.neighbours.get(1)!=null){
			Cell temp = top; // TODO szinten, ertek szerinti?
			// lefele vegignezi a cellakat
			for(int i = 0; i<range && top.neighbours.get(2)!=null; ++i){
				if(temp.isPath()){
					paths.add((Path)temp);
				}
				temp = temp.neighbours.get(2);
			}
			++columnCount;
			top = top.neighbours.get(1);
		}
	}
	
	public void addITGem(ITGem g) {
		g.upgradeTower(this);
		gems.add(g);
		
	}
	
	public void registerField(Field field) {
		if(!field.hasTower()){
			myField = field;
			field.registerITower(this);
		}
	}
	
	public void sell() {
		int value = price;
		
		// kristalyok arat hozzaadja
		for(ITGem g:gems){
			value += g.getValue();
		}
		
		// value eddig a teljes rakoltott mana, csak a felet kapja vissza
		value /= 2;
		
		igame.changeMana(value);
		
		igame.removeTower(this);
		
		myField.deleteIFieldPlaceable(this);
		
		System.out.println(ProtoTester.getKeyByValue(this) + " sold for "+ value + " mana");
	}
}
