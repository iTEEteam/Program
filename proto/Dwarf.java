package proto;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Dwarf.java
//  @ Date : 2014.03.11.
//  @ Author : itee_team
//
//




/**
 * Dwarf t�pus� Enemy
**/
public class Dwarf extends Enemy {
	public Dwarf(IGame game, Path p) {
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
			
		b.getDamageDwarf();

	}
}
