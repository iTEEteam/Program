package proto;

public class DamageGem extends Gem implements ITGem {
	public static final int price = 20;

	private int damage;
	public DamageGem() {
		ProtoTester.addToObjectCatalog(this);
	}
	
	public void upgradeTower(Tower t) {
		t.upgradeDamage(damage);
				
	}
	
	public int getValue() {

		return 0;
	}
}
