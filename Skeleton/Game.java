package Program.Skeleton;
import java.util.ArrayList;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Game.java
//  @ Date : 2014.03.11.
//  @ Author : itee_team
//
//

public class Game implements IGame {
	/**
	 * A t�rk�p, amiben a cell�k t�rol�dnak.
	**/
	private Map map;
	
	/**
	 * Az ellens�gek list�ja, akik az adott p�ly�ra bel�pnek.
	**/
	private ArrayList<Enemy> enemiesIn;
	
	private ArrayList<Enemy> enemiesOut;
	
	private ArrayList<Tower> towers;
	
	/**
	 * A j�t�kos var�zsereje, amib�l akad�lyokat, tornyokat vehet, ill. ami minden meg�lt ellens�g ut�n gyarapszik.
	**/
	private int mana;
	
	private Path firstP;
	
	private int noEnemies;
	
	private int succeededE;
	
	/**
	 * Konstruktor
	**/
	public void Game() {
	
	}
	
	/**
	 * Friss�ti a GUI-t.
	**/
	public void update() {
	
	}
	
	public void makeEnemies() {
	
	}
	
	public void initialize(String name) {
	
	}
	
	/**
	 * A man�t cs�kkent�/j�v��r� f�ggv�ny.
	**/
	public void changeMana(int value) {
		SkeletonTester.safePrint("--> Game change mana", true);
		
		SkeletonTester.safePrint("<-- Game return", false);
	}
	
	public void incSucceeded() {
	}
	
	public void addTower(Tower t) {
	}
	
	public void removeTower(Tower t) {
		SkeletonTester.safePrint("--> Game remove tower", true);
		

		SkeletonTester.safePrint("<-- Game return", false);
	}
	
	public void removeEnemy(Enemy e) {
	}

}
