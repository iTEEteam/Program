package Program.Skeleton;

public class Controller {
	private IGame igame;
	private Field choosenField;
	private Path choosenPath;
	private ITGem choosenEnemy;

	public Controller(IGame game){
		igame = game;
	}
	
	public void setField(Field field){
		choosenField = field;
	}
	
	public void setPath(Path path){
		choosenPath = path;
	}
	
	public void setEnemy(ITGem e){
		choosenEnemy = e;
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
		SkeletonTester.safePrint("--> Controller buySpeedGem", true);
		int value;

		if(choosenField.hasTower()){
			value = SpeedGem.price;
			
			if(igame.getMana()>=value){
				igame.changeMana(-value);
				
				ITGem speedGem = new SpeedGem();
				
				ITower itower = choosenField.getITower();
				
				itower.addITGem(speedGem);

			}
		}
		SkeletonTester.safePrint("<-- Controller buySpeedGem return", false);
	}
		
	public void buyRangeGem(){
		SkeletonTester.safePrint("--> Controller buyRangeGem", true);
		int value;

		if(choosenField.hasTower()){
			value = RangeGem.price;
			
			if(igame.getMana()>=value){
				igame.changeMana(-value);
				
				ITGem rangeGem = new RangeGem();
				
				ITower itower = choosenField.getITower();
				
				itower.addITGem(rangeGem);

			}
		}
		SkeletonTester.safePrint("<-- Controller buyRangeGem return", false);
	}
	public void buyDamageGem(){
		SkeletonTester.safePrint("--> Controller buyDamageGem", true);
		int value;

		if(choosenField.hasTower()){
			value = DamageGem.price;
			
			if(igame.getMana()>=value){
				igame.changeMana(-value);
				
				ITGem damageGem = new DamageGem();
				
				ITower itower = choosenField.getITower();
				
				itower.addITGem(damageGem);
			}
		}
		SkeletonTester.safePrint("<-- Controller buyDamageGem return", false);

	}
	
	public void buyEnemyTypeGem(){
		SkeletonTester.safePrint("--> Controller buyEnemyGem", true);
		int value;

		if(choosenField.hasTower()){
			value = EnemyTypeGem.price;
			
			if(igame.getMana()>=value){
				igame.changeMana(-value);
								
				ITower itower = choosenField.getITower();
				
				itower.addITGem(choosenEnemy);
			}
		}
		SkeletonTester.safePrint("<-- Controller buyEnemyGem return", false);
	}
	
	public void buyIntensityGem(){
		SkeletonTester.safePrint("--> Controller buyIntensityGem", true);
		int value;
				
		// ez nem kene?
//		if(choosenPath.hasEnemy()){
//			SkeletonTester.safePrint("<-- Controller buyObstacle return", false);
//			return;
//		}
		// ez tuti kell
		if(choosenPath.hasObstacle()){
			value = IntensityGem.price;
			
			if(igame.getMana()>=value){
				igame.changeMana(-value);
				
				IOGem intensityGem = new IntensityGem();
				
				IObstacle iObstacle = choosenPath.getIObstacle();
				
				iObstacle.addIOGem(intensityGem);

			}
		}
		SkeletonTester.safePrint("<-- Controller buyIntensityGem return", false);
	}
	
	public void buyRepairGem(){
		SkeletonTester.safePrint("--> Controller buyRepairGem", true);
		int value;

		if(choosenPath.hasObstacle()){
			value = RepairGem.price;
			
			if(igame.getMana()>=value){
				igame.changeMana(-value);
				
				IOGem repairGem = new RepairGem();
				
				IObstacle iObstacle = choosenPath.getIObstacle();
				
				iObstacle.addIOGem(repairGem);

			}
		}
		SkeletonTester.safePrint("<-- Controller buyRepairGem return", false);
	}
}
