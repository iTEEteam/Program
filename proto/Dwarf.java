package proto;



/**
 * Dwarf tï¿½pusï¿½ Enemy
 * 
 * @author Seres
**/
public class Dwarf extends Enemy {
	
	/**
	 * A törp hp-ja.
	 */
	private static final int maxHP = 50;
	
	/**
	 * Konstruktor.
	 *
	 * @param 	game Az IGame interfész, amivel a Game-et eléri.
	 * @param 	p A létrehozás helye. Felesleges, mert úgysem úton hozzuk létre.
	**/
	public Dwarf(IGame game, Path p) {
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
			
		int damage = b.getDamageDwarf();
		
		if(damage == 0) {
			cut();
		} else {
			health -= damage;
		} 
		
		if(health <= 0) {
			eliminate();
		}
	}
	
	@Override
	public void cut() {
		health /= 2;
		
		Enemy enemy = new Dwarf(igame, myPath);
		enemy.setHealth(health);
		
		igame.addEnemyIn(enemy);
		myPath.registerIPathPlaceable(enemy);
	}
}

