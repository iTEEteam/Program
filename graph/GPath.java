package graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

/**
 * A GPath felel a Path osztaly megjeleniteseert, kirajzolasaert. Minden Path-hoz tartozik egy, es mindegyikhez
 * egy Path tartozik.
 * 
 * @author Seres
 *
 */
public class GPath extends GCell {

	/**
	 * Az Eclipse ragaszkodott hozza. :(
	 */
	private static final long serialVersionUID = 6584228015160996554L;
	
	/**
	 * Referencia arra az utra, amelyikhez tartozik. Ezen keresztul kerheti le a szukseges adatokat
	 * a modellbol.
	 */
	private Path path;
	
	/**
	 * Az path enemies-hez tartozó GEnemy-k listaja.
	 */
	ArrayList<GEnemy> genemies;
	
	private static final File fElf = new File("gameImages/elf.png");
	private static final File fHuman = new File("gameImages/human.png");
	private static final File fDwarf = new File("gameImages/dwarf.png");
	private static final File fHobbit = new File("gameImages/hobbit.png");
	
	private static final File fPath = new File("gameImages/path.jpg");
	private static final File fObstacle = new File("gameImages/obstacle.png");
	
	private int enemyCounter;
	
	private BufferedImage image;
	
	/**
	 * Konstruktor.
	 * 
	 * @param path A modellbeli kapcsolatot biztosito Path.
	 */
	public GPath(Path path) {
		super();
		this.path = path;
		
		genemies = new ArrayList<>();
		
		image = new BufferedImage(cellSize.width, cellSize.height, BufferedImage.TYPE_INT_ARGB);
	}
	
	/**
	 * Getter path-hoz.
	 * 
	 * @return A tarolt Path.
	 */
	public Path getPath() {
		return path;
	}
	
	/**
	 * Az ut kirajzolasat vegzo fuggveny.
	 */
	@Override
	protected void draw() {
		enemyCounter = 0;
		
		Graphics2D g = image.createGraphics();
		
		Image bgImg = null;
		try {
			bgImg = ImageIO.read(fPath);
		} catch(IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
		
		if(highlighted) {
			g.setColor(Color.RED);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			int clipping = (int) ((5d / getWidth()) * bgImg.getHeight(null));
			g.drawImage(bgImg, 5, 5, getWidth() - 5, getHeight() - 5, clipping, clipping, bgImg.getWidth(null) - clipping, 
					bgImg.getHeight(null) - clipping, null);
		} else {
			g.drawImage(bgImg, 0, 0, getWidth(), getHeight(), null);
		}
		
		if(path.hasObstacle()) {
			try {
				g.drawImage(ImageIO.read(fObstacle), 20, 20, this.getSize().width - 40, this.getSize().height - 40, null);
			} catch (IOException e) {
				System.err.println(e.getLocalizedMessage());
			}
		}
		
		for(GEnemy ge : genemies) {
			ge.drawMe(this);
		}
		
		
		super.draw();
	}
	
	/**
	 * Egy parameterkent kapott ellenseg kepet rahelyezi az utra.
	 * @param img Az ellenseg kepe.
	 */
	private void drawPicture(Image img) {
		int sideLength = (int) Math.ceil(Math.sqrt(genemies.size()));

		image.createGraphics().drawImage(img, 10 + ((enemyCounter % sideLength) * ((cellSize.width - 20) / sideLength)), 10 + ((enemyCounter / sideLength) * ((cellSize.width - 20) / sideLength)), 
				(cellSize.width - 20) / sideLength, (cellSize.height - 20) / sideLength, null);
		
		enemyCounter++;
	}
	
	/**
	 * A Humanhoz tartozo rajzolofuggveny.
	 * @throws IOException Ha a kep beolvasasa kozben hiba lep fel.
	 */
	public void drawGHuman() {
		try {
			drawPicture(ImageIO.read(fHuman));
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}
	
	/**
	 * A Dwarfhoz tartozo rajzolofuggveny.
	 * @throws IOException Ha a kep beolvasasa kozben hiba lep fel.
	 */
	public void drawGDwarf() {
		try {
			drawPicture(ImageIO.read(fDwarf));
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}
	
	/**
	 * Az Elfhez tartozo rajzolofuggveny.
	 * @throws IOException Ha a kep beolvasasa kozben hiba lep fel.
	 */
	public void drawGElf() {
		try {
			drawPicture(ImageIO.read(fElf));
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}
	
	/**
	 * A Hobbithoz tartozo rajzolofuggveny.
	 * @throws IOException Ha a kep beolvasasa kozben hiba lep fel.
	 */
	public void drawGHobbit() {
		try {
			drawPicture(ImageIO.read(fHobbit));
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}
		
	/**
	 * A Path-re lepo ellenseghez tartozo GEnemy-t tarolja el.
	 * 
	 * @param ge A Hozzaadando GEnemy.
	 */
	@Override
	public void addGEnemy(GEnemy ge) {
		genemies.add(ge);
	}
	
	/**
	 * Egy adott Enemy-hez tartozo GEnemy objektumot adja vissza.
	 * 
	 * @param e Az Enemy, amelyiknek a GEnemy-jet kerdezzuk le.
	 * @return Az Enemy-hez tartozo GEnemy.
	 */
	@Override
	public GEnemy getGEnemy(Enemy e) {
		
		GEnemy genemy = null;
		
		for(GEnemy ge : genemies) {
			if(ge.getEnemy().equals(e)) {
				genemy = ge;
			}
		}
		return genemy;
	}
	
	/**
	 * Egy adott Enemy-hez tartozo GEnemy objektumot torol.
	 * 
	 * @param e A torlendo GEnemyhez tartozo Enemy objektum.
	 */
	@Override
	public void deleteGEnemy(Enemy e) {
		
		Iterator<GEnemy> iter = genemies.iterator();
		
		while(iter.hasNext()) {
			GEnemy genemy = iter.next();
			
			if(genemy.getEnemy().equals(e)) {
				iter.remove();
			}
		}
	}
	
	/**
	 * Ezzel a fuggvennyel valositjuk meg a konkret rajzolast.
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
	
	/**
	 * Mindig igazzal ter vissza.
	 * 
	 * @return Igaz.
	 */
	@Override
	public boolean isGPath() {
		return true;
	}
}
