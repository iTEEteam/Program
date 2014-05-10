package graph;

import java.util.ArrayList;
import java.util.Random;

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
	
//	private Path forcedNextPath;
	
	// ez is WTF?
	//private Enemy myPath;
	
	//private Tower paths;
	
	/**
	 * Konstruktor
	**/
	public Path(IGame igame) {
		super(igame);
		
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
		((GPath)iview).deleteGEnemy(e);
		iview.gNotify();
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
		((GPath)iview).addGEnemy(e.getGEnemy());
		iview.gNotify();
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
		Path nextPath = null;
//		if(forcedNextPath!=null){
//			nextPath = forcedNextPath;
//		}else 
		
		// ha minden iranyba null, akkor adja vissza h null
		boolean bLastPath = false; // ha marad hamis, akkor ez a vegzet hegye
		boolean tb[] = new boolean[nextPaths.size()]; // ez tarolja, hogy egyes helyeken null van-e
		for(int i = 0; i<nextPaths.size(); ++i){
			// ha null, akkor hamis van a helyen
			tb[i] = (nextPaths.get(i)!=null);
			System.out.println(tb[i]);
			if(tb[i]==true) {
				bLastPath = true;
			}
		}
		// ha mind hamis volt, ez a vegzet hegye
		if(!bLastPath)
			return null;
		
		
		/*
		 * ha van merre tovabbmenni kivalaszt egy random indexet a nextPaths-tombre,
		 * elkezd egy iteraciot amiben addig megy vegig a tombon, a random indextol,  
		 * amig nem talal utat amire lephet
		 * az index a tomb meretevel modulo, szoval ha kierne a tombbol az elso helyen folytatja
		 */
		Random rand = new Random();
		int ind = rand.nextInt(nextPaths.size());
		for(int i=0; nextPath==null; ++i){
			if(tb[(ind+i)%nextPaths.size()]==true){
				nextPath = nextPaths.get((ind+i)%nextPaths.size());
			}
		}

//		forcedNextPath = null;
		return nextPath;

	}
        
//        public void determineNext(int direction) {
//            forcedNextPath = null;
//            switch(direction){
//            case 0:
//            	if(neighbours.get(0).isPath()){
//            		forcedNextPath = (Path) neighbours.get(0);
//            	}
//            	break;
//            case 1:
//            	if(neighbours.get(1).isPath()){
//            		forcedNextPath = (Path) neighbours.get(1);
//            	}
//            	break;
//            case 2:
//            	if(neighbours.get(2).isPath()){
//            		forcedNextPath = (Path) neighbours.get(2);
//            	}
//            	break;
//            case 3:
//            	if(neighbours.get(3).isPath()){
//            		forcedNextPath = (Path) neighbours.get(3);
//            	}
//            	break;
//            	
//            	
//            }
//        }

	@Override
	public boolean isPath() {
		return true;
	}
	//ez nem kell mashogy csinaltam meg a loadban
//	public void addNext(Path p){
//		nextPaths.add(p);
//	}
	
	public ArrayList<Path> getNextPaths() {
		return nextPaths;
	}
}
