package proto;


/**
 * Human tipusu Enemy.
 * 
 * @author Seres
**/
public class Human extends Enemy {
	
	/**
	 * Az ember HP-ja.
	 */
	private static final int maxHP = 50;
	
	/**
	 * Konstruktor.
	 * 
	 * @param 	game Az IGame interfész, amivel a Game-et eléri.
	 * @param 	p A létrehozás helye. Felesleges, mert úgysem úton hozzuk létre.
	**/
	public Human(IGame game, Path p) {
		super(game, p);
		ProtoTester.addToObjectCatalog(this);
	}
	
	/**
	 * Változtatja a manát, törli az Enemyt az útról és a Game-bõl.
	 */
	@Override
	public void eliminate() {
		
		super.eliminate();
		
		igame.changeMana(maxHP);
	}
	
	/**
	 * Sebzést végzõ függvény.
	 */
	@Override
	public void hurt(Bullet b) {
		
		int damage = b.getDamageHuman();
		
		health -= damage; 
		
		if(health <= 0) {
			eliminate();
		}
	}
}
