package graph;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
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
	
	private JPanel gamePanel;
	private JComponent topPanel;
	private JPanel downPanel;
	
	
	private JPanel stopPanel;
	private BufferedImage stopPicture;
	private JLabel stopPicLabel;
	
	private JPanel winPanel;
	
	private UpdateThread updateThread;
	
	
	/**
	 * Konstruktor
	 */
	public Application(){
		super("Rise of the Great Towers");
		ResourcesCache.loadResources();
		
		updateThread = null;
		
		// jatek vege kep
		try {
			stopPicture = ImageIO.read(new File("gameImages/game_over.jpeg"));
			stopPicLabel = new JLabel(new ImageIcon(stopPicture));
			stopPanel = new JPanel();
			stopPanel.add(stopPicLabel);
		} catch (IOException e) {}
		
		winPanel = null;
		
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
		removePanels();
		
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
		downPanel = new JPanel();
		downPanel.add((GController)controller.getIView());
		this.add(downPanel);
		
		this.pack();
		
		startUpdateThread();
	}

	private void removePanels() {
		if(gamePanel!=null){
			this.remove(gamePanel);
		}
		if(topPanel!=null){
			this.remove(topPanel);
		}
		if(downPanel!=null){
			this.remove(downPanel);
		}
		if(stopPanel!=null){
			this.remove(stopPanel);
		}
		if(winPanel!=null){
			this.remove(winPanel);
		}
	}

	private void startUpdateThread() {
		if(updateThread != null) {
			updateThread.setStopFlag();
			
			try {
				updateThread.join();
			} catch (InterruptedException e) {}
		}
		
		updateThread = new UpdateThread(this);
		updateThread.start();
	}

	private void setGameEnd(){
		removePanels();
		
		this.add(stopPanel);
		this.pack();
		this.repaint();
		
	}
	
	public void setGameWin() {
		removePanels();
		
		winPanel = new JPanel();
		JLabel winLabel = new JLabel("You win!");
		winLabel.setFont(this.getFont().deriveFont(300));
		winPanel.add(winLabel);
		
		this.add(winPanel);
		this.pack();
		this.repaint();
	}
	
	private class UpdateThread extends Thread {
		
		private Application app;
		
		private volatile boolean stopFlag;
		
		public UpdateThread(Application app) {
			this.app = app;
			
			stopFlag = false;
		}
		
		public void setStopFlag() {
			stopFlag = true;
		}
		
		@Override
		public void run() {
			while(!stopFlag && (app.game.getWaveCounter() <= 5) && (app.game.getSucceededE() < 10)) {
				app.game.update();
				try {
	        		Thread.sleep(60);
				} catch (InterruptedException e) {}
			}
			
			if(app.game.getSucceededE() >= 10) {
				app.setGameEnd();
			} else if(app.game.getWaveCounter() > 5) {
				app.setGameWin();
			}
			
			
		}
	}
	
	/**
	 * A program belepesi pontja.
	 * 
	 * @param args Parancssori argumentumok. 
	 */
	public static void main(String[] args) {
		Application app = new Application();
		app.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("newGame")) {
			Initialize();
		}
	}
}
