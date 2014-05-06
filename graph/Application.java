package graph;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFrame;


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
	
	private GGame ggame;
	
	ArrayList<ArrayList<GCell>> cellGrid;
	
	/**
	 * Konstruktor
	 */
	public Application(){
		//JFrame beallitasa
		this.setLayout(new GridBagLayout());
		
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
		
		ggame = new GGame(game);
		game.setIView(ggame);
		
		ArrayList<ArrayList<Cell>> tmpGrid = game.getMap().getGrid();
		
		//cellGrid felepitese
		cellGrid = new ArrayList<ArrayList<GCell>>();
		
		for(int i = 0; i < tmpGrid.size(); i++) {
			
			ArrayList<GCell> gRow = new ArrayList<GCell>();
			
			for(int j = 0; j < tmpGrid.get(0).size(); j++) {
				gRow.add( (GCell) tmpGrid.get(i).get(j).getIView() );
			}
			
			cellGrid.add(gRow);
		}
		
		tmpConstraints = new GridBagConstraints(0, 0, cellGrid.size() + 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, 
				new Insets(0, 0, 0, 0), 0, 0);
		
		this.add(ggame, tmpConstraints);
		
		for(int i = 0; i < cellGrid.size(); i++) {
			
			for(int j = 0; j < cellGrid.get(0).size(); j++) {
				
				tmpConstraints = new GridBagConstraints(j, i + 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, 
						new Insets(0, 0, 0, 0), 0, 0);
				
				this.add(cellGrid.get(i).get(j), tmpConstraints);
			}
		}
		
		this.setSize(getMapSize());
	}
	
	private Dimension getMapSize() {
		return new Dimension(cellGrid.get(0).size() * GCell.cellSize.width, cellGrid.size() * GCell.cellSize.height);
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

}
