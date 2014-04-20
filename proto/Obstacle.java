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
		
		ProtoTester.safePrint("--> Obstacle konstruktor");
		amort = 10;
		slowIntens = 5;
		gems = new ArrayList<IOGem>();
		
		ProtoTester.safePrint("<-- Obstacle konstruktor return");
	
	}
	
	public void slow(Enemy e) {
		ProtoTester.safePrint("--> Obstacle slow");
		
		e.setModSpeed(slowIntens);
		
		amortization();
		
		if(amort <= 0){
			eliminate(myPath);
		}
		ProtoTester.safePrint("<-- Obstacle slow return");
	}
	
	public void amortization() {
		ProtoTester.safePrint("--> Obstacle amortization");
		amort--;
		
		ProtoTester.safePrint("<-- Obstacle amortization return");
	}
	
	public void increaseIntensity(int intens) {
		ProtoTester.safePrint("--> Obstacle increaseIntensity");
		slowIntens+=intens;
		
		ProtoTester.safePrint("<-- Obstacle increaseIntensity return");
	}
	
	public void addIOGem(IOGem g) {
		ProtoTester.safePrint("--> Obstacle addIOGem");
		
		g.upgradeObstacle(this);
		gems.add(g);
		
		ProtoTester.safePrint("<-- Obstacle addIOGem return");
	}
	
	public void repair() {
		ProtoTester.safePrint("--> Obstacle repair");
		
		amort = 0;
		
		ProtoTester.safePrint("<-- Obstacle repair return");
	}
	
	public void eliminate(Path p) {
		ProtoTester.safePrint("<-- Obstacle eliminate");
		myPath.deleteIObstacle(this);
		ProtoTester.safePrint("<-- Obstacle eliminate return");
	}
	
	public void registerPath(Path p) {
		ProtoTester.safePrint("--> Obstacle registerPath");
		myPath = p;
		
		p.registerIObstacle(this);
		
		ProtoTester.safePrint("<-- Obstacle registerPath return");
	
	}

    @Override
    public void eliminate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
