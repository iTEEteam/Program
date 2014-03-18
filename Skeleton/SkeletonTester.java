package Program.Skeleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.server.Skeleton;


public class SkeletonTester {
	/* FONTOS!!!
	 *  nrOfTabs - kiirt tabok szama
	 *  isPrinting - ki kell e a fvnek irnia az adatait
	 *  printTabs - kiirja a fv-eit 
	 *  	param b: 
	 *  		true - noveli a tabok szamat (fv hivas)
	 *  		false - csokkenti a tabok szamat (fv return)
	 *  safePrint - ezzel irassatok ki a fv adatait
	 *  	param b: printtabsnak adja at
	 *  	param str: kiirando szoveg  
	 */
	private static int nrOfTabs = -1;
	public static boolean isPrinting = false;
	private static void printTabs(boolean b){
		if(b==true) ++nrOfTabs;
		for(int i=0; i<nrOfTabs; ++i){
			System.out.print('\t');
		}
		if(b==false) --nrOfTabs;
	}
	public static void safePrint(String str, boolean b){
		if(isPrinting){
			printTabs(b);
			System.out.println(str);
		}
	}
	
	public static void main(String[] args) {
		SkeletonTester tester = new SkeletonTester();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String str;
		
		System.out.println("M E N U");
		
		try {
			while(!(str = reader.readLine()).equals("0")){
				if(str.equals("TowerBuyOnField")){
					tester.TowerBuyOnField();
				} else if(str.equals("TowerSellNonUpgraded")){
					tester.TowerSellNonUpgraded();
				} else if(str.equals("TowerSellUpgraded")){
					tester.TowerSellUpgraded();
				} else if(str.equals("TowerShootEnemy")){
					tester.TowerShootEnemy();
				} else if(str.equals("TowerShootNoEnemy")){
					tester.TowerShootNoEnemy();
				} else if(str.equals("ObstacleBuyOnEnemy")){
					tester.ObstacleBuyOnEnemy();
				} else if(str.equals("ObstacleBuyOnObstacle")){
					tester.ObstacleBuyOnObstacle();
				} else if(str.equals("ObstacleBuy")){
					tester.ObstacleBuy();
				} else if(str.equals("TowerBuy")){
					tester.TowerBuy();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("VEGE");		
	}
	
	public void TowerSellUpgraded(){
		Game game = new Game();
		Field field = new Field(game);
		Tower tower = new Tower(game);
		tower.registerField(field);
		tower.addITGem(new RangeGem());
		tower.addITGem(new DamageGem());
		
		SkeletonTester.isPrinting = true;
		tower.sell();
		SkeletonTester.isPrinting = false;		
	}
	
	public void TowerSellNonUpgraded(){
		Game game = new Game();
		Field field = new Field(game);
		Tower tower = new Tower(game);
		tower.registerField(field);
		
		SkeletonTester.isPrinting = true;
		tower.sell();
		SkeletonTester.isPrinting = false;

	}
	
	public void TowerShootEnemy(){
		Game game = new Game();
		Path path = new Path();
		path.registerEnemy(new Hobbit());
		Tower tower = new Tower(game);
		tower.addPath(path);
		
		SkeletonTester.isPrinting = true;
		tower.shoot();
		SkeletonTester.isPrinting = false;
	}
	
	public void TowerShootNoEnemy(){
		Game game = new Game();
		Path path = new Path();
		Tower tower = new Tower(game);
		tower.addPath(path);
		
		SkeletonTester.isPrinting = true;
		tower.shoot();
		SkeletonTester.isPrinting = false;
	}
	
	public void TowerBuyOnField(){
		Game game = new Game();
		Field field = new Field(game);
		Tower tower = new Tower(game);
		Controller controller = new Controller(game);
		controller.setField(field);
		tower.registerField(field);
		
		SkeletonTester.isPrinting = true;
		controller.buyTower();
		SkeletonTester.isPrinting = false;

	}
	
	/* szekvenciadiagramja hibas?? */
	public void ObstacleBuyOnEnemy(){
		Game game = new Game();
		Controller controller = new Controller(game);
		Path path = new Path();
		controller.setPath(path);
		path.registerEnemy(new Hobbit());
		
		SkeletonTester.isPrinting = true;
		controller.buyObstacle();
		SkeletonTester.isPrinting = false;
	}
	
	/* szekvenciadiagramja hibas?? */
	public void ObstacleBuyOnObstacle(){
		Game game = new Game();
		Controller controller = new Controller(game);
		Path path = new Path();
		controller.setPath(path);
		Obstacle obstacle1 = new Obstacle();
		path.registerIObstacle(obstacle1);
		
		SkeletonTester.isPrinting = true;
		controller.buyObstacle();
		SkeletonTester.isPrinting = false;
	}
	
	/* mi a fasz van ezekkel?  */
	public void ObstacleBuy(){
		Game game = new Game();
		Controller controller = new Controller(game);
		controller.setPath(new Path());
		game.changeMana(100);
		
		//TODO lsd Path.registerIObstacle()
		SkeletonTester.isPrinting = true;
		controller.buyObstacle();
		SkeletonTester.isPrinting = false;
	}
	
	public void TowerBuy(){
		Game game = new Game();
		Controller controller = new Controller(game);
		controller.setField(new Field(game));
		game.changeMana(100);
		
		SkeletonTester.isPrinting = true;
		controller.buyTower();
		SkeletonTester.isPrinting = false;
	}

}

/* VALTOZTATASOK:
 * 
 * Controller osztaly - letezik
 * Tower.price - publikus
 * Obstacle.price - publikus
 * Obstacle konstruktoranak kivettem a parametereit
 */
