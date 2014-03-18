package Program.Skeleton;
import java.util.ArrayList;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Tower.java
//  @ Date : 2014.03.11.
//  @ Author : itee_team
//
//


public class Tower implements ITower, IFieldPlaceable {
	/**
	 * A torony �ra. Az az �rt�k, amit a man�b�l le kell vonni ahhoz, hogy a j�t�kos megvehesse a tornyot. Statikus, mert minden toronyra ugyanaz.
	**/
	private static final int price = 10;
	
	/**
	 * A torony hat�sugara, hogy h�ny cell�t �r el a szomsz�dos cell�k k�z�l.
	**/
	private int range;
	private int speedCtr;
	private int speed;
	/**
	 * A l�ved�k amit a torony kil�.
	**/
	private Bullet bullet;
	
	/**
	 * Fejleszt�seket t�rol� v�ltoz�. Ebb�l der�l ki, hogy az adott toronyra milyen Gem-eket vett m�r meg a j�t�kos.
	**/
	private ArrayList<ITGem> gems;
	
	/**
	 * Annak a mez�nek a referenci�ja, amelyiken az adott torony �ll.
	**/
	private Field myField;
	
	/**
	 * Azon cell�k list�ja, amelyek beleesnek a l�t�vba (range).
	**/
	private ArrayList<Path> paths;
	private IGame igame;
	private Field itower;
	public Game towers;
	
	/**
	 * Konstruktor
	 *
	 * @param    rang
	 * @param    pr
	**/
	public Tower(IGame game) {
		igame = game;
		gems = new ArrayList<ITGem>();
		paths = new ArrayList<Path>();
		bullet = new Bullet();
//		System.out.println("--> Tower konstruktor");
//		System.out.println("<-- Tower return");
	}
	
	public void upgradeSpeed(int sp) {
		
	}
	
	public void upgradeRange(int rng) {
	
	}
	
	public void upgradeEnemy(Enemy e) {
	
	}
	
	public void upgradeDamage(int dmg) {
	
	}
	
	public Enemy chooseEnemy() {
		SkeletonTester.safePrint("--> Tower choose enemy", true);
		Path path = paths.get(0);
		
		SkeletonTester.safePrint("<-- Tower return", false);	
		if(!path.hasEnemy()) return null;
		return path.getEnemies().get(0);
	}
	
	// kivalaszt egy ellenseget es megsebzi a bullet-tel
	public void shoot() {
		SkeletonTester.safePrint("--> Tower shoot", true);

		Enemy target = chooseEnemy();
		if(target==null){
			SkeletonTester.safePrint("<-- Tower return", false);
			return;
		}
		target.hurt(bullet);
		SkeletonTester.safePrint("<-- Tower return", false);
	}
	
	// ez a fv csak teszteleshez kell
	public void addPath(Path path){
		paths.add(path);		
	}
	
	public void setPaths() {
		SkeletonTester.safePrint("--> Tower setPaths", true);
		
		SkeletonTester.safePrint("<-- Tower return", false);
	}
	
	public void addITGem(ITGem g) {
		SkeletonTester.safePrint("--> Tower addITGem", true);
		gems.add(g);
		
		SkeletonTester.safePrint("<-- Tower return", false);
	}
	
	public void registerField(Field field) {
		SkeletonTester.safePrint("--> Tower registerField", true);
		if(!field.hasTower()){
			myField = field;
			field.registerITower(this);
		}
		SkeletonTester.safePrint("<-- Tower return", false);
	}
	
	public void sell() {
		SkeletonTester.safePrint("--> Tower sell", true);
		int value = price/2;
		
		
		for(ITGem g:gems){
			value = g.getValue();
		}
		
		igame.changeMana(value);
		
		igame.removeTower(this);
		
		myField.deleteIFieldPlaceable(this);
		
		SkeletonTester.safePrint("<-- Tower return", false);
	}
}
