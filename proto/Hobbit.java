package proto;


/**
 * Hobbit tipusu Enemy
**/
public class Hobbit extends Enemy {
	private static final int maxHP = 50;

	public Hobbit(IGame game, Path p) {
		super(game, p);
		ProtoTester.addToObjectCatalog(this);

	}
	
	// TODO minek a Path p amikor ugyis a sajatjarol veszi le? IPP miatt?
	public void eliminate(Path p) {
		
		igame.changeMana(maxHP);
		igame.removeEnemy(this);
		myPath.deleteEnemy(this);
		
	}

	@Override
	public void hurt(Bullet b) {
		
		int damage = b.getDamageHobbit();
		
		health -= damage;
		if(health <= 0){
			eliminate(myPath);
		}
		
	}
}
