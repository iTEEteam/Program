package graph;

public class Controller {
	private IGame igame;
	private Field choosenField;
	private Path choosenPath;
	private String choosenEnemy;
	private IView iview;

	public Controller(IGame game){
		ProtoTester.isPrinting = false;
		ProtoTester.addToObjectCatalog(this);
		ProtoTester.isPrinting = true;
		igame = game;
		iview = null;
	}
	
	// ha kivalaszt a jatekos egy mezot akkor a kivalasztott utat/ellenseget el kell felejtenie
	public void setField(Field field){
		choosenPath = null;
		choosenField = field;
	}
	
	// ha kivalaszt a jatekos egy utat akkor a kivalasztott mezot/ellenseget el kell felejtenie
	public void setPath(Path path){
		choosenField = null;
		choosenPath = path;
	}
	
	// ha kivalaszt a jatekos egy ellenseget akkor a kivalasztott utat/mezot el kell felejtenie
	public void setEnemy(String e){
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
			tower.setPaths();

		} else 
			ProtoTester.safePrint("Not enough mana");
	}
	
	
	
	public void buyObstacle(){
		int value;
		
		if(choosenPath == null){
			ProtoTester.safePrint("No choosen path");
			return;
		}
		
		if(choosenPath.hasObstacle()){
			ProtoTester.safePrint("Cell occupied");
			return;
		}
		
		if(choosenPath.hasEnemy()){
			ProtoTester.safePrint("Cell occupied");
			return;
		}
		
		value = Obstacle.price;
		
		if(igame.getMana()>=value){
			igame.changeMana(-value);
			
			choosenPath.registerIPathPlaceable(new Obstacle());

		} else {
			ProtoTester.safePrint("Not enough mana.");
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

			} else {
				ProtoTester.safePrint("Not enough mana");
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

			} else {
				ProtoTester.safePrint("Not enough mana");
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
			} else {
				ProtoTester.safePrint("Not enough mana");
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
			} else {
				ProtoTester.safePrint("Not enough mana");
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
				if(iObstacle == null) 
					ProtoTester.safePrint("No obstacle on road");
				else
					iObstacle.addIOGem(intensityGem);

			} else {
				ProtoTester.safePrint("Not enough mana");
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

			} else {
				ProtoTester.safePrint("Not enough mana");
			}
		}
	}
	
	/**
	 * Az ertesiteseket fogado objektumot allitja be. 
	 * 
	 * @param iview A grafikus jellegu esemenyek eseten ertesitendo objektum.
	 */
	public void setIView(IView iview) {
		this.iview = iview;
	}
	
	/**
	 * Az ertesiteseket fogado objektumot adja vissza. 
	 * 
	 * @return A grafikus jellegu esemenyek eseten ertesitendo objektum.
	 */
	public IView getIView() {
		return iview;
	}
	
	
}
