package proto;

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
	 * Az egy lepesben kovetkezo Path cellak cimei.
	**/
// 4 elemu tomb. 0=u, 1=r, 2=d, 3=l ha az adott iranyba nincs path -> null
	private ArrayList<Path> nextPaths;
	
	/**
	 * Az uton levo akadaly interfesze valtozo cime. Ha nincs akadaly az uton, null.
	**/
	private IObstacle myIObstacle;
	
	/**
	 * Az eppen a Path-en tartozkodo ellenseg(ek) cime(i).
	**/
	private ArrayList<Enemy> enemies;
	
	private IGame igame;
	
	// ez is WTF?
	//private Enemy myPath;
	
	//private Tower paths;
	
	/**
	 * Konstruktor
	**/
	public Path(IGame game) {
		ProtoTester.addToObjectCatalog(this);
		igame = game;
		
		enemies = new ArrayList<Enemy>();
		nextPaths = new ArrayList<Path>();
		neighbours = new ArrayList<Cell>();
	}
	
	public void setNextPaths(ArrayList<Path> nextPaths_){
		nextPaths = nextPaths_;
	}
	
	
	
	
	/**
	 * A bool tipusu visszateresi ertekben megmondja, hogy van-e ellenseg a Path-en. 
	**/
	public boolean hasEnemy() {
		return !enemies.isEmpty();
	}
	
	public boolean hasObstacle() {

		return !(myIObstacle==null);
	}
	
	
	/**
	 * Torli a parameterben kapott IPathPlaceable interfeszu valtozot a Path-rol. 
	 *
	 * @param    e
	**/
	public boolean deleteEnemy(Enemy e) {	
		return enemies.remove(e);
	
	}
	
	public boolean deleteIObstacle(IObstacle io) {
		if (myIObstacle==io){
			myIObstacle = null;
			return true;
		}
		return false;
	
	}
	
	/**
	 * Beregisztralja a parameterben kapott IPathPlaceable interfeszi valtozot a Path-en. 
	 *
	 * @param    ipp
	**/
	public void registerIPathPlaceable(IPathPlaceable ipp) {
		
		ipp.registerPath(this);
	}
	
	/**
	 * Beregisztralja a parameterben kapott IObstacle interfeszu valtozot a Path-en. 
	 *
	 * @param    io
	**/
	public void registerIObstacle(IObstacle io) {		
		if(hasObstacle()){
			return;
		}
		if(hasEnemy()){
			return;
		}
		
		// TODO kell a pathnak  igame interfesz, hogy a manat valtoztatni tudjuk
		myIObstacle = io;
		
	}
	
	/**
	 * Beregisztralja a parameterben kapott Enemy tipusu valtozot a Path-en. 
	 *
	 * @param    e
	**/
	public void registerEnemy(Enemy e) {
		
		if(myIObstacle != null){
			myIObstacle.slow(e);
		}
		enemies.add(e);
	}
	
	/**
	 * Visszater a Path-en levo ellenseg(ek)el.
	**/
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	
	}
	
	public IObstacle getIObstacle(){
		return myIObstacle;
	}
	
	/**
	 * Visszater a nextPaths lista egy elemevel, ahova az enemy majd lephet.
	**/
	public Path getNext() {
		if(igame.getRandom()==true){
//TODO random valasztas
			return null;
		}else{
//TODO nem random valasztas
			return nextPaths.get(0);
		}
		

	}
        
        public void determineNext(int direction) {
            
        }

	@Override
	public boolean isPath() {
		return true;
	}
	//ez nem kell mashogy csinaltam meg a loadban
//	public void addNext(Path p){
//		nextPaths.add(p);
//	}
}
