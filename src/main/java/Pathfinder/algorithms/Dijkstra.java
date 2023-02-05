package Pathfinder.algorithms;

import Pathfinder.domain.Graphnode;
import Pathfinder.utility.GraphBuilder;
import java.util.PriorityQueue;

/**
 * Dijkstran algorimin toteuttava luokka.
 * @author lasse
 */
public class Dijkstra implements Calculable {
    
    private GraphBuilder builder;
    protected PriorityQueue<int[]> q;     //Jonossa kolmikoita (distance, y, x), jotka kertovat lasketun etäisyyden kuhunkin koordinaattiin.
    
    public Dijkstra(GraphBuilder builder) {
        this.builder = builder;
    }
   
    /**
     * Ratkaisee lyhyimmän reitin pisteestä (ay, ax) pisteeseen (by, bx) (huom.
     * koordinaatit esitetty muodossa (y,x).
     * Reitti löytyy Graphnodejen 'previous'-viitteitä seuraamalla.
     * seuraamalla.
     * @param ay
     * @param ax
     * @param by
     * @param bx
     * @return 
     */
    public boolean calculate(int ay, int ax, int by, int bx) {
        if (!builder.reset()) {
            return false;
        }
        if (!builder.validatePoint(ay, ax) || !builder.validatePoint(by, bx)) {
            return false;
        }
        this.q = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        builder.getGraphnode(ay, ax).setDistance(0);
        builder.getGraphnode(ay, ax).isInQ();
        q.add(new int[]{0, ay, ax});
        
        while (!q.isEmpty()) {
            int[] c = q.poll();
            Graphnode node = builder.getGraphnode(c[1], c[2]);
            node.notInQ();
            if (node.isExpanded()) {
                continue;
            }
            node.setExpanded();                                                 //Asetetaan tämä solmu käsitellyksi
            for (Graphnode next: node.getNeighbors()) {
                if (next.isExpanded()) {                                        //Jo tarkasteltuihin solmuihin ei palata
                    continue;
                }
                if (adjust(next, node.getDistance() + 1)) {                     //Jos naapurille lisättiin uusi parempi etäisyys...
                    next.setPrevious(node);                                     //...merkitään sille edeltäjäksi tämä solmu.
                }
                if (by == next.getY() && bx == next.getX()) {                   //Jos seuraavana oleva solmu on maalisolmu, lopetetaan haku.
                    return true;
                }
            }
        }
        return false;                                                           //Haku päättyy, maalisolmua ei löytynyt
    }
    
    /**
     * Dijkstran algoritmin mukainen etäisyyden säätö.
     * @param next
     * @param now 
     */
    public boolean adjust(Graphnode next, int g) {                              //Säädetään naapurisomun etäisyyttä.
        if (next.getDistance() <= g) {                                          //Tarkistetaan onko naapurille annettava etäisyys paras tähänastisista.
            return false;
        }
        next.setDistance(g);
        int ny = next.getY();
        int nx = next.getX();
        q.add(new int[]{g, ny, nx});                                            //Lisätään naapurisolmu jonoon
        return true;
    }
}
