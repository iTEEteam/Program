package graph;


/**
 * Elf tipusu Enemy
 * 
 * @author Seres
**/
public class Elf extends Enemy {

	/**
	 * Konstruktor.
	 * 
	 * @param 	game Az IGame interfesz, amivel a Game-et eleri.
	**/
	public Elf(IGame game) {
		super(game, 1, 120);
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
		
		int damage = b.getDamageElf();
		
		if(damage == 0) {
			cut();
		} else {
			health -= damage;
		}
		
		if(health <= 0){
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
		
		Enemy enemy = new Elf(igame);
		enemy.setHealth(health);
		
		GEnemy ge = new GElf(enemy);
		enemy.addGEnemy(ge);
		
		igame.addEnemyIn(enemy);
		myPath.registerIPathPlaceable(enemy);
	}
}
