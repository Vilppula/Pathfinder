package Pathfinder.algorithms;

import Pathfinder.domain.Graphnode;
import Pathfinder.utility.GraphBuilder;
import Pathfinder.utility.Observer;
import Pathfinder.utility.Settings;
import java.util.ArrayList;
import java.util.List;

/**
 * T‰m‰ luokan metodit tarjoavat p‰‰n‰kym‰n kontrollero-luokalle 
 * rajapinnan algoritmeihin.
 * @author lasse
 */
public class Solver {
    
    private Dijkstra dijkstra;
    private AStar aStar;
    private FringeSearch fringe;
    private GraphBuilder builder;
    public Settings settings;
    private int ax, ay, bx, by;
    private List<Calculable> algorithms;
    public Observer observer;

    
    public Solver(GraphBuilder builder, Settings settings, Observer observer) {
        this.dijkstra = new Dijkstra(builder, this);
        this.aStar = new AStar(builder, this);
        this.fringe = new FringeSearch(builder, this);
        this.builder = builder;
        this.settings = settings;
        this.algorithms = new ArrayList<>();
        this.observer = observer;
        ax = ay = bx = by = -1;
    }
    
    /**
     * Lis‰‰/ poista Dijkstran algoritmi suoritettaviin.
     */
    public void dijkstra() {
        if (algorithms.contains(dijkstra)) {
            algorithms.remove(dijkstra);
            observer.clear(dijkstra);
        } else {
            algorithms.add(dijkstra);
        }
    }
    
    /**
     * Lis‰‰/ poista A* algoritmi suoritettaviin.
     */
    public void aStar() {
        if (algorithms.contains(aStar)) {
            algorithms.remove(aStar);
            observer.clear(aStar);
        } else {
            algorithms.add(aStar);
        }
    }
    
    /**
     * Lis‰‰/ poista Fringe Search algoritmi suoritettaviin.
     */
    public void fringeSearch() {
        if (algorithms.contains(fringe)) {
            algorithms.remove(fringe);
            observer.clear(fringe);
        } else {
            algorithms.add(fringe);
        }
    }
    
    /**
     * Lis‰‰/muuta valitun l‰htˆsolmun koordinaatit
     * @param ay
     * @param ax 
     */
    public void addA(int ay, int ax) {
        if (!builder.validatePoint(ay, ax)) {
            return;
        }
        this.ax = ax; this.ay = ay;
        
    }
    
    /**
     * Lis‰‰/muuta valitun maalisolmun koordinaatit.
     * @param by
     * @param bx 
     */
    public void addB(int by, int bx) {
        if (!builder.validatePoint(by, bx)) {
            return;
        }
        this.bx = bx; this.by = by;
    }
    
    //==========================================================================
    /**
     * Metodia kutsutaan kun k‰yttˆliittym‰n solve-painiketta on painettu.
     * K‰y l‰pi valitut algoritmit.
     * @return 
     */
    public boolean solve() {
        boolean success = false;
        if (ax == -1 || ay == -1 || by == -1 || bx == -1) {
            return false;
        }
        builder.heuristic(by, bx);
        for (Calculable c: this.algorithms) {
            builder.reset();                                                    //Alusta verkon solmut (Graphnode-oliot) algoritmien v‰lill‰
            observer.start(c);
            success = c.calculate(ay, ax, by, bx);
            observer.stop(c);
        }
        return success;
    }

    //==========================================================================
    public int getBx() {
        return bx;
    }

    public int getBy() {
        return by;
    }

    public List<Graphnode> getDijkstraNodes() {
        return observer.getExpandedNodes(this.dijkstra);
    }
    
    public List<Graphnode> getAStarNodes() {
        return observer.getExpandedNodes(this.aStar);
    }

    public List<Graphnode> getFringeSearchNodes() {
        return observer.getExpandedNodes(this.fringe);
    }

    public List<Calculable> getAlgorithms() {
        return algorithms;
    }
}
