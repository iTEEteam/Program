package proto;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : RepairGem.java
//  @ Date : 2014.03.11.
//  @ Author : itee_team
//
//




public class RepairGem extends Gem implements IOGem {
	public static final int price = 20;

	public  RepairGem() {

	}
	
	public void upgradeObstacle(Obstacle o) {
		
		o.repair();
		
	}
}
