package Program.Skeleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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
				} else if(str.equals("ObstacleBuyGems")){
					tester.ObstacleBuyGems();
				} else if(str.equals("EnemyMove")){
					
				} else if(str.equals("Initialize")){
					
				} else if(str.equals("TowerBuyGems")){
					tester.TowerBuyGems();
				} else if(str.equals("TowerKillEnemy")){
					tester.TowerKillEnemy();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("VEGE");		
	}
	
	private void TowerSellUpgraded(){
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
	
	private void TowerSellNonUpgraded(){
		Game game = new Game();
		Field field = new Field(game);
		Tower tower = new Tower(game);
		tower.registerField(field);
		
		SkeletonTester.isPrinting = true;
		tower.sell();
		SkeletonTester.isPrinting = false;

	}
	
	private void TowerShootEnemy(){
		Game game = new Game();
		Path path = new Path();
		path.registerEnemy(new Hobbit(game, path));
		Tower tower = new Tower(game);
		tower.addPath(path);
		
		SkeletonTester.isPrinting = true;
		tower.shoot();
		SkeletonTester.isPrinting = false;
	}
	
	private void TowerShootNoEnemy(){
		Game game = new Game();
		Path path = new Path();
		Tower tower = new Tower(game);
		tower.addPath(path);
		
		SkeletonTester.isPrinting = true;
		tower.shoot();
		SkeletonTester.isPrinting = false;
	}
	
	private void TowerKillEnemy(){
		Game game = new Game();
		Path path = new Path();
		Enemy hobbit = new Hobbit(game, path);
		hobbit.setHealth(5);
		path.registerEnemy(hobbit);
		Tower tower = new Tower(game);
		tower.addPath(path);
		
		SkeletonTester.isPrinting = true;
		tower.shoot();
		SkeletonTester.isPrinting = false;
		
	}
	
	private void TowerBuyOnField(){
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
	
	private void TowerBuyGems(){
		Game game = new Game();
		Field field = new Field(game);
		Controller controller = new Controller(game);
		controller.setField(field);
		game.changeMana(1000);
		controller.buyTower();
		
		SkeletonTester.isPrinting = true;
		controller.buySpeedGem();
		controller.buyRangeGem();
		controller.buyDamageGem();
		// TODO ez meg nem jo
		//controller.buyEnemyTypeGem();
		SkeletonTester.isPrinting = false;
		
	}
	
	/* szekvenciadiagramja hibas?? */
	private void ObstacleBuyOnEnemy(){
		Game game = new Game();
		Controller controller = new Controller(game);
		Path path = new Path();
		controller.setPath(path);
		path.registerEnemy(new Hobbit(game, path));
		
		SkeletonTester.isPrinting = true;
		controller.buyObstacle();
		SkeletonTester.isPrinting = false;
	}
	
	private void ObstacleBuyOnObstacle(){
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
	
	private void ObstacleBuy(){
		Game game = new Game();
		Controller controller = new Controller(game);
		controller.setPath(new Path());
		game.changeMana(100);
		
		SkeletonTester.isPrinting = true;
		controller.buyObstacle();
		SkeletonTester.isPrinting = false;
	}
	
	private void TowerBuy(){
		Game game = new Game();
		Controller controller = new Controller(game);
		controller.setField(new Field(game));
		game.changeMana(100);
		
		SkeletonTester.isPrinting = true;
		controller.buyTower();		
		SkeletonTester.isPrinting = false;
	}
	
	private void ObstacleBuyGems() {
		Game game = new Game();
		Controller controller = new Controller(game);
		controller.setPath(new Path());
		game.changeMana(100);	
		controller.buyObstacle();
		
		SkeletonTester.isPrinting = true;
		controller.buyIntensityGem();
		controller.buyRepairGem();
		SkeletonTester.isPrinting = false;
	}

}

/* VALTOZTATASOK:
 * 
 * Controller osztaly - letezik
 * Tower.price - publikus
 * Obstacle.price - publikus
 * Obstacle konstruktoranak kivettem a parametereit
 * Path - uj metodus: public IObstacle getIObstacle
 * Field - uj metodus: public ITower getITower
 * Enemy - health, myPath, igame lathatosaga protected
 * Enemy - private static final int maxHP
 * Enemy - setHealth(int) csak teszteles celjabol
 */
