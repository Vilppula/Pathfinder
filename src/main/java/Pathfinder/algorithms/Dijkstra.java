package Pathfinder.algorithms;

import Pathfinder.domain.Graphnode;
import Pathfinder.utility.GraphBuilder;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Dijkstran algorimin toteuttava luokka.
 * @author lasse
 */
public class Dijkstra implements Calculable {
    
    private GraphBuilder builder;
    protected Solver solver;
    protected PriorityQueue<int[]> q;     //Jonossa kolmikoita (distance, y, x), jotka kertovat lasketun et‰isyyden kuhunkin koordinaattiin.
    private int actionNumber;
    
    /**
     * Dijkstran konstruktori. Verkko annetaan parametrina.
     * @param builder 
     */
    public Dijkstra(GraphBuilder builder, Solver solver) {
        this.builder = builder;
        this.solver = solver;
    }
   
    /**
     * Ratkaisee lyhyimm‰n reitin pisteest‰ (ay, ax) pisteeseen (by, bx) (huom.
     * koordinaatit esitetty muodossa (y,x).
     * Reitti lˆytyy Graphnodejen 'previous'-viitteit‰ seuraamalla.
     * seuraamalla.
     * @param ay
     * @param ax
     * @param by
     * @param bx
     * @return 
     */
    public boolean calculate(int ay, int ax, int by, int bx) {
        Scanner scan = new Scanner(System.in);
        this.q = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        builder.getGraphnode(ay, ax).setDistance(0);
        builder.getGraphnode(ay, ax).isInQ();
        q.add(new int[]{0, ay, ax});
        actionNumber = 0;
        while (!q.isEmpty()) {
            int[] c = q.poll();
            Graphnode node = builder.getGraphnode(c[1], c[2]);
            node.notInQ();
            if (node.isExpanded()) {
                continue;
            }
            node.addExpandedValue(actionNumber);                                //Lis‰‰ solmulle vuoronumeron jolla sen tarkastelu aloitettiin
            actionNumber++;
            addNode(node);
            node.setExpanded();                                                 //Asetetaan t‰m‰ solmu k‰sitellyksi
            for (Graphnode next: node.getNeighbors()) {
                if (next.isExpanded()) {                                        //Jo tarkasteltuihin solmuihin ei palata
                    continue;
                }
                //Lis‰t‰‰n diagonaalisesti siirrytt‰erss‰ et‰isyyteen 141, muuten 100
                int newDist = node.getDistance() + 
                        (Math.abs(next.getX()-node.getX()) + Math.abs(next.getY() - node.getY()) == 2 ? 141 : 100);
                if (adjust(next, newDist)) {
                    next.setPrevious(node);
                }
                if (by == next.getY() && bx == next.getX()) {                   //Jos seuraavana oleva solmu on maalisolmu, lopetetaan haku.
                    return true;
                }
            }
        }
        return false;                                                           //Haku p‰‰ttyy, maalisolmua ei lˆytynyt
    }
    
    /**
     * Dijkstran algoritmin mukainen et‰isyyden s‰‰tˆ ja jonoon lis‰‰minen.
     * @param next
     * @param now 
     */
    public boolean adjust(Graphnode next, int newDist) {                              //S‰‰det‰‰n naapurisomun et‰isyytt‰.
        if (next.getDistance() <= newDist) {                                        //Tarkistetaan onko naapurille annettava et‰isyys paras t‰h‰nastisista.
            return false;
        }
        next.setDistance(newDist);
        int ny = next.getY();
        int nx = next.getX();
        q.add(new int[]{newDist, ny, nx});                                            //Lis‰t‰‰n naapurisolmu jonoon
        next.addInListValue(actionNumber);                                      //Lis‰t‰‰n naapurisolmulle vuoronumero jolla se asetettiin jonoon
        //solver.addToNodelist(next);
        actionNumber++;
        return true;
    }
    
    public void addNode(Graphnode node) {
        solver.addDijkstraNode(node);   
    }
}
