package Pathfinder.utility;

/**
 * Ohjelman muuttuvat asetukset. Vaihdetaan käyttöliittymän kautta.
 * @author lasse
 */
public class Settings {
    
    public Heuristic heuristic;
    
    public enum Heuristic {
        MANHATTAN, EUCLIDEAN;
    }

    /**
     * Konstruktori luo oletusarvoiset asetukset.
     */
    public Settings() {
        this.heuristic = Heuristic.MANHATTAN;
    }
    
    public void setHeuristic(Heuristic heuristic) {
        this.heuristic = heuristic;
    }
}
