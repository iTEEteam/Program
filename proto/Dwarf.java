package proto;



/**
 * Dwarf t�pus� Enemy
 * 
 * @author Seres
**/
public class Dwarf extends Enemy {
	
	/**
	 * A t�rp hp-ja.
	 */
	private static final int maxHP = 50;
	
	/**
	 * Konstruktor.
	 *
	 * @param 	game Az IGame interf�sz, amivel a Game-et el�ri.
	 * @param 	p A l�trehoz�s helye. Felesleges, mert �gysem �ton hozzuk l�tre.
	**/
	public Dwarf(IGame game, Path p) {
		super(game, p);
		ProtoTester.addToObjectCatalog(this);
	}
	
	/**
	 * V�ltoztatja a man�t, t�rli az Enemyt az �tr�l �s a Game-b�l.
	 */
	@Override
	public void eliminate() {
		
		super.eliminate();
		
		igame.changeMana(maxHP);
	}	
	
	/**
	 * Sebz�st v�gz� f�ggv�ny.
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

