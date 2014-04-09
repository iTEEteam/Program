package proto;


/**
 * Human tipusu Enemy.
**/
public class Human extends Enemy {
	public Human(IGame game, Path p) {
		super(game, p);
		ProtoTester.addToObjectCatalog(this);
		// TODO Auto-generated constructor stub
	}
	public void eliminate(Path p) {
	}
	public void registerPath(Path p) {
	}
	@Override
	public void hurt(Bullet b) {
		b.getDamageHuman();
	}
}
