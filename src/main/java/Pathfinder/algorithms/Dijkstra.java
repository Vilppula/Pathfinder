package Pathfinder.algorithms;

import Pathfinder.domain.Graphnode;
import Pathfinder.utility.GraphBuilder;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Dijkstran algorimin toteuttava luokka.
 * @author lasse
 */
public class Dijkstra {
    
    private GraphBuilder builder;
    private PriorityQueue<Graphnode> q;
    
    public Dijkstra(GraphBuilder builder) {
        this.builder = builder;
        this.q = new PriorityQueue<>(Comparator.comparing(Graphnode::getDistance));
    }
   
    /**
     * Ratkaisee lyhyimmän reitin pisteestä (ax,ay) pisteeseen (bx,by).
     * Palauttaa lyhyimmän reitin listana.
     * @param ay
     * @param ax
     * @param by
     * @param bx
     * @return 
     */
    public List<int[]> calculate(int ay, int ax, int by, int bx) {
        if (!builder.validatePoint(ay, ax) || !builder.validatePoint(by, bx)) {
            return null;
        }
        while (!q.isEmpty()) {
            Graphnode node = q.poll();
            if (node.isExpanded()) {
                continue;
            }
            node.setExpanded();
            for (Graphnode next: node.getNeighbors()) {
                if (next.isExpanded()) {
                    continue;
                }
                next.setDistance(Math.min(node.getDistance() + 1, next.getDistance()));
                q.add(next);
            }
        }
        return null;
    }
}
