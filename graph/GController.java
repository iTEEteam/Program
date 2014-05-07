package graph;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * A GController irányítja a grafikus elemeket, végzi a felhasználói interakciók érvényre jutásának a felületre
 * vonatkozó részeit, illetve kezdeményezi a modell megváltoztatását.
 * 
 * @author Seres
 */
public class GController extends JPanel implements IView, MouseListener, ActionListener {
	
	/**
	 * Ez a hivatkozasi pont a modellhez.
	 */
	private Controller controller;
	
	/**
	 * Ez a hivatkozasi pont a grafika masik reszehez.
	 */
	private GGame ggame;
	
	/**
	 * A kijelolt ut, pl.: akadaly vetele.
	 */
	private GPath chosenGPath;
	
	/**
	 * A kijelolt mezo, pl.: torony vetele.
	 */
	private GField chosenGField;
	
	Application parent;
	
	JButton btnBuyTower;
	JButton btnBuyObstacle;
	JButton btnBuySpeedGem;
	JButton btnBuyRangeGem;
	JButton btnBuyDamageGem;
	JButton btnBuyEnemyTypeGemHuman;
	JButton btnBuyEnemyTypeGemElf;
	JButton btnBuyEnemyTypeGemHobbit;
	JButton btnBuyEnemyTypeGemDwarf;
	JButton btnBuyIntensityGem;
	JButton btnBuyRepairGem;
	
	/**
	 * Az Eclipse ragaszkodott hozza. :(
	 */
	private static final long serialVersionUID = 6381818474948343583L;
	
	/**
	 * Konstruktor.
	 * 
	 * @param controller A hivatkozasi pont a modellhez.
	 */
	public GController(Controller controller, Application parent) {
		this.controller = controller;
		this.parent = parent;
		
		this.setLayout(new FlowLayout());
		
		TitledBorder tmpTitle;
		JPanel tmpPanel;
		JPanel tmpPanel2;
		
		btnBuyTower = new JButton("Torony");
		btnBuyTower.setActionCommand("tower");
		btnBuyTower.addActionListener(this);
		btnBuyObstacle = new JButton("Akadály");
		btnBuyObstacle.setActionCommand("obstacle");
		btnBuyObstacle.addActionListener(this);
		
		btnBuySpeedGem = new JButton("Sebesség");
		btnBuySpeedGem.setActionCommand("speed");
		btnBuySpeedGem.addActionListener(this);
		btnBuyRangeGem = new JButton("Távolság");
		btnBuyRangeGem.setActionCommand("range");
		btnBuyRangeGem.addActionListener(this);
		btnBuyDamageGem = new JButton("Sebzés");
		btnBuyDamageGem.setActionCommand("damage");
		btnBuyDamageGem.addActionListener(this);
		
		btnBuyEnemyTypeGemHuman = new JButton("Ember");
		btnBuyEnemyTypeGemHuman.setActionCommand("human");
		btnBuyEnemyTypeGemHuman.addActionListener(this);
		btnBuyEnemyTypeGemDwarf = new JButton("Törp");
		btnBuyEnemyTypeGemDwarf.setActionCommand("dwarf");
		btnBuyEnemyTypeGemDwarf.addActionListener(this);
		btnBuyEnemyTypeGemHobbit = new JButton("Hobbit");
		btnBuyEnemyTypeGemHobbit.setActionCommand("hobbit");
		btnBuyEnemyTypeGemHobbit.addActionListener(this);
		btnBuyEnemyTypeGemElf = new JButton("Elf");
		btnBuyEnemyTypeGemElf.setActionCommand("elf");
		btnBuyEnemyTypeGemElf.addActionListener(this);
		
		btnBuyIntensityGem = new JButton("Intenzitás");
		btnBuyIntensityGem.setActionCommand("intensity");
		btnBuyIntensityGem.addActionListener(this);
		btnBuyRepairGem = new JButton("Javítás");
		btnBuyRepairGem.setActionCommand("repair");
		btnBuyRepairGem.addActionListener(this);
		
		//Epites panel hozzadasa
		tmpTitle = BorderFactory.createTitledBorder("Építés");
		tmpPanel = new JPanel();
		tmpPanel.setBorder(tmpTitle);
		tmpPanel.add(btnBuyTower);
		tmpPanel.add(btnBuyObstacle);
		this.add(tmpPanel);
		
		//Torony fejlesztes panel hozzadasa
		tmpTitle = BorderFactory.createTitledBorder("Torony fejlesztés");
		tmpPanel = new JPanel();
		tmpPanel.setBorder(tmpTitle);
		tmpPanel.add(btnBuyRangeGem);
		tmpPanel.add(btnBuySpeedGem);
		tmpPanel.add(btnBuyDamageGem);
		//Torony fejlesztesen beluli ellensegtipusra fejlesztes panel hozzaadasa
		tmpTitle = BorderFactory.createTitledBorder("Ellenség típus");
		tmpPanel2 = new JPanel();
		tmpPanel2.setBorder(tmpTitle);
		tmpPanel2.add(btnBuyEnemyTypeGemHuman);
		tmpPanel2.add(btnBuyEnemyTypeGemHobbit);
		tmpPanel2.add(btnBuyEnemyTypeGemElf);
		tmpPanel2.add(btnBuyEnemyTypeGemDwarf);
		tmpPanel.add(tmpPanel2);
		this.add(tmpPanel);
		
		//Akadaly fejlesztes panel hozzadasa
		tmpTitle = BorderFactory.createTitledBorder("Akadály fejlesztés");
		tmpPanel = new JPanel();
		tmpPanel.setBorder(tmpTitle);
		tmpPanel.add(btnBuyIntensityGem);
		tmpPanel.add(btnBuyRepairGem);
		this.add(tmpPanel);
		
		disableAll();
	}
	
