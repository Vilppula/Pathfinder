package Pathfinder.algorithms;

import Pathfinder.domain.Graphnode;
import Pathfinder.utility.GraphBuilder;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Fringe Search algoritmin toteuttava luokka.
 * @author lasse
 */
public class FringeSearch implements Calculable {

    private GraphBuilder builder;
    private Deque<Graphnode> now;
    private Deque<Graphnode> later;
    private Graphnode goal;
    private int treshold;
    private boolean found;
    
    public FringeSearch(GraphBuilder builder) {
        this.builder = builder;
    }
    
    /**
     * Etsi lyhyin polku pisteestä (ax,ay) pisteeseen (bx,by)
     * @param ay
     * @param ax
     * @param by
     * @param bx
     * @return 
     */
    @Override
    public boolean calculate(int ay, int ax, int by, int bx) {
        this.now = new ArrayDeque<>();
        this.later = new ArrayDeque<>();
        goal = builder.getGraphnode(by, bx);
        Graphnode root = builder.getGraphnode(ay, ax);
        treshold = root.getHDistance();                                         //Ensimmäisen iteraation raja-arvoksi tulee lähtösolmun heuristinen etäisyys maalisolmusta..
        root.setDistance(0);                                                    //Alkusomun etäisyys on 0
        found = false;  
        
        
        while (!found) {
        //Lue solmu jonosta now
        Graphnode head = now.pollFirst();
        for (Graphnode next: head.getNeighbors()) {                             //Luetaan solmun node naapurit
            if (search(head, next)) {
                if (found) {
                    return true;
                }
            }
        }
        
        
        //Jos maalia ei löydetty tällä iteraatiolla, tee jonosta 'later' jono 'now'
        for (Graphnode laterNode: later) {
            now.addLast(laterNode);
        }
        
        }
        
        return false;
    }
    
    /**
     * Rekursiivinen metodi. Käydään läpi solmun node lapset vasemmalta oikealle.
     * @param previous
     * @param node
     * @return 
     */
    public boolean search(Graphnode previous, Graphnode node) {
        if (node == goal) {
            found = true;
            return true;
        }
        for (Graphnode next: node.getNeighbors()) {
            if (search(node, next)) {
                return true;
            }
        }
        return false;
    }

    public boolean adjust(Graphnode grphnd, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
