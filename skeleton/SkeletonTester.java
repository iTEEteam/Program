package skeleton;

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
        System.out.println();
        System.out.println("0 Exit (Kilépés.)");
        System.out.println("1 ObstacleBuyOnEnemy (Akadály elhelyezése ellenség által foglalt útra.)");
        System.out.println("2 ObstacleBuyOnObstacle (Akadály elhelyezése másik akadály által foglalt útra.)");
        System.out.println("3 ObstacleBuy (Akadály elhelyezése üres útra.)");
        System.out.println("4 ObstacleBuyGems (Akadály feljesztése.)");
        System.out.println("5a EnemyMove (Ellenfél mozgása.)");
        System.out.println("5b EnemySucceeded (Ellenfél bejut a végzet hegyéhez");
        System.out.println("6 TowerSellNonUpgraded (Fejlesztetlen torony eladása.)");
        System.out.println("7 TowerSellUpgraded (Fejlesztett torony eladása.)");
        System.out.println("8 Initialize (Inicializálás.)");
        System.out.println("9 TowerBuyOnField (Torony elhelyezése foglalt mezőre.)");
        System.out.println("10 TowerBuy (Torony elhelyezése üres mezőre.)");
        System.out.println("11 TowerBuyGems (Torony feljesztése minden gemből eggyel.)");
        System.out.println("12 TowerShootNoEnemy (Torony hatókörében nincs ellenség tüzeléskor.)");
        System.out.println("13 TowerKillEnemy (Torony lelő egy ellenséget fejlesztetlenül.)");
        System.out.println("14 TowerShootEnemy (Torony tüzel egy ellenségre fejlesztetlenül.)");
        System.out.println();

        try {
			while(!(str = reader.readLine()).equals("0")){
                if(str.equals("ObstacleBuyOnEnemy") || str.equals("1")){
                    tester.ObstacleBuyOnEnemy();
                } else if(str.equals("ObstacleBuyOnObstacle") || str.equals("2")){
                    tester.ObstacleBuyOnObstacle();
                } else if(str.equals("ObstacleBuy") || str.equals("3")){
                    tester.ObstacleBuy();
                } else if(str.equals("ObstacleBuyGems") || str.equals("4")){
                    tester.ObstacleBuyGems();
                } else if(str.equals("EnemyMove") || str.equals("5a")){
                    tester.EnemyMove();
                } else if(str.equals("EnemySucceeded") || str.equals("5b")){ 
                	tester.EnemySucceeded();
				} else if(str.equals("TowerSellNonUpgraded") || str.equals("6")){
                    tester.TowerSellNonUpgraded();
                } else if(str.equals("TowerSellUpgraded") || str.equals("7")){
                    tester.TowerSellUpgraded();
                } else if(str.equals("Initialize") || str.equals("8")){
                    tester.Initialize();
                } else if(str.equals("TowerBuyOnField") || str.equals("9")){
					tester.TowerBuyOnField();
                } else if(str.equals("TowerBuy") || str.equals("10")){
                    tester.TowerBuy();
                } else if(str.equals("TowerBuyGems") || str.equals("11")){
                    tester.TowerBuyGems();
                } else if(str.equals("TowerShootNoEnemy") || str.equals("12")){
                    tester.TowerShootNoEnemy();
                } else if(str.equals("TowerKillEnemy") || str.equals("13")){
                    tester.TowerKillEnemy();
				} else if(str.equals("TowerShootEnemy") || str.equals("14")){
					tester.TowerShootEnemy();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("VEGE");		
	}
	
	// Fejlesztett torony eladása.	
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
	
	// Fejlesztetlen torony eladása.
	private void TowerSellNonUpgraded(){
		Game game = new Game();
		Field field = new Field(game);
		Tower tower = new Tower(game);
		tower.registerField(field);
		
		SkeletonTester.isPrinting = true;
		tower.sell();
		SkeletonTester.isPrinting = false;

	}
	
	// Fejlesztetlen torony, hatósugarában egy ellenség van.
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
	
	// Torony hatókörében nincs ellenség tüzeléskor
	private void TowerShootNoEnemy(){
		Game game = new Game();
		Path path = new Path();
		Tower tower = new Tower(game);
		tower.addPath(path);
		
		SkeletonTester.isPrinting = true;
		tower.shoot();
		SkeletonTester.isPrinting = false;
	}
	
	// Torony megöl egy ellenséget.
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
	
	// Egy torony elhelyezése a pályán, ami masik torony altal el van foglalva
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
		controller.buyEnemyTypeGem();
		SkeletonTester.isPrinting = false;
		
	}
	
	// Akadály elhelyezése olyan mezőre amin ellenség van.
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
	
	// Akadály elhelyezése üres útra.	
	private void ObstacleBuy(){
		Game game = new Game();
		Controller controller = new Controller(game);
		controller.setPath(new Path());
		game.changeMana(100);
		
		SkeletonTester.isPrinting = true;
		controller.buyObstacle();
		SkeletonTester.isPrinting = false;
	}
	
	// Egy torony elhelyezése a pályán.
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

	// Ellenség léptetése.
	private void EnemyMove(){
		Game game = new Game();
		Path path = new Path();
		Enemy enemy = new Hobbit(game, path);
		path.addNext(new Path());
		
		SkeletonTester.isPrinting = true;
		enemy.move();
		SkeletonTester.isPrinting = false;
	}
	
	// Annak tesztelése, hogy ha bejut egy ellenség a végzet hegyéhez.
	private void EnemySucceeded(){
		Game game = new Game();
		Path path = new Path();
		Enemy enemy = new Hobbit(game, path);
		path.addNext(null);
		
		SkeletonTester.isPrinting = true;
		enemy.move();
		SkeletonTester.isPrinting = false;
	}
	
	// A játék kezdeti betöltésének lefuttatása.
	private void Initialize(){
		Application app = new Application();
		
		SkeletonTester.isPrinting = true;
		app.Initialize();
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
 * Enemy - uj private Path nextPath valtozo
 * Path - Obstacle myPath valtozo nevet atirtam myObstacle-re
 * Application - kapott egy void Initialize() fuggvenyt
 * Map konstruktoraban nem stringet hanem mapot kap
 * EnemyTypeGem, Tower, Bullet, Controller - Stringre atirtam a tarolt type-ot, fv parametereket 
 */
