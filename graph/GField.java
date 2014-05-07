package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A GField felel a Field osztaly megjeleniteseert, kirajzolasaert. Minden Field-hez tartozik egy, es mindegyikhez
 * egy Field tartozik.
 * 
 * @author Seres
 *
 */
public class GField extends GCell {

	/**
	 * Az Eclipse ragaszkodott hozza. :(
	 */
	private static final long serialVersionUID = 8151525822402323058L;
	
	/**
	 * Referencia arra mezore, amelyikhez tartozik. Ezen keresztul kerheti le a szukseges adatokat
	 * a modellbol.
	 */
	private Field field;
	
	private static final File fNormalTower = new File("gameImages/tower1.png");
	private static final File fElfTower = new File("gameImages/tower2.png");
	private static final File fDwarfTower = new File("gameImages/tower3.png");
	private static final File fHumanTower = new File("gameImages/tower4.png");
	private static final File fHobbitTower = new File("gameImages/tower5.png");
	
	private static final File fField = new File("gameImages/field.jpg");
	
	/**
	 * Konstruktor. 
	 * 
	 * @param field A modellbeli kapcsolatot biztosito Field.
	 */ 
	public GField(Field field) {
		super();
		this.field = field;
	}
	
	/**
	 * Getter fieldhez.
	 * 
	 * @return A tarolt Field referenciaja.
	 */
	public Field getField() {
		return field;
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
	 * Ezzel a fuggvennyel valositjuk meg a konkret rajzolast.
	 */
	@Override
	public void paint(Graphics g) {
		Image bgImg = null;
		try {
			bgImg = ImageIO.read(fField);
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
		
		
		if(field.hasTower()) {
			String enemyType = field.getITower().getEnemyType();
			
			BufferedImage img = null;
			
			
			try {
				if((enemyType == null) || enemyType.equals("")) {
					img = ImageIO.read(fNormalTower);
				} else if(enemyType.equals("human")) {
					img = ImageIO.read(fHumanTower);
				} else if(enemyType.equals("hobbit")) {
					img = ImageIO.read(fHobbitTower);
				} else if(enemyType.equals("elf")) {
					img = ImageIO.read(fElfTower);
				} else if(enemyType.equals("dwarf")) {
					img = ImageIO.read(fDwarfTower);
				}
			} catch(IOException e) {
				System.err.println(e.getLocalizedMessage());
				System.exit(-1);
			}
			
			g.drawImage(img, 10, 10, 80, 80, null);
		}
	}
	
	/**
	 * Mindig hamissal ter vissza.
	 * 
	 * @return Hamis.
	 */
	@Override
	public boolean isGPath() {
		return false;
	}

}
