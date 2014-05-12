package graph;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;


/**
 * A program belepesi pontja, az alkalmazas grafikus feluletenek kozeppontja.
 * 
 * @author Seres
 *
 */
public class Application extends JFrame implements ActionListener {
	
	/**
	 * Az Eclipse ragaszkodott hozza. :(
	 */
	private static final long serialVersionUID = 1388781381443801946L;

	public Game game;
	
	private Controller controller;
	
	ArrayList<ArrayList<GCell>> cellGrid;
	
	private String currentMap = null;
	
	private JPanel gamePanel;
	private JComponent topPanel;
	private JPanel downPanel;
	
	
	private JPanel stopPanel;
	private BufferedImage stopPicture;
	private JLabel stopPicLabel;
	
	
	/**
	 * Konstruktor
	 */
	public Application(){
		super("Rise of the Great Towers");
		ResourcesCache.loadResources();
				
		
		// jatek vege kep
		try {
			stopPicture = ImageIO.read(new File("gameImages/game_over.jpeg"));
			stopPicLabel = new JLabel(new ImageIcon(stopPicture));
			stopPanel = new JPanel();
			stopPanel.add(stopPicLabel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//JFrame beallitasa
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem ujJatek;;
		
		menu = new JMenu("Jatek");
		ujJatek = new JMenuItem("Uj jatek");
		ujJatek.setActionCommand("newGame");
		ujJatek.addActionListener(this);
		
		menu.add(ujJatek);
		menuBar.add(menu);
		
		this.setJMenuBar(menuBar);
		
		Initialize();
	}
	
	/**
	 * Inicializalast vegezne, de nem tudom, mennyire kell ez.
	 */
	public void Initialize(){
		if(gamePanel!=null){
			this.remove(gamePanel);
			this.remove(topPanel);
			this.remove(downPanel);
		}
		
		JFileChooser fc = new JFileChooser(new File("."));
		
		FileFilter mapFilter = new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				if(pathname.isDirectory() || pathname.getName().matches(".*map.*")) {
					return true;
				} else {
                    return false;
                }
			}

			@Override
			public String getDescription() {
				return "Map files";
			}
		};
		
		fc.setFileFilter(mapFilter);
		
		File fCurrentMap = null;
		
		if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			fCurrentMap = fc.getSelectedFile();
		} else {
			System.exit(-1);
		}
		
		currentMap = fCurrentMap.getName();
		
		GridBagConstraints tmpConstraints;
		
		//game letrehozasa, inicializalasa
		game = new Game();
		
		try {
			game.initialize(fCurrentMap.getPath());
		} catch (FileNotFoundException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(-1);
		}
		
		game.setIView(new GGame(game));
		
		//Controller l�trehoz�sa
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
		topPanel = (GGame)game.getIView();
		this.add(topPanel);
		
		((GGame)game.getIView()).setPreferredSize(new Dimension(cellGrid.get(0).size() * GCell.cellSize.width, 50));
		
		
		//Cellak elhelyezese
		gamePanel = new JPanel(new GridBagLayout());
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
		downPanel = new JPanel();
		downPanel.add((GController)controller.getIView());
		this.add(downPanel);
		
		this.pack();
	}

	private void setGameEnd(){
		this.remove(topPanel);
		this.remove(gamePanel);
		this.remove(downPanel);
		
		this.add(stopPanel);
		this.pack();
		
	}
	
	/**
	 * A program belepesi pontja.
	 * 
	 * @param args Parancssori argumentumok. 
	 */
	public static void main(String[] args) {
		Application app = new Application();
		app.setVisible(true);
		while(true){
		
			while(app.game.getSucceededE()<10){
				app.game.update();
				try {
	        		Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			app.setGameEnd();

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("newGame")) {
			Initialize();
			this.remove(stopPanel);
		}
	}
}
