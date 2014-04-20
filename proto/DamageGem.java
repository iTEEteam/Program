package proto;

public class DamageGem extends Gem implements ITGem {
	public static final int price = 20;

	private int damage;
	public DamageGem() {
		ProtoTester.addToObjectCatalog(this);
	}
	
	/**
	 * Fejlesztjuk a tuzeles erosseget.
	 */
	public void upgradeTower(Tower t) {
		t.upgradeDamage(damage);
				
	}
	
	/**
	 * Eladashoz kalkulalt jovairando ertekhez.
	 */
	public int getValue() {

		return price;
	}
}
