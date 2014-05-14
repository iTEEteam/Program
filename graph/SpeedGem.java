package graph;



public class SpeedGem extends Gem implements ITGem {
	public static final int price = 20;

	// ezt az erteket vonja majd le a torony sebessegebol
	// azert kell levonni, mert moduloval donti el, hogy lojjon e
	private int speed;
	
	public SpeedGem() {
		speed=1;
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
