package proto;


public class Field extends Cell {
	/**
	 * A mezon levo, torony interfesze valtozo referenciaja.
	**/
	private ITower itower;
			
	/**
	 * Konstruktor
	**/
	public Field(IGame igame) {
		super(igame);
		ProtoTester.isPrinting = false;
		ProtoTester.addToObjectCatalog(this);
		ProtoTester.isPrinting = true;
	}
	
	/**
	 * Torli a parameterkent kapott IFieldPlaceable interfeszu valtozot a mezorol. 
	 *
	 * @param    ifp
	**/
	public boolean deleteIFieldPlaceable(IFieldPlaceable ifp) {
		if(itower==ifp){
			itower = null;
			return true;
		}
		
		
		return false;
	
	}
	
	/**
	 * Beregisztralja a kapott IFieldPlaceable interfeszu valtozot a mezon.
	 *
	 * @param    ifp
	**/
	public void registerIFieldPlaceable(IFieldPlaceable ifp) {
		ifp.registerField(this);
	}
	
	/**
	 * Beregisztralja a torony interfeszu valtozot a mezon.
	 *
	 * @param    it
	**/
	public void registerITower(ITower it) {
		if(!hasTower()){
			itower = it;
		}
//TODO mana?
	}
	
	public boolean isPath(){
	  return false;
	}

	public boolean hasTower() {
		return (itower!=null);	
	}
	
	public ITower getITower(){
		return itower;
	}
}
