package Program.Skeleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class SkeletonTester {

	public static void main(String[] args) {
		SkeletonTester tester = new SkeletonTester();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String str;
		
		System.out.println("M E N U");
		
		try {
			while(!(str = reader.readLine()).equals("0")){
				if(str.equals("test sequence")){
					tester.testSequence();
				} else if(str.equals("TowerSellNonUpgraded")){
					tester.TowerSellNonUpgraded();
				} else if(str.equals("TowerSellUpgraded")){
					tester.TowerSellUpgraded();
				} else if(str.equals("TowerShootEnemy")){
					tester.TowerShootEnemy();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("VEGE");		
	}
	
	public void testSequence(){
		System.out.println("--> testSequence");
		
		System.out.println("<-- testSequence return");
	}
	
	public void TowerSellUpgraded(){
		Game game = new Game();
		Field field = new Field(game);
		Tower tower = new Tower(game, field);
		tower.addITGem(new RangeGem());
		tower.addITGem(new DamageGem());
		
		tower.sell();
	}
	
	public void TowerSellNonUpgraded(){
		Game game = new Game();
		Field field = new Field(game);
		Tower tower = new Tower(game, field);
		
		tower.sell();
	}
	
	public void TowerShootEnemy(){
		Game game = new Game();
		Field field = new Field(game);
		Path path = new Path();
		path.registerEnemy(new Hobbit());
		Tower tower = new Tower(game, field);
		tower.addPath(path);
		
		tower.shoot();
	}

}
