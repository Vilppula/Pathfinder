package Pathfinder.utility;

/**
 * Ohjelman muuttuvat asetukset. Vaihdetaan k�ytt�liittym�n kautta.
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
