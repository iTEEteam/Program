package graph;



public class IntensityGem extends Gem implements IOGem {

	public static final int price = 20;

	private int intensity;
	
	public IntensityGem() {
		intensity=10;
		ProtoTester.addToObjectCatalog(this);
	}
	
	/**
	 * Megnoveljuk a lassitas merteket az akadalyon.
	 */
	public void upgradeObstacle(Obstacle o) {
		
		// sd_akadaly_fejlesztese szekvenciadiagrammon el van irva
		o.increaseIntensity(intensity);
		
	}
}
