package proto;



public class RangeGem extends Gem implements ITGem {
	public static final int price = 20;

	private int range;
	public RangeGem() {
		ProtoTester.addToObjectCatalog(this);

	}
	
	/**
	 * Fejlesztjuk a tornyot.
	 */
	public void upgradeTower(Tower t) {
		t.upgradeRange(range);
	}
	
	/**
	 * Visszaasjuk az arat.
	 */
	public int getValue() {
		return price;
	}
}
