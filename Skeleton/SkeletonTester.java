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
				if(str.equals("TowerPutOnOccupiedField")){
					tester.TowerPutOnOccupiedField();
				} else if(str.equals("TowerSellNonUpgraded")){
					tester.TowerSellNonUpgraded();
				} else if(str.equals("TowerSellUpgraded")){
					tester.TowerSellUpgraded();
				} else if(str.equals("TowerShootEnemy")){
					tester.TowerShootEnemy();
				} else if(str.equals("TowerShootNoEnemy")){
					tester.TowerShootNoEnemy();
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
	
	public void TowerPutOnOccupiedField(){
		Game game = new Game();
		Field field = new Field(game);
		Tower tower1 = new Tower(game);
		Tower tower2 = new Tower(game);
		
		tower1.registerField(field);
		
		SkeletonTester.isPrinting = true;
		tower2.registerField(field);
		SkeletonTester.isPrinting = false;

	}

}
