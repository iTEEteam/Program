package graph;

/**
 * A Human kirajzol�s��rt felel�s oszt�ly. 
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
