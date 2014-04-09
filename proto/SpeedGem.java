package proto;



public class SpeedGem extends Gem implements ITGem {
	public static final int price = 20;

	private int speed;
	
	public SpeedGem() {
		ProtoTester.addToObjectCatalog(this);
	}
	
	public void upgradeTower(Tower t) {
		t.upgradeSpeed(speed);
	}
	
	public int getValue() {
		return 0;
	}
}
