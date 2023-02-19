package Pathfinder.algorithms;

import Pathfinder.domain.Graphnode;
import Pathfinder.utility.GraphBuilder;
import Pathfinder.utility.Settings;
import java.util.ArrayList;
import java.util.List;

/**
 * T‰m‰ luokan metodit tarjoavat p‰‰n‰kym‰n kontrollero-luokalle 
 * rajapinnan algoritmeihin. Luokka huolehtii myˆs statistiikan ker‰‰misest‰.
 * @author lasse
 */
public class Solver {
    
    private Dijkstra dijkstra;
    private AStar aStar;
    private FringeSearch fringe;
    private GraphBuilder builder;
    private Settings settings;
    private int ax, ay, bx, by;
    private List<Calculable> algorithms;
    private List<Graphnode> dijkstraNodes;
    private List<Graphnode> aStarNodes;
    private List<Graphnode> fringeSearchNodes;


    public Solver(GraphBuilder builder, Settings settings) {
        this.dijkstra = new Dijkstra(builder, this);
        this.aStar = new AStar(builder, this);
        this.fringe = new FringeSearch(builder, this);
        this.builder = builder;
        this.settings = settings;
        this.dijkstraNodes = new ArrayList<>();
        this.aStarNodes = new ArrayList<>();
        this.fringeSearchNodes = new ArrayList<>();
        this.algorithms = new ArrayList<>();
        ax = ay = bx = by = -1;
    }
    
    /**
     * Lis‰‰/ poista Dijkstran algoritmi suoritettaviin.
     */
    public void dijkstra() {
        if (algorithms.contains(dijkstra)) {
            algorithms.remove(dijkstra);
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
    
    /**
     * Metodia kutsutaan kun k‰yttˆliittym‰n solve-painiketta on painettu.
     * K‰y l‰pi valitut algoritmit.
     * @return 
     */
    public boolean solve() {
        boolean success = false;
        this.dijkstraNodes = new ArrayList<>();
        this.aStarNodes = new ArrayList<>();
        this.fringeSearchNodes = new ArrayList<>();
        if (ax == -1 || ay == -1 || by == -1 || bx == -1) {
            return false;
        }
        builder.heuristic(by, bx);
        for (Calculable c: this.algorithms) {
            System.out.println("Ratkaistaan "+c.getClass());
            builder.reset();
            success = c.calculate(ay, ax, by, bx);
        }
        return success;
    }

    /**
     * Lis‰t‰‰n solmuja listalle niiden k‰sittelyj‰rjestyksess‰. T‰m‰n avulla
     * visualisointi voidaan tehd‰ vastaavassa j‰rjestyksess‰.
     * @param node 
     */
    public void addDijkstraNode(Graphnode node) {
        this.dijkstraNodes.add(node);
    }
    
    public void addAStarNode(Graphnode node) {
        this.aStarNodes.add(node);
    }
    
    /**
     * Koska Fringe Searchin iteraatiot saattavat vierailla samassa solmussa
     * monta kertaa, lis‰t‰‰n listalle null arvo erottamaan 'somurintamat'
     * toisistaan.
     * @param fringe 
     */
    public void addFringeSearchNode(Graphnode node) {
        this.fringeSearchNodes.add(node);
    }
    
    //==========================================================================
    public int getBx() {
        return bx;
    }

    public int getBy() {
        return by;
    }

    public List<Graphnode> getDijkstraNodes() {
        return dijkstraNodes;
    }
    
    public List<Graphnode> getAStarNodes() {
        return aStarNodes;
    }

    public List<Graphnode> getFringeSearchNodes() {
        return fringeSearchNodes;
    }
}
