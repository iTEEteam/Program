package graph;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A GController irányítja a grafikus elemeket, végzi a felhasználói interakciók érvényre jutásának a felületre
 * vonatkozó részeit, illetve kezdeményezi a modell megváltoztatását.
 * 
 * @author Seres
 */
public class GController extends Graphic implements IView, MouseListener {
	
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
	private GPath chosenGPath;
	
	/**
	 * Ez a hivatkozasi pont a grafika masik reszehez.
	 */
	private GField chosenGField;

	/**
	 * Az Eclipse ragaszkodott hozza. :(
	 */
	private static final long serialVersionUID = 6381818474948343583L;
	
	/**
	 * Konstruktor.
	 * 
	 * @param controller A hivatkozasi pont a modellhez.
	 */
	public GController(Controller controller) {
		this.controller = controller;
	}
	
	/**
	 * Getter a chosenGPath-hez.
	 * 
	 * @return A chosenGPath.
	 */
	public GPath getChosenGPath() {
		return chosenGPath;
	}
	
	/**
	 * Getter a chosenGField-hez.
	 * 
	 * @return A chosenGField.
	 */
	public GField getChosenGField() {
		return chosenGField;
	}
	
	/**
	 * Setter a chosenGPath-hez.
	 * 
	 * @param gp A chosenGPath.
	 */
	public void setChosenGPath(GPath gp) {
		chosenGPath = gp;
	}
	
	/**
	 * Setter a chosenGField-hez.
	 * 
	 * @param gf A chosenGField.
	 */
	public void setChosenGField(GField gf) {
		chosenGField = gf;
	}
	
	/**
	 * A Tower vasarlasat vegzo fuggveny.
	 */
	public void buyGTower() {
		//TODO
	}
	
	/**
	 * Az Obstacle vasarlasat vegzo fuggveny.
	 */
	public void buyGObstacle() {
		//TODO
	}
	
	/**
	 * A SpeedGem vasarlasat vegzo fuggveny.
	 */
	public void buyGSpeedGem() {
		//TODO
	}
	
	/**
	 * A RangeGem vasarlasat vegzo fuggveny.
	 */
	public void buyGRangeGem() {
		//TODO
	}
	
	/**
	 * A DamageGem vasarlasat vegzo fuggveny.
	 */
	public void buyGDamageGem() {
		//TODO
	}
	
	/**
	 * Az EnemyTypeGem vasarlasat vegzo fuggveny.
	 * 
	 * @param enemyType A vasarolt EnemyTypeGem tipusa.
	 */
	public void buyGEnemyTypeGem(String enemyType) {
		//TODO
	}
	
	/**
	 * Az IntensityGem vasarlasat vegzo fuggveny.
	 */
	public void buyGIntensityGem() {
		//TODO
	}
	
	/**
	 * A RepairGem vasarlasat vegzo fuggveny.
	 */
	public void buyGRepairGem() {
		//TODO
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
	
	/**
	 * Az eger kattintasat kezeli a GCell objektumokon.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * MouseListener interfesz miatt szukseges, egyebkent nem implementalt.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	/**
	 * MouseListener interfesz miatt szukseges, egyebkent nem implementalt.
	 */
	@Override
	public void mouseExited(MouseEvent e) {}
	
	/**
	 * MouseListener interfesz miatt szukseges, egyebkent nem implementalt.
	 */
	@Override
	public void mousePressed(MouseEvent e) {}

	/**
	 * MouseListener interfesz miatt szukseges, egyebkent nem implementalt.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}
}
