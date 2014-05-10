package graph;



public class RangeGem extends Gem implements ITGem {
	public static final int price = 20;

	private int range;
	public RangeGem() {
		range=2;

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
