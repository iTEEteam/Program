package proto;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Field.java
//  @ Date : 2014.03.11.
//  @ Author : itee_team
//
//




public class Field extends Cell {
	/**
	 * A mez�n l�v�, torony interf�sz� v�ltoz� referenci�ja.
	**/
	private ITower itower;
	
	private IGame igame;
	
	private Tower myField;
	
	/**
	 * Konstruktor
	**/
	public Field(IGame game) {
		ProtoTester.addToObjectCatalog(this);
		igame = game;
	}
	
	/**
	 * T�rli a param�terk�nt kapott IFieldPlaceable interf�sz� v�ltoz�t a mez�r�l. 
	 *
	 * @param    ifp
	**/
	public boolean deleteIFieldPlaceable(IFieldPlaceable ifp) {
		return false;
	
	}
	
	/**
	 * Beregisztr�lja a kapott IFieldPlaceable interf�sz� v�ltoz�t a mez�n.
	 *
	 * @param    ifp
	**/
	public void registerIFieldPlaceable(IFieldPlaceable ifp) {
		
		ifp.registerField(this);
	}
	
	/**
	 * Beregisztr�lja a torony interf�sz� v�ltoz�t a mez�n.
	 *
	 * @param    it
	**/
	public void registerITower(ITower it) {
		if(!hasTower()){
			itower = it;
		}
		
	}
	
	public boolean isPath(){
	  return false;
	}

	public boolean hasTower() {
		ProtoTester.safePrint("--> Field hasTower", true);
		ProtoTester.safePrint("<-- Field hasTower return", false);
		return (itower!=null);	
	}
	
	public ITower getITower(){
		ProtoTester.safePrint("--> Field getITower", true);
		
		ProtoTester.safePrint("<-- Field getITower return", false);
		return itower;
	}
}
