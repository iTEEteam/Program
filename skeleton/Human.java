package skeleton;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Human.java
//  @ Date : 2014.03.11.
//  @ Author : itee_team
//
//




/**
 * Human t�pus� Enemy.
**/
public class Human extends Enemy {
	public Human(IGame game, Path p) {
		super(game, p);
		// TODO Auto-generated constructor stub
	}
	public void eliminate(Path p) {
	}
	public void registerPath(Path p) {
	}
	@Override
	public void hurt(Bullet b) {
		System.out.println("--> Human hurt");
		b.getDamageHuman();
		System.out.println("<-- Human return");		
	}
}