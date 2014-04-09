package proto;



public interface IObstacle {
	public void slow(Enemy e);
	public void amortization();
	public void increaseIntensity(int intens);
	public void addIOGem(IOGem g);
	public void repair();
}
