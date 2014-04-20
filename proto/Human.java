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
	 * @param 	game Az IGame interfesz, amivel a Game-et eleri.
	 * @param 	p A letrehozas helye. Felesleges, mert ugysem uton hozzuk letre.
	**/
	public Human(IGame game, Path p) {
		super(game, p);
		ProtoTester.addToObjectCatalog(this);
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
		
		int damage = b.getDamageHuman();
		
		ProtoTester.safePrint(ProtoTester.getKeyByValue(this) + " damage " + damage);
		
		if(damage == 0) {
			cut();
		} else {
			health -= damage;
		}
		
		if(health <= 0) {
			ProtoTester.safePrint(ProtoTester.getKeyByValue(this) + " died");
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
		
		Enemy enemy = new Human(igame, myPath);
		enemy.setHealth(health);
		
		igame.addEnemyIn(enemy);
		myPath.registerIPathPlaceable(enemy);
	}
}
