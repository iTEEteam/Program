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
		choosenPath = null;
		choosenEnemy = null;
		choosenField = field;
	}
	
	public void setPath(Path path){
		choosenEnemy = null;
		choosenField = null;
		choosenPath = path;

	}
	
	public void setEnemy(String e){
		choosenPath = null;
		choosenField = null;
		choosenEnemy = e;
	}
	
	public void buyTower(){
		int value;
		
		if(choosenField.hasTower()){
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
		

	}
	
	public void buyObstacle(){
		int value;
		
		if(choosenPath.hasObstacle()){
			return;
		}
		
		if(choosenPath.hasEnemy()){
			return;
		}
		
		value = Obstacle.price;
		
		if(igame.getMana()>=value){
			igame.changeMana(-value);
			
			choosenPath.registerIPathPlaceable(new Obstacle());

		}
		
		
	}
	
	public void buySpeedGem(){
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
	}
		
	public void buyRangeGem(){
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
	}
	public void buyDamageGem(){
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

	}
	
	public void buyEnemyTypeGem(){
		int value;

		if(choosenField.hasTower()){
			value = EnemyTypeGem.price;
			
			if(igame.getMana()>=value){
				igame.changeMana(-value);
								
				ITower itower = choosenField.getITower();
						
				
				itower.addITGem(new EnemyTypeGem(choosenEnemy));
			}
		}
	}
	
	public void buyIntensityGem(){
		int value;
				
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
	}
	
	public void buyRepairGem(){
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
	}
}
