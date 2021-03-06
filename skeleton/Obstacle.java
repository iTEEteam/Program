package skeleton;

import java.util.ArrayList;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Obstacle.java
//  @ Date : 2014.03.11.
//  @ Author : itee_team
//
//




public class Obstacle implements IObstacle, IPathPlaceable {
	/**
	 * Az akad�ly �ra. Statikus, mert minden akad�lyra ugyanannyi.
	**/
	public static final int price = 10;
	
	/**
	 * A lass�t�s m�rt�ke.
	**/
	private int slowIntens;
	
	/**
	 * Az amortiz�ci� m�rt�ke.
	**/
	private int amort;
	
	/**
	 * Az Path c�me, amin az akad�ly van.
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
		SkeletonTester.safePrint("--> Obstacle konstruktor", true);
		
		gems = new ArrayList<IOGem>();
		
		SkeletonTester.safePrint("<-- Obstacle konstruktor return", false);
	
	}
	
	public void slow(Enemy e) {
		SkeletonTester.safePrint("--> Obstacle slow", true);
		
		e.setModSpeed(slowIntens);
		
		amortization();
		
		if(amort <= 0){
			eliminate(myPath);
		}
		SkeletonTester.safePrint("<-- Obstacle slow return", false);
	}
	
	public void amortization() {
		SkeletonTester.safePrint("--> Obstacle amortization", true);
		
		SkeletonTester.safePrint("<-- Obstacle amortization return", false);
	}
	
	public void increaseIntensity(int intens) {
		SkeletonTester.safePrint("--> Obstacle increaseIntensity", true);
		
		SkeletonTester.safePrint("<-- Obstacle increaseIntensity return", false);
	}
	
	public void addIOGem(IOGem g) {
		SkeletonTester.safePrint("--> Obstacle addIOGem", true);
		
		g.upgradeObstacle(this);
		gems.add(g);
		
		SkeletonTester.safePrint("<-- Obstacle addIOGem return", false);
	}
	
	public void repair() {
		SkeletonTester.safePrint("--> Obstacle repair", true);
		
		amort = 0;
		
		SkeletonTester.safePrint("<-- Obstacle repair return", false);
	}
	
	public void eliminate(Path p) {
		SkeletonTester.safePrint("<-- Obstacle eliminate", true);
		myPath.deleteIObstacle(this);
		SkeletonTester.safePrint("<-- Obstacle eliminate return", false);
	}
	
	public void registerPath(Path p) {
		SkeletonTester.safePrint("--> Obstacle registerPath", true);
		myPath = p;
		
		p.registerIObstacle(this);
		
		SkeletonTester.safePrint("<-- Obstacle registerPath return", false);
	
	}
}
