package proto;
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
	
	// osszegyujti a hatosugaraban levo ellensegeket,
	// kivalasztja a legelso
	public Enemy chooseEnemy() {
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		for(Path p: paths){  			
			// TODO, kell hozza ismerni a setPath()-t
		}
		
		if(enemies.isEmpty()) return null;
		
		return enemies.get(0);
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
	
	
	
	public void setPaths() {
		// TODO
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
		
		System.out.println(ProtoTester.objectCatalog.get(this) + " sold for "+ value + " mana");
	}
}
