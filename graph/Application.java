package graph;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * A program belepesi pontja, az alkalmazas grafikus feluletenek kozeppontja.
 * 
 * @author Seres
 *
 */
public class Application extends JFrame {
	
	/**
	 * Az Eclipse ragaszkodott hozza. :(
	 */
	private static final long serialVersionUID = 1388781381443801946L;

	private Game game;
	
	private Controller controller;
	
	ArrayList<ArrayList<GCell>> cellGrid;
	
	/**
	 * Konstruktor
	 */
	public Application(){
		ResourcesCache.loadResources();
		
		//JFrame beallitasa
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		GridBagConstraints tmpConstraints;
		
		//game letrehozasa, inicializalasa
		game = new Game();
		
		try {
			game.initialize("testmap4.txt");
		} catch (FileNotFoundException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(-1);
		}
		
		game.setIView(new GGame(game));
		
		//Controller létrehozása
		controller = new Controller(game);
		controller.setIView(new GController(controller, this));
		
		
		//cellGrid felepitese
		ArrayList<ArrayList<Cell>> tmpGrid = game.getMap().getGrid();
		
		cellGrid = new ArrayList<ArrayList<GCell>>();
		
		for(int i = 0; i < tmpGrid.size(); i++) {
			
			ArrayList<GCell> gRow = new ArrayList<GCell>();
			
			for(int j = 0; j < tmpGrid.get(0).size(); j++) {
				
				GCell tmpGcell = (GCell) tmpGrid.get(i).get(j).getIView();
				tmpGcell.addMouseListener((GController)controller.getIView());
				
				gRow.add(tmpGcell);
			}
			
			cellGrid.add(gRow);
		}
		
		
		//GGame elhelyezese
		//tmpConstraints = new GridBagConstraints(0, 0, cellGrid.get(0).size() + 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, 
				//new Insets(0, 0, 0, 0), 0, 0);
		
		this.add((GGame)game.getIView());
		
		((GGame)game.getIView()).setPreferredSize(new Dimension(cellGrid.get(0).size() * GCell.cellSize.width, 50));
		
		
		//Cellak elhelyezese
		JPanel gamePanel = new JPanel(new GridBagLayout());
		for(int i = 0; i < cellGrid.size(); i++) {
			
			for(int j = 0; j < cellGrid.get(0).size(); j++) {
				
				GCell gcell = cellGrid.get(i).get(j);
				gcell.gNotify();
				
				tmpConstraints = new GridBagConstraints(j, i, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, 
						new Insets(0, 0, 0, 0), 0, 0);
				
				gamePanel.add(gcell, tmpConstraints);
			}
		}
		this.add(gamePanel);
		
		
		//GController elhelyezese
		//tmpConstraints = new GridBagConstraints(0, cellGrid.size() + 1, cellGrid.get(0).size(), 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, 
				//new Insets(0, 0, 0, 0), 0, 0);
		this.add((GController)controller.getIView());
		
		
		JPanel test = new JPanel();
		JButton testbutt = new JButton("Make enemies.");
		testbutt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.makeEnemies();
			}
		});
		test.add(testbutt);
		
		testbutt = new JButton("Update.");
		testbutt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.update();
			}
		});
		test.add(testbutt);
		
		this.add(test);
		
		this.pack();
	}
	
	/**
	 * Inicializalast vegezne, de nem tudom, mennyire kell ez.
	 */
	public void Initialize(){
		//TODO kell ez?
	}
	
	/**
	 * A program belepesi pontja.
	 * 
	 * @param args Parancssori argumentumok. 
	 */
	public static void main(String[] args) {
		(new Application()).setVisible(true);
	}
	
	
	public void highlight(GCell gcell) {
		for(ArrayList<GCell> gRow : cellGrid) {
			for(GCell cell : gRow) {
				cell.deHighlight();
			}
		}
		gcell.highlight();
	}
}
