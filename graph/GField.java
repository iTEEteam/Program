package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

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
		Image bgImg = ResourcesCache.imgField;
		
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
			
			Image img = null;
			
			if((enemyType == null) || enemyType.equals("")) {
				img = ResourcesCache.imgNormalTower;
			} else if(enemyType.equals("human")) {
				img = ResourcesCache.imgHumanTower;
			} else if(enemyType.equals("hobbit")) {
				img = ResourcesCache.imgHobbitTower;
			} else if(enemyType.equals("elf")) {
				img = ResourcesCache.imgElfTower;
			} else if(enemyType.equals("dwarf")) {
				img = ResourcesCache.imgDwarfTower;
			}
			
			g.drawImage(img, (int)(this.getSize().width * 0.1), (int)(this.getSize().height * 0.1), (int)(this.getSize().width * 0.8), (int)(this.getSize().height * 0.8), null);
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
