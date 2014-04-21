package proto;

import java.util.ArrayList;

public class Obstacle implements IObstacle, IPathPlaceable {

    /**
     * Az akadaly ara. Statikus, mert minden akadalyra ugyanannyi.
	*
     */
    public static final int price = 10;

    /**
     * A lassitas merteke.
	*
     */
    private int slowIntens;

    /**
     * Az amortizacio merteke.
	*
     */
    private int amort;

    /**
     * Az Path cime, amin az akadaly van.
	*
     */
    private Path myPath;

    private ArrayList<IOGem> gems;

    /**
     * Konstruktor
     *
     * @param price
     * @param intens
     * @param am
     * @param p
     * @param up
	*
     */
    public Obstacle() {
        ProtoTester.addToObjectCatalog(this);
        gems = new ArrayList<IOGem>();
        amort = 100;
    }

    public void slow(Enemy e) {
        String obstacleName = ProtoTester.getKeyByValue(this);
        String enemyName = ProtoTester.getKeyByValue(e);        
        
        e.setModSpeed(slowIntens);
        ProtoTester.safePrint(obstacleName + " slows " + enemyName + " by "  + slowIntens);
        
        amortization();

        if (amort <= 0) {
            eliminate();
            ProtoTester.safePrint(obstacleName + " worn out");
        }         
    }

    public void amortization() {
        amort -= 30;
    }

    public void increaseIntensity(int intens) {
        slowIntens += intens;
    }

    public void addIOGem(IOGem g) {
        g.upgradeObstacle(this);
        gems.add(g);
    }

    public void repair() {
        amort = 0;
    }

    public void registerPath(Path p) {
        myPath = p;
        p.registerIObstacle(this);
    }

    public void eliminate() {
        myPath.deleteIObstacle(this);
    }
}
