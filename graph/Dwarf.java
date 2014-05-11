package graph;


/**
 * Dwarf tipusu Enemy
 * 
 * @author Seres
**/
public class Dwarf extends Enemy {
	
	/**
	 * A torp hp-ja.
	 */
	private static final int maxHP = 80;

	/**
	 * Konstruktor.
	 * 
	 * @param 	game Az IGame interfesz, amivel a Game-et eleri.
	**/
	public Dwarf(IGame game) {
		super(game, 13, 100);
	}
	
	/**
	 * Valtoztatja a manat, torli az Enemyt az utrol es a Game-bol.
	 */
	@Override
	public void eliminate() {
		
		super.eliminate();
		
		igame.changeMana(maxHP);
	}	
	
	/**
	 * Sebzest vegzo fuggveny.
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
	
	/**
	 * A kettevagast vegzo fuggveny. 
	 */
	@Override
	public void cut() {
		/* A super.cut hivasra a kiiratas miatt van szukseg, igy valamivel egysegesebb.*/
		super.cut();
		
		health /= 2;
		
		Enemy enemy = new Dwarf(igame);
		enemy.setHealth(health);
		
		igame.addEnemyIn(enemy);
		myPath.registerIPathPlaceable(enemy);
	}
}

