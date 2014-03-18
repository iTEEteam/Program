package Program.Skeleton;

public class Controller {
	private IGame igame;
	private Field choosenField;
	private Path choosenPath;

	public Controller(IGame game){
		igame = game;
	}
	
	public void setField(Field field){
		choosenField = field;
	}
	
	public void setPath(Path path){
		choosenPath = path;
	}
	
	public void buyTower(){
		SkeletonTester.safePrint("--> Controller buyTower", true);
		int value;
		
		if(choosenField.hasTower()){
			SkeletonTester.safePrint("<-- Controller buyTower return", false);
			return;
		}
		
		value = Tower.price;
		
		// ha van eleg mana akkor veszi meg
		if(igame.getMana() >= value){
			igame.changeMana(-value);
			
			Tower tower = new Tower(igame);
			igame.addTower(tower);
			
			// ez a fv az sd_Torony_elhelyez_ures_mezore kepen rosszul van irva
			choosenField.registerIFieldPlaceable(tower);
		}
		

		SkeletonTester.safePrint("<-- Controller buyTower return", false);
	}
	
	public void buyObstacle(){
		SkeletonTester.safePrint("--> Controller buyObstacle", true);
		int value;
		
		if(choosenPath.hasObstacle()){
			SkeletonTester.safePrint("<-- Controller buyObstacle return", false);
			return;
		}
		
		if(choosenPath.hasEnemy()){
			SkeletonTester.safePrint("<-- Controller buyObstacle return", false);
			return;
		}
		
		value = Obstacle.price;
		
		if(igame.getMana()>=value){
			igame.changeMana(-value);
			
			choosenPath.registerIPathPlaceable(new Obstacle());

		}
		
		
		SkeletonTester.safePrint("<-- Controller buyObstacle return", false);
	}
	
	public void buySpeedGem(){
		
	}
	public void buyRangeGem(){
		
	}
	public void buyDamageGem(){
		
	}
	
	public void buyEnemyTypeGem(){
		
	}
	
	public void buyIntensityGem(){
	
	}
	
	public void buyRepairGem(){
		
	}
}
