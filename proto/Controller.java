package proto;

public class Controller {
	private IGame igame;
	private Field choosenField;
	private Path choosenPath;
	private String choosenEnemy;

	public Controller(IGame game){
		igame = game;
	}
	
	public void setField(Field field){
		choosenField = field;
	}
	
	public void setPath(Path path){
		choosenPath = path;
	}
	
	public void setEnemy(String e){
		choosenEnemy = e;
	}
	
	public void buyTower(){
		ProtoTester.safePrint("--> Controller buyTower", true);
		int value;
		
		if(choosenField.hasTower()){
			ProtoTester.safePrint("<-- Controller buyTower return", false);
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
		

		ProtoTester.safePrint("<-- Controller buyTower return", false);
	}
	
	public void buyObstacle(){
		ProtoTester.safePrint("--> Controller buyObstacle", true);
		int value;
		
		if(choosenPath.hasObstacle()){
			ProtoTester.safePrint("<-- Controller buyObstacle return", false);
			return;
		}
		
		if(choosenPath.hasEnemy()){
			ProtoTester.safePrint("<-- Controller buyObstacle return", false);
			return;
		}
		
		value = Obstacle.price;
		
		if(igame.getMana()>=value){
			igame.changeMana(-value);
			
			choosenPath.registerIPathPlaceable(new Obstacle());

		}
		
		
		ProtoTester.safePrint("<-- Controller buyObstacle return", false);
	}
	
	public void buySpeedGem(){
		ProtoTester.safePrint("--> Controller buySpeedGem", true);
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
		ProtoTester.safePrint("<-- Controller buySpeedGem return", false);
	}
		
	public void buyRangeGem(){
		ProtoTester.safePrint("--> Controller buyRangeGem", true);
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
		ProtoTester.safePrint("<-- Controller buyRangeGem return", false);
	}
	public void buyDamageGem(){
		ProtoTester.safePrint("--> Controller buyDamageGem", true);
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
		ProtoTester.safePrint("<-- Controller buyDamageGem return", false);

	}
	
	public void buyEnemyTypeGem(){
		ProtoTester.safePrint("--> Controller buyEnemyGem", true);
		int value;

		if(choosenField.hasTower()){
			value = EnemyTypeGem.price;
			
			if(igame.getMana()>=value){
				igame.changeMana(-value);
								
				ITower itower = choosenField.getITower();
						
				
				itower.addITGem(new EnemyTypeGem(choosenEnemy));
			}
		}
		ProtoTester.safePrint("<-- Controller buyEnemyGem return", false);
	}
	
	public void buyIntensityGem(){
		ProtoTester.safePrint("--> Controller buyIntensityGem", true);
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
		ProtoTester.safePrint("<-- Controller buyIntensityGem return", false);
	}
	
	public void buyRepairGem(){
		ProtoTester.safePrint("--> Controller buyRepairGem", true);
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
		ProtoTester.safePrint("<-- Controller buyRepairGem return", false);
	}
}
