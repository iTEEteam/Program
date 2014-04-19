package proto;



public interface IGame {
	/**
	 * A manat csokkento/novelo fuggveny.
	**/
	public void changeMana(int value);
	
	public int getMana();
	
	public void incSucceeded();
	
	public void addTower(Tower t);
	
	public void removeTower(Tower t);
	
//	public void removeEnemy(Enemy e);
	
	public boolean getRandom();
}
