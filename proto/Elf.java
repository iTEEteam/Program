package proto;



/**
 * Elf t�pus� Enemy
**/
public class Elf extends Enemy {
	public Elf(IGame game, Path p) {
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
		
		b.getDamageElf();

	}
}
