package proto;


public class EnemyTypeGem extends Gem implements ITGem {
	public static final int price = 20;

	private String eType;
	public EnemyTypeGem(String enemyType) {
		ProtoTester.addToObjectCatalog(this);
		eType = enemyType;
	}
	
	public void upgradeTower(Tower t) {
		t.upgradeEnemy(eType);
	}
	
	public int getValue() {
		return 0;
	}
}
