package proto;

import java.util.ArrayList;


public class Obstacle implements IObstacle, IPathPlaceable {
	/**
	 * Az akadaly ara. Statikus, mert minden akadalyra ugyanannyi.
	**/
	public static final int price = 10;
	
	/**
	 * A lassitas merteke.
	**/
	private int slowIntens;
	
	/**
	 * Az amortizacio merteke.
	**/
	private int amort;
	
	/**
	 * Az Path cime, amin az akadaly van.
	**/
	private Path myPath;
	
	private ArrayList<IOGem> gems;
	
	/**
	 * Konstruktor
	 *
	 * @param    price
	 * @param    intens
	 * @param    am
	 * @param    p
	 * @param    up
	**/
	public Obstacle() {
		ProtoTester.addToObjectCatalog(this);
		
		ProtoTester.safePrint("--> Obstacle konstruktor", true);
		
		gems = new ArrayList<IOGem>();
		
		ProtoTester.safePrint("<-- Obstacle konstruktor return", false);
	
	}
	
	public void slow(Enemy e) {
		ProtoTester.safePrint("--> Obstacle slow", true);
		
		e.setModSpeed(slowIntens);
		
		amortization();
		
		if(amort <= 0){
			eliminate(myPath);
		}
		ProtoTester.safePrint("<-- Obstacle slow return", false);
	}
	
	public void amortization() {
		ProtoTester.safePrint("--> Obstacle amortization", true);
		
		ProtoTester.safePrint("<-- Obstacle amortization return", false);
	}
	
	public void increaseIntensity(int intens) {
		ProtoTester.safePrint("--> Obstacle increaseIntensity", true);
		
		ProtoTester.safePrint("<-- Obstacle increaseIntensity return", false);
	}
	
	public void addIOGem(IOGem g) {
		ProtoTester.safePrint("--> Obstacle addIOGem", true);
		
		g.upgradeObstacle(this);
		gems.add(g);
		
		ProtoTester.safePrint("<-- Obstacle addIOGem return", false);
	}
	
	public void repair() {
		ProtoTester.safePrint("--> Obstacle repair", true);
		
		amort = 0;
		
		ProtoTester.safePrint("<-- Obstacle repair return", false);
	}
	
	public void eliminate(Path p) {
		ProtoTester.safePrint("<-- Obstacle eliminate", true);
		myPath.deleteIObstacle(this);
		ProtoTester.safePrint("<-- Obstacle eliminate return", false);
	}
	
	public void registerPath(Path p) {
		ProtoTester.safePrint("--> Obstacle registerPath", true);
		myPath = p;
		
		p.registerIObstacle(this);
		
		ProtoTester.safePrint("<-- Obstacle registerPath return", false);
	
	}
}
