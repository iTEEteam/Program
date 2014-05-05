package graph;

/**
 * A GEnemy felel azert, hogy a GPath tudja, hogy milyen tipusu Enemyket kell rajzolni. Ezt az enemy attributumbol
 * tudja meg, illetve drawMe metodus kulonfele implementacioinak segitsegevel uzen GEnemy
 * a GPath-nak. Minden Enemy-hez letrehozaskor tarsitunk egy peldanyt, amit elhelyezunk a GGame-ben.
 * 
 * @author Danny
 *
 */
public abstract class GEnemy {
	
	protected Enemy enemy;
	
	/**
	 * Konstruktor.
	 * 
	 * @param e Az Enemy referencia, amelyhez csatlakozik a GEnemy.
	 */
	public GEnemy(Enemy e) {
		enemy = e;
	}
	
	/**
	 * Ez a metodus gondoskodik arrol, hogy a megfelelo tipusú Enemy kirajzolasa tortenjen
	 * meg a Path-on.
	 */
	public abstract void drawMe(GPath gp);
}
