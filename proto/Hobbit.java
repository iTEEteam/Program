package proto;


/**
 * Hobbit tipusu Enemy
 * 
 * @author Seres
**/ 
public class Hobbit extends Enemy {
	
	/**
	 * A hobbit hp-ja.
	 */
	private static final int maxHP = 50;
	
	/**
	 * Konstruktor.
	 * 
	 * @param 	game Az IGame interf�sz, amivel a Game-et el�ri.
	 * @param 	p A l�trehoz�s helye. Felesleges, mert �gysem �ton hozzuk l�tre.
	**/
	public Hobbit(IGame game, Path p) {
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
		
		int damage = b.getDamageHobbit();
		
		health -= damage;
		
		if(health <= 0){
			eliminate();
		}
	}
 
}