	private void disableFieldRelated() {
		btnBuyTower.setEnabled(false);
		btnBuySpeedGem.setEnabled(false);
		btnBuyRangeGem.setEnabled(false);
		btnBuyDamageGem.setEnabled(false);
		btnBuyEnemyTypeGemHuman.setEnabled(false);
		btnBuyEnemyTypeGemElf.setEnabled(false);
		btnBuyEnemyTypeGemHobbit.setEnabled(false);
		btnBuyEnemyTypeGemDwarf.setEnabled(false);
	}
	
	private void enableFieldRelated() {
		btnBuyTower.setEnabled(true);
		btnBuySpeedGem.setEnabled(true);
		btnBuyRangeGem.setEnabled(true);
		btnBuyDamageGem.setEnabled(true);
		btnBuyEnemyTypeGemHuman.setEnabled(true);
		btnBuyEnemyTypeGemElf.setEnabled(true);
		btnBuyEnemyTypeGemHobbit.setEnabled(true);
		btnBuyEnemyTypeGemDwarf.setEnabled(true);
	}
	
	private void disablePathRelated() {
		btnBuyObstacle.setEnabled(false);
		btnBuyIntensityGem.setEnabled(false);
		btnBuyRepairGem.setEnabled(false);
	}
	
	private void enablePathRelated() {
		btnBuyObstacle.setEnabled(true);
		btnBuyIntensityGem.setEnabled(true);
		btnBuyRepairGem.setEnabled(true);
	}
	
	private void disableAll() {
		disableFieldRelated();
		disablePathRelated();
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
		deHighlightSelected();
		
		chosenGPath = gp;
		chosenGField = null;
		
		chosenGPath.highlight();
		chosenGPath.draw();
		
		controller.setPath(chosenGPath.getPath());
		
		disableFieldRelated();
		enablePathRelated();
	}
	
