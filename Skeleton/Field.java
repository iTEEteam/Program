package skeleton;

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
	

	
	/**
	 * Konstruktor
	**/
	public Field(IGame game) {
		SkeletonTester.safePrint("--> Field konstruktor", true);
		igame = game;
		SkeletonTester.safePrint("<-- Field konstruktor return", false);

	}
	
	/**
	 * T�rli a param�terk�nt kapott IFieldPlaceable interf�sz� v�ltoz�t a mez�r�l. 
	 *
	 * @param    ifp
	**/
	public boolean deleteIFieldPlaceable(IFieldPlaceable ifp) {
		SkeletonTester.safePrint("--> Field deleteIFieldPlaceable", true);
		SkeletonTester.safePrint("<-- Field deleteIFieldPlaceable return", false);
		return false;
	
	}
	
	/**
	 * Beregisztr�lja a kapott IFieldPlaceable interf�sz� v�ltoz�t a mez�n.
	 *
	 * @param    ifp
	**/
	public void registerIFieldPlaceable(IFieldPlaceable ifp) {
		SkeletonTester.safePrint("--> Field registerIFieldPlaceable", true);
		
		ifp.registerField(this);

		SkeletonTester.safePrint("<-- Field registerIFieldPlaceable", false);
	}
	
	/**
	 * Beregisztr�lja a torony interf�sz� v�ltoz�t a mez�n.
	 *
	 * @param    it
	**/
	public void registerITower(ITower it) {
		SkeletonTester.safePrint("--> Field registerITower", true);
		if(!hasTower()){
			itower = it;
		}
		
		SkeletonTester.safePrint("<-- Field registerITower return", false);
	}
	
	public boolean isPath(){
	  return false;
	}

	public boolean hasTower() {
		SkeletonTester.safePrint("--> Field hasTower", true);
		SkeletonTester.safePrint("<-- Field hasTower return", false);
		return (itower!=null);	
	}
	
	public ITower getITower(){
		SkeletonTester.safePrint("--> Field getITower", true);
		
		SkeletonTester.safePrint("<-- Field getITower return", false);
		return itower;
	}
}
