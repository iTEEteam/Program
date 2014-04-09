package proto;



public class RepairGem extends Gem implements IOGem {
	public static final int price = 20;

	public  RepairGem() {
		ProtoTester.addToObjectCatalog(this);
	}
	
	public void upgradeObstacle(Obstacle o) {
		
		o.repair();
		
	}
}
