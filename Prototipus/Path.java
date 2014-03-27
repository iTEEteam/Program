package Program.Skeleton;

import java.util.ArrayList;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Path.java
//  @ Date : 2014.03.11.
//  @ Author : itee_team
//
//

public class Path extends Cell {
	/**
	 * Az egy l�p�sben k�vetkez� Path cell�k c�mei.
	**/
	private ArrayList<Path> nextPaths;
	
	/**
	 * Az �ton l�v� akad�ly interf�sz� v�ltoz� c�me. Ha nincs akad�ly az �ton, null.
	**/
	private IObstacle myIObstacle;
	
	/**
	 * Az �ppen a Path-en tart�zkod� ellens�g(ek) c�me(i).
	**/
	private ArrayList<Enemy> enemies;
	
	private Obstacle myObstacle;
	
	// ez is WTF?
	//private Enemy myPath;
	
	private Tower paths;
	
	/**
	 * Konstruktor
	**/
	public Path() {
		SkeletonTester.safePrint("--> Path konstruktor", true);
		enemies = new ArrayList<Enemy>();
		nextPaths = new ArrayList<Path>();
		SkeletonTester.safePrint("<-- Path konstruktor return", false);

	}
	
	/**
	 * A bool t�pus� visszat�r�si �rt�kben megmondja, hogy van-e ellens�g a Path-en. 
	**/
	public boolean hasEnemy() {
		SkeletonTester.safePrint("--> Path hasEnemy", true);
		SkeletonTester.safePrint("<-- Path hasEnemy return", false);
		return !enemies.isEmpty();
	}
	
	public boolean hasObstacle() {
		SkeletonTester.safePrint("--> Path hasObstacle", true);
		SkeletonTester.safePrint("<-- Path hasObstacle return", false);
		return !(myIObstacle==null);
	}
	
	
	/**
	 * T�rli a param�terben kapott IPathPlaceable interf�sz� v�ltoz�t a Path-r�l. 
	 *
	 * @param    e
	**/
	public boolean deleteEnemy(Enemy e) {
		SkeletonTester.safePrint("--> Path deleteEnemy", true);
		
		SkeletonTester.safePrint("<-- Path deleteEnemy return", false);
		
		return false;
	
	}
	
	public boolean deleteIObstacle(IObstacle io) {
		return false;
	
	}
	
	/**
	 * Beregisztr�lja a param�terben kapott IPathPlaceable interf�sz� v�ltoz�t a Path-en. 
	 *
	 * @param    ipp
	**/
	public void registerIPathPlaceable(IPathPlaceable ipp) {
		SkeletonTester.safePrint("--> Path registerIPathPlaceable", true);
		
		ipp.registerPath(this);
		
		SkeletonTester.safePrint("<-- Path registerIPathPlaceable return", false);
	}
	
	/**
	 * Beregisztr�lja a param�terben kapott IObstacle interf�sz� v�ltoz�t a Path-en. 
	 *
	 * @param    io
	**/
	public void registerIObstacle(IObstacle io) {
		SkeletonTester.safePrint("--> Path registerIObstacle", true);
		
		if(hasObstacle()){
			SkeletonTester.safePrint("<-- Path registerIObstacle return", false);
			return;
		}
		if(hasEnemy()){
			SkeletonTester.safePrint("<-- Path registerIObstacle return", false);
			return;
		}
		
		// TODO kell a pathnak  igame interfesz, hogy a manat valtoztatni tudjuk
		myIObstacle = io;
		
		SkeletonTester.safePrint("<-- Path registerIObstacle return ", false);
	}
	
	/**
	 * Beregisztr�lja a param�terben kapott Enemy t�pus� v�ltoz�t a Path-en. 
	 *
	 * @param    e
	**/
	public void registerEnemy(Enemy e) {
		SkeletonTester.safePrint("--> Path registerEnemy", true);
		
		if(myObstacle != null){
			myObstacle.slow(e);
		}
		enemies.add(e);
		SkeletonTester.safePrint("<-- Path registerEnemy return", false);
	}
	
	/**
	 * Visszat�r a Path-en l�v� ellens�g(ek)el.
	**/
	public ArrayList<Enemy> getEnemies() {
		SkeletonTester.safePrint("--> Path getEnemies", true);
		
		SkeletonTester.safePrint("<-- Path getEnemies return", false);
		return enemies;
	
	}
	
	public IObstacle getIObstacle(){
		SkeletonTester.safePrint("--> Path getIObstacle", true);
		
		SkeletonTester.safePrint("<-- Path getIObstacle return", false);
		return myIObstacle;
	}
	
	/**
	 * Visszat�r a nextPaths lista egy elem�vel, ahova az enemy majd l�phet.
	**/
	public Path getNext() {
		SkeletonTester.safePrint("--> Path getNext", true);
		
		SkeletonTester.safePrint("<-- Path getNext return", false);
		return nextPaths.get(0);
	
	}

	@Override
	public boolean isPath() {
		return true;
	}
	
	public void addNext(Path p){
		nextPaths.add(p);
	}
}
