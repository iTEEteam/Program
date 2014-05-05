package graph;

/**
 * Az IView interfesz teremt kapcsolatot a modellbol a grafikus felulet fele. Segitsegevel lehet ertesiteni a
 * feluletet a modellbeli valtozasokrol. Ehhez kulonbozo ertesito metodusokat tartalmaz.
 * 
 * @author Seres
 *
 */
public interface IView {
	
	/**
	 * Alap ertesito metodus.
	 */
	public void gNotify();
	
	/**
	 * GEnemy hozzaadasa. 
	 * 
	 * @param ge A hozzaadando GEnemy.
	 */
	public void addGEnemy(GEnemy ge);
	
	/**
	 * Egy adott Enemyhez tartozo GEnemyt ad vissza.
	 * 
	 * @param e Az Enemy, amelynek a GEnemyjet akarjuk megkapni.
	 * @return Az e Enemyhez tartozo GEnemy.
	 */
	public GEnemy getGEnemy(Enemy e);
	
	/**
	 * GEnemy eltavolitasa enemy parameter alapjan.
	 * 
	 * @param e Az eltavolitando GEnemyhez tartozo enemy.
	 */
	public void deleteGEnemy(Enemy e);
	
}
