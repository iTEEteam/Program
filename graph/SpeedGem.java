package graph;



public class SpeedGem extends Gem implements ITGem {
	public static final int price = 20;

	private int speed;
	
	public SpeedGem() {
		speed=10;
		ProtoTester.addToObjectCatalog(this);
	}
	
	/**
	 * Fejlesztjuk a tornyot.
	 */
	public void upgradeTower(Tower t) {
		t.upgradeSpeed(speed);
	}
	
	/**
	 * Torony eladasakor kalkulalt osszeghez kell az ar. Ezt adja.
	 */
	public int getValue() {
		return price;
	}
}
