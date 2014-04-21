package proto;


public class Bullet {
	/**
	 * Megadja, hogy mennyivel kell csokkenteni sebzeskor az Enemy health valtozojat.
	**/
	private int damage = 20;
	
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
		ProtoTester.isPrinting = false;
		ProtoTester.addToObjectCatalog(this);
		ProtoTester.isPrinting = true;

		setDamage(dmg);
		setEnemy(et);
	}
	/*
	 * default constructor
	 */
	public Bullet(){
		ProtoTester.isPrinting = false;
		ProtoTester.addToObjectCatalog(this);
		ProtoTester.isPrinting = true;

		setEnemy("");
	}
	
	/**
	 * Megadja, hogy mennyit sebez a Bullet egy Elf-en.
	**/
	public int getDamageElf() {
		
		// 8% esellyel ketteszeli az ellenseget
		if(Game.bRandom && Math.random()<0.08)
			return 0;
		
		if(enemyType.equals("Elf"))
			return damage+20;
		
		return damage;
	
	}
	
	/**
	 * Megadja, hogy mennyit sebez a Bullet egy Hobbit-on.
	**/
	public int getDamageHobbit() {
		// 8% esellyel ketteszeli az ellenseget
		if(Game.bRandom && Math.random()<0.08)
			return 0;
		
		if(enemyType.equals("Hobbit"))
			return damage+20;
		
		return damage;	
	}
	
	/**
	 * Megadja, hogy mennyit sebez a Bullet egy Dwarf-on.
	**/
	public int getDamageDwarf() {
		// 8% esellyel ketteszeli az ellenseget
		if(Game.bRandom && Math.random()<0.08)
			return 0;
		
		if(enemyType.equals("Dwarf"))
			return damage+20;
		
		return damage;	
	}
	
	/**
	 * Megadja, hogy mennyit sebez a Bullet egy Human-en.
	**/
	public int getDamageHuman() {
		// 8% esellyel ketteszeli az ellenseget
		if(Game.bRandom && Math.random()<0.08)
			return 0;
		
		if(enemyType.equals("Human"))
			return damage+20;
		
		return damage;
	}
	
	public void setEnemy(String e) {
		enemyType = e;
	}
	
	public void setDamage(int dmg) {
		damage += dmg;
	}
}
