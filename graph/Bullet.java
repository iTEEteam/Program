package graph;


public class Bullet {
	/**
	 * Megadja, hogy mennyivel kell csokkenteni sebzeskor az Enemy health valtozojat.
	**/
	private int damage = 25;
	
	/**
	 * Megadja annak az ellensegnek a tipusat, amire nagyobb a sebzes, fejlesztettek a bulletet. 
	**/
	private String enemyType;
	
	/**
	 * Konstruktor
	 *
	 * @param    dmg
	 * @param    et
	**/
	public Bullet(int dmg, String et) {
		setDamage(dmg);
		setEnemy(et);
	}
	/*
	 * default constructor
	 */
	public Bullet(){
		setEnemy("");
	}
	
	/**
	 * Megadja, hogy mennyit sebez a Bullet egy Elf-en.
	**/
	public int getDamageElf() {
		
		// 8% esellyel ketteszeli az ellenseget
		if(Math.random()<0.08)
			return 0;
				
		if(enemyType.equals("elf"))
			return damage+40;
		
		return damage;
	
	}
	
	/**
	 * Megadja, hogy mennyit sebez a Bullet egy Hobbit-on.
	**/
	public int getDamageHobbit() {
		// 8% esellyel ketteszeli az ellenseget
		if(Math.random()<0.08)
			return 0;
		
		
		if(enemyType.equals("hobbit"))
			return damage+40;
		
		return damage;	
	}
	
	/**
	 * Megadja, hogy mennyit sebez a Bullet egy Dwarf-on.
	**/
	public int getDamageDwarf() {
		// 8% esellyel ketteszeli az ellenseget
		if(Math.random()<0.08)
			return 0;
		
	
		
		if(enemyType.equals("dwarf"))
			return damage+40;
		
		return damage;	
	}
	
	/**
	 * Megadja, hogy mennyit sebez a Bullet egy Human-en.
	**/
	public int getDamageHuman() {
		// 8% esellyel ketteszeli az ellenseget
		if(Math.random()<0.08)
			return 0;
		
	
		
		if(enemyType.equals("human"))
			return damage+40;
		
		return damage;
	}
	
	public void setEnemy(String e) {
		enemyType = e;
	}
	
	public void setDamage(int dmg) {
		damage += dmg;
	}
	
	public String getEnemyType() {
		return enemyType;
	}
}
