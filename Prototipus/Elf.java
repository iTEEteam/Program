package Program.Skeleton;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Elf.java
//  @ Date : 2014.03.11.
//  @ Author : itee_team
//
//




/**
 * Elf t�pus� Enemy
**/
public class Elf extends Enemy {
	public Elf(IGame game, Path p) {
		super(game, p);
		// TODO Auto-generated constructor stub
	}
	public void eliminate(Path p) {
	}
	public void registerPath(Path p) {
	}
	@Override
	public void hurt(Bullet b) {
		System.out.println("--> Elf hurt");
		
		b.getDamageElf();

		System.out.println("<-- Elf return");		
	}
}
