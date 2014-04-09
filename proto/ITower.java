package proto;




public interface ITower {
	public Enemy chooseEnemy();
	public void shoot();
	public void setPaths();
	public void addITGem(ITGem g);
	public void sell();
}
