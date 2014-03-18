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
	
	// WTF?
	private Obstacle myPath;
	
	// ez is WTF?
	//private Enemy myPath;
	
	private Tower paths;
	
	/**
	 * Konstruktor
	**/
	public Path() {
		enemies = new ArrayList<Enemy>();
	}
	
	/**
	 * A bool t�pus� visszat�r�si �rt�kben megmondja, hogy van-e ellens�g a Path-en. 
	**/
	public boolean hasEnemy() {
		return !enemies.isEmpty();
	
	}
	
	/**
	 * T�rli a param�terben kapott IPathPlaceable interf�sz� v�ltoz�t a Path-r�l. 
	 *
	 * @param    e
	**/
	public boolean deleteEnemy(Enemy e) {
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
	
	}
	
	/**
	 * Beregisztr�lja a param�terben kapott IObstacle interf�sz� v�ltoz�t a Path-en. 
	 *
	 * @param    io
	**/
	public void registerIObstacle(IObstacle io) {
	
	}
	
	/**
	 * Beregisztr�lja a param�terben kapott Enemy t�pus� v�ltoz�t a Path-en. 
	 *
	 * @param    e
	**/
	public void registerEnemy(Enemy e) {
		enemies.add(e);
	}
	
	/**
	 * Visszat�r a Path-en l�v� ellens�g(ek)el.
	**/
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	
	}
	
	/**
	 * Visszat�r a nextPaths lista egy elem�vel, ahova az enemy majd l�phet.
	**/
	public Path getNext() {
		return null;
	
	}

	@Override
	public boolean isPath() {
		return true;
	}
}