	/**
	 * Setter a chosenGField-hez.
	 * 
	 * @param gf A chosenGField.
	 */
	public void setChosenGField(GField gf) {
		deHighlightSelected();
		
		chosenGField = gf;
		chosenGPath = null;
		
		chosenGField.highlight();
		chosenGField.draw();
		
		controller.setField(chosenGField.getField());
		
		disablePathRelated();
		enableFieldRelated();
	}
	
	/**
	 * A Tower vasarlasat vegzo fuggveny.
	 */
	public void buyGTower() {
		controller.buyTower();
		
		if(chosenGField != null) {
			chosenGField.gNotify();
		}
	}
	
	/**
	 * Az Obstacle vasarlasat vegzo fuggveny.
	 */
	public void buyGObstacle() {
		controller.buyObstacle();
		
		if(chosenGPath != null) {
			chosenGPath.gNotify();
		}
	}
	
	/**
	 * A SpeedGem vasarlasat vegzo fuggveny.
	 */
	public void buyGSpeedGem() {
		controller.buySpeedGem();
	}
	
	/**
	 * A RangeGem vasarlasat vegzo fuggveny.
	 */
	public void buyGRangeGem() {
		controller.buyRangeGem();
	}
	
	/**
	 * A DamageGem vasarlasat vegzo fuggveny.
	 */
	public void buyGDamageGem() {
		controller.buyDamageGem();
	}
	
	/**
	 * Az EnemyTypeGem vasarlasat vegzo fuggveny.
	 * 
	 * @param enemyType A vasarolt EnemyTypeGem tipusa.
	 */
	public void buyGEnemyTypeGem(String enemyType) {
		controller.setEnemy(enemyType);
		controller.buyEnemyTypeGem();
		
		if(chosenGField != null) {
			chosenGField.gNotify();
		}
	}
	
	/**
	 * Az IntensityGem vasarlasat vegzo fuggveny.
	 */
	public void buyGIntensityGem() {
		controller.buyIntensityGem();
	}
	
	/**
	 * A RepairGem vasarlasat vegzo fuggveny.
	 */
	public void buyGRepairGem() {
		controller.buyRepairGem();
	}
	
	private void deHighlightSelected() {
		if(chosenGField != null) {
			chosenGField.deHighlight();
			chosenGField.gNotify();
		} else if(chosenGPath != null) {
			chosenGPath.deHighlight();
			chosenGPath.gNotify();
		}
	}
	
	/**
	 * A controller allapotanak valtozasakor ertesiti a feluletet. 
	 * Nem hiszem, hogy szukseg van ra.
	 */
	@Override
	public void gNotify() {}
	
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
	 * MouseListener interfesz miatt szukseges, egyebkent nem implementalt.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}
	
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
	 * Az eger kattintasat kezeli a GCell objektumokon.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		GCell tmpGCell = (GCell) e.getSource();
	
		if(tmpGCell.isGPath()) {
			GPath tmpGPath = (GPath) tmpGCell;
			
			setChosenGPath(tmpGPath);
		} else {
			GField tmpGField = (GField) tmpGCell;
			
			setChosenGField(tmpGField);
		}
	}

	/**
	 * MouseListener interfesz miatt szukseges, egyebkent nem implementalt.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("tower")) {
			buyGTower();
		} else if(actionCommand.equals("obstacle")) {
			buyGObstacle();
		} else if(actionCommand.equals("range")) {
			buyGRangeGem();
		} else if(actionCommand.equals("speed")) {
			buyGSpeedGem();
		} else if(actionCommand.equals("damage")) {
			buyGDamageGem();
		} else if(actionCommand.equals("human") || actionCommand.equals("hobbit") 
				|| actionCommand.equals("elf") || actionCommand.equals("dwarf")) {
			buyGEnemyTypeGem(actionCommand);
		} else if(actionCommand.equals("intensity")) {
			buyGIntensityGem();
		} else if(actionCommand.equals("repair")) {
			buyGRepairGem();
		} 
	}
}
