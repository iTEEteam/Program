package proto;

public class Controller {
	private IGame igame;
	private Field choosenField;
	private Path choosenPath;
	private String choosenEnemy;

	public Controller(IGame game){
		ProtoTester.addToObjectCatalog(this);
		igame = game;
	}
	
	// ha kivalaszt a jatekos egy mezot akkor a kivalasztott utat/ellenseget el kell felejtenie
	public void setField(Field field){
		choosenPath = null;
		choosenEnemy = null;
		choosenField = field;
	}
	
	// ha kivalaszt a jatekos egy utat akkor a kivalasztott mezot/ellenseget el kell felejtenie
	public void setPath(Path path){
		choosenEnemy = null;
		choosenField = null;
		choosenPath = path;

	}
	
	// ha kivalaszt a jatekos egy ellenseget akkor a kivalasztott utat/mezot el kell felejtenie
	public void setEnemy(String e){
		choosenPath = null;
		choosenField = null;
		choosenEnemy = e;
	}
	
	// torony vetele a kivalasztott mezore
	public void buyTower(){
		int value;
				
		// meg kell vizsgalni, hogy van e kivalasztott mezo
		if(choosenField == null){
			ProtoTester.safePrint("No choosen field");
			return;
		}
		
		// a mezon van e mar torony
		if(choosenField.hasTower()){
			ProtoTester.safePrint("Cell occupied");
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
		} else 
			ProtoTester.safePrint("Not enough mana");
	}
	
	
	
	public void buyObstacle(){
		int value;
		
		if(choosenPath == null){
			ProtoTester.safePrint("No choosen path");
		}
		
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
	/* kulonbozo kristalyok vetele
	 * mindegyiknel vizsgalja, hogy jo tipusu cella van e kivalasztva, 
	 * amin van e megfelelo fejlesztheto objektum
	 * ha van eleg mana, akkor letrehoz, es rarak az objektumra a megfelelo kristalybol
	 */
	public void buySpeedGem(){
		int value;

		if(choosenField != null && choosenField.hasTower()){
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

		if(choosenField != null && choosenField.hasTower()){
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

		if(choosenField != null && choosenField.hasTower()){
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

		if(choosenField != null && choosenField.hasTower()){
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
		if(choosenPath != null && choosenPath.hasObstacle()){
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

		if(choosenPath != null && choosenPath.hasObstacle()){
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
