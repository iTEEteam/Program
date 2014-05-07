package graph;

/**
 * A Elf kirajzol·s·Èrt felelıs oszt·ly. 
 * 
 * @author Danny
 *
 */
public class GElf extends GEnemy  {

	public GElf(Enemy e) {
		super(e);
	}

	@Override
	public void drawMe(GPath gp) {
		gp.drawGElf();
	}

}
