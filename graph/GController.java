package graph;

/**
 * A GController ir�ny�tja a grafikus elemeket, v�gzi a felhaszn�l�i interakci�k �rv�nyre jut�s�nak a fel�letre
 * vonatkoz� r�szeit, illetve kezdem�nyezi a modell megv�ltoztat�s�t.
 * 
 * @author Seres
 */
public class GController extends Graphic implements IView {
	
	/**
	 * Ez a hivatkozasi pont a modellhez.
	 */
	private Controller controller;
	
	/**
	 * A kijelolt ut, pl.: akadaly vetele.
	 */
	private GGame ggame;
	
	/**
	 * A kijelolt mezo, pl.: torony vetele.
	 */
	private GPath chosenPath;
	
	/**
	 * Ez a hivatkozasi pont a grafika masik reszehez.
	 */
	private GField chosenField;

	/**
	 * Az Eclipse ragaszkodott hozza. :(
	 */
	private static final long serialVersionUID = 6381818474948343583L;
	
	/**
	 * 
	 * @param controller
	 */
	public GController(Controller controller) {
		
	}
	
	/**
	 * A Controllerrel kapcsolatos felulet kirajzolasaert felelos fuggveny.
	 */
	@Override
	protected void draw() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Ures metodus. A modell kialakitasa miatt muszaj megvalositani, de szukseg nincs ra.
	 */
	@Override
	public void highlight() {}
	
	/**
	 * Ures metodus. A modell kialakitasa miatt muszaj megvalositani, de szukseg nincs ra.
	 */
	@Override
	public void deHighlight() {}
	
	/**
	 * A controller allapotanak valtozasakor ertesiti a feluletet.
	 */
	@Override
	public void gNotify() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Ures metodus. A modell kialakitasa miatt muszaj megvalositani, de szukseg nincs ra.
	 */
	@Override
	public void addGEnemy(GEnemy ge) {}
	
	/**
	 * Ures metodus. A modell kialakitasa miatt muszaj megvalositani, de szukseg nincs ra.
	 */
	@Override
	public GEnemy getGEnemy(Enemy e) {
		return null;
	}
	
	/**
	 * Ures metodus. A modell kialakitasa miatt muszaj megvalositani, de szukseg nincs ra.
	 */
	@Override
	public void deleteGEnemy(Enemy e) {}
}
