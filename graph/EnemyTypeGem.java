package graph;


public class EnemyTypeGem extends Gem implements ITGem {
	public static final int price = 20;

	private String eType;
	public EnemyTypeGem(String enemyType) {
		eType = enemyType;
	}
	
	/**
	 * Enemy tipusra fejlesztjuk a tornyot.
	 */
	public void upgradeTower(Tower t) {
		t.upgradeEnemy(eType);
	}
	
	/**
	 * Mana jovairasahoz, torony eladasakor szamitando ertekhez.
	 */
	public int getValue() {
		return price;
	}
}
