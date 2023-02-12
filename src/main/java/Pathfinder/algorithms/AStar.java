package Pathfinder.algorithms;

import Pathfinder.domain.Graphnode;
import Pathfinder.utility.GraphBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A* algoritmin toteuttava luokka. Perii luokan 'Dijkstra'. Toteutus
 * eroaa ainoastaan 'adjust' metodin osalta.
 * @author lasse
 */
public class AStar extends Dijkstra {
    
    public AStar(GraphBuilder builder, Solver solver) {
        super(builder, solver);
    }
    /**
     * Lisätään solmun ilmentymä jonoon laskemalla heuristinen arvio mukaan.
     * @param next
     * @param g 
     */
    @Override
    public boolean adjust(Graphnode next, int newDist) {
        if (newDist >= next.getDistance()) {
            return false;
        }
        next.setDistance(newDist);
        int ny = next.getY();
        int nx = next.getX();
        super.q.add(new int[]{newDist+next.getHDistance(), ny, nx});
        return true;
    }
    
    @Override
    public void addNode(Graphnode node) {
        solver.addAStarNode(node);
    }
}
