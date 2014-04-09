package proto;



public class IntensityGem extends Gem implements IOGem {

	public static final int price = 20;

	private int intensity;
	
	public IntensityGem() {

	}
	
	public void upgradeObstacle(Obstacle o) {
		
		// sd_akadaly_fejlesztese szekvenciadiagrammon el van irva
		o.increaseIntensity(intensity);
		
	}
}
