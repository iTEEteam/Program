package proto;


public class Bullet {
	/**
	 * Megadja, hogy mennyivel kell csokkenteni sebzeskor az Enemy health valtozojat.
	**/
	private int damage;
	
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
		ProtoTester.addToObjectCatalog(this);
		setDamage(dmg);
		setEnemy(et);
	}
	/*
	 * default constructor
	 */
	public Bullet(){
		ProtoTester.addToObjectCatalog(this);
		setDamage(10);
//TODO itt mi az enemytype?
		setEnemy(null);
	}
	
//TODO enemyType szerint visszaadni a damage-t	
	/**
	 * Megadja, hogy mennyit sebez a Bullet egy Elf-en.
	**/
	public int getDamageElf() {
	
		return damage;
	
	}
	
	/**
	 * Megadja, hogy mennyit sebez a Bullet egy Hobbit-on.
	**/
	public int getDamageHobbit() {

		return damage;
	
	}
	
	/**
	 * Megadja, hogy mennyit sebez a Bullet egy Dwarf-on.
	**/
	public int getDamageDwarf() {
;
		return damage;
	
	}
	
	/**
	 * Megadja, hogy mennyit sebez a Bullet egy Human-en.
	**/
	public int getDamageHuman() {

		return damage;
	
	}
	
	public void setEnemy(String e) {
		enemyType = e;
	}
	
	public void setDamage(int dmg) {
		damage = dmg;
	}
}
