package proto;



public class RangeGem extends Gem implements ITGem {
	public static final int price = 20;

	private int range;
	public RangeGem() {
		ProtoTester.addToObjectCatalog(this);

	}
	public void upgradeTower(Tower t) {
		t.upgradeRange(range);
	}
	
	public int getValue() {

		return 0;
	}
}
