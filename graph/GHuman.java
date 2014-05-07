package graph;

/**
 * A Human kirajzol·s·Èrt felelıs oszt·ly. 
 * 
 * @author Danny
 *
 */
public class GHuman extends GEnemy  {

	public GHuman(Enemy e) {
		super(e);
	}

	@Override
	public void drawMe(GPath gp) {
		gp.drawGHuman();
	}

}
