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
    protected PriorityQueue<int[]> q;     //Jonossa kolmikoita (distance, y, x), jotka kertovat lasketun et�isyyden kuhunkin koordinaattiin.
    
    public Dijkstra(GraphBuilder builder) {
        this.builder = builder;
    }
   
    /**
     * Ratkaisee lyhyimm�n reitin pisteest� (ay, ax) pisteeseen (by, bx) (huom.
     * koordinaatit esitetty muodossa (y,x).
     * Reitti l�ytyy Graphnodejen 'previous'-viitteit� seuraamalla.
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
            node.setExpanded();                                                 //Asetetaan t�m� solmu k�sitellyksi
            for (Graphnode next: node.getNeighbors()) {
                if (next.isExpanded()) {                                        //Jo tarkasteltuihin solmuihin ei palata
                    continue;
                }
                if (adjust(next, node.getDistance() + 1)) {                     //Jos naapurille lis�ttiin uusi parempi et�isyys...
                    next.setPrevious(node);                                     //...merkit��n sille edelt�j�ksi t�m� solmu.
                }
                if (by == next.getY() && bx == next.getX()) {                   //Jos seuraavana oleva solmu on maalisolmu, lopetetaan haku.
                    return true;
                }
            }
        }
        return false;                                                           //Haku p��ttyy, maalisolmua ei l�ytynyt
    }
    
    /**
     * Dijkstran algoritmin mukainen et�isyyden s��t�.
     * @param next
     * @param now 
     */
    public boolean adjust(Graphnode next, int g) {                              //S��det��n naapurisomun et�isyytt�.
        if (next.getDistance() <= g) {                                          //Tarkistetaan onko naapurille annettava et�isyys paras t�h�nastisista.
            return false;
        }
        next.setDistance(g);
        int ny = next.getY();
        int nx = next.getX();
        q.add(new int[]{g, ny, nx});                                            //Lis�t��n naapurisolmu jonoon
        return true;
    }
}
