package Pathfinder.algorithms;

import Pathfinder.domain.Graphnode;
import Pathfinder.utility.GraphBuilder;

/**
 * A* algoritmin toteuttava luokka. Perii luokan 'Dijkstra'. Toteutus
 * eroaa 'adjust'- ja 'addNode'-metodien osalta.
 * @author lasse
 */
public class AStar extends Dijkstra {
    
    public AStar(GraphBuilder builder, Solver solver) {
        super(builder, solver);
    }
    /**
     * Lis‰t‰‰n solmun ilmentym‰ jonoon laskemalla heuristinen arvio mukaan.
     * @param next
     * @param g 
     */
    @Override
    public boolean adjust(Graphnode next, int newDist) {
        if (newDist > next.getDistance()) {
            return false;
        }
        next.setDistance(newDist);
        int ny = next.getY();
        int nx = next.getX();
        super.q.add(new int[]{newDist+next.getHDistance(), ny, nx});            //f(x) = g(x) + h(x) (erona dijkstraan, jossa f(x) = g(x))
        return true;
    }
    
    /**
     * Lis‰‰ solmun solverin AStar listaan (A*:n tarkastellut solmut tarkasteluj‰rjestyksess‰)
     * @param node 
     */
    @Override
    public void addNode(Graphnode node) {
        solver.addAStarNode(node);
    }
}
