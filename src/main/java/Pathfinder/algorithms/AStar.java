package Pathfinder.algorithms;

import Pathfinder.domain.Graphnode;
import Pathfinder.utility.GraphBuilder;

/**
 * A* algoritmin toteuttava luokka. Perii luokan 'Dijkstra'. Toteutus
 * eroaa ainoastaan 'adjust' metodin osalta.
 * @author lasse
 */
public class AStar extends Dijkstra {
    
    public AStar(GraphBuilder builder) {
        super(builder);
    }
    /**
     * Lis‰t‰‰n solmun ilmentym‰ jonoon laskemalla heuristinen arvio mukaan.
     * @param next
     * @param g 
     */
    @Override
    public boolean adjust(Graphnode next, int g) {
        if (next.getDistance() <= g) {
            return false;
        }
        next.setDistance(g);
        int ny = next.getY();
        int nx = next.getX();
        super.q.add(new int[]{g + next.getHDistance(), ny, nx});
        return true;
    }
}
