package Pathfinder.utility;

/**
 * Ohjelman muuttuvat asetukset. Vaihdetaan käyttöliittymän kautta.
 * @author lasse
 */
public class Settings {
    
    private Heuristic heuristic;
    private boolean animated;
    public static final int maxInt = Integer.MAX_VALUE-10000000;
    
    public enum Heuristic {
        MANHATTAN, EUCLIDEAN;
    }

    /**
     * Konstruktori luo oletusarvoiset asetukset.
     */
    public Settings() {
        this.heuristic = Heuristic.EUCLIDEAN;
        this.animated = false;
    }
    
    public Heuristic getHeuristic() {
        return heuristic;
    }

    public boolean isAnimated() {
        return animated;
    }

    public void changeHeuristic() {
        if (this.heuristic == Heuristic.MANHATTAN) {
            this.heuristic = Heuristic.EUCLIDEAN;
        } else {
            this.heuristic = Heuristic.MANHATTAN;
        }
    }
    
    public void setAnimated(boolean animated) {
        this.animated = animated;
    }
}
