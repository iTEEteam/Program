package proto;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Application.java
//  @ Date : 2014.03.11.
//  @ Author : itee_team
//
//


public class Application {
	//default constructor
	public Application(){
		ProtoTester.addToObjectCatalog(this);
	}
	
	
	public void Initialize(){
		Game game = new Game();
		game.initialize("MapName");
		
	}

}