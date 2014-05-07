package graph;

/**
 * A Dwarf kirajzol·s·Èrt felelıs oszt·ly. 
 * 
 * @author Danny
 *
 */
public class GDwarf extends GEnemy  {

	public GDwarf(Enemy e) {
		super(e);
	}

	@Override
	public void drawMe(GPath gp) {
		gp.drawGDwarf();
	}

}
