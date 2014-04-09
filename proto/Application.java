package proto;

import java.io.FileNotFoundException;



public class Application {
	//default constructor
	public Application(){
		ProtoTester.addToObjectCatalog(this);
	}
	
	
	public void Initialize(){
		Game game = new Game();
		try {
			game.initialize("MapName");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
