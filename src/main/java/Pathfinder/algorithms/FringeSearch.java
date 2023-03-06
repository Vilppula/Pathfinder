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
    private Solver solver;
    private Deque<Graphnode> now;
    private Deque<Graphnode> later;
    private int treshold;
    
    public FringeSearch(GraphBuilder builder, Solver solver) {
        this.builder = builder;
        this.solver = solver;
    }
    
    /**
     * Etsi lyhyin polku pisteest� (ax,ay) pisteeseen (bx,by)
     * @param ay
     * @param ax
     * @param by
     * @param bx
     * @return 
     */
    @Override
    public boolean calculate(int ay, int ax, int by, int bx) {
        
        // Alustetaan =============================
        this.now = new ArrayDeque<>();
        this.later = new ArrayDeque<>();
        Graphnode root = builder.getGraphnode(ay, ax);
        treshold = root.getHDistance();                                         //Ensimm�isen iteraation raja-arvoksi tulee l�ht�solmun heuristinen et�isyys maalisolmusta..
        root.setDistance(0);                                                    //Alkusomun et�isyys on 0
        now.add(root);
        root.addInQ();
        boolean found = false;
        
        // Algoritmin l�pik�ynti ======================
        while (!now.isEmpty()) {
            int newTreshold = Integer.MAX_VALUE;                                //Talletetaan uusi totuudenmukaisempi raja-arvo t�h�n muuttujaan.
            solver.observer.addAsExpanded(this, null);                          //Lis�t��n null merkiksi uuden iteraation alkamisesta. T�m� on visualisoinnin kannalta parasta.
            
            // Iteraation alku ============================
            while (!now.isEmpty()) {                                            //Niin kauan kuin jonossa 'now' on solmuja, k�yd��n ne l�pi
                Graphnode head = now.peek();
                int f = head.getDistance() + head.getHDistance();               //Arvo f on summa 'solmun head et�isyys l�ht�solmusta + heuristinen et�isyys maaliin'.
                if (f > treshold) {                                             //Jos solmun f-arvo ylitt�� raja-arvon, siirr� listalle 'later'...
                    later.add(head);
                    newTreshold = Math.min(newTreshold, f);
                    now.poll();
                    continue;
                }
                if (head.getY() == by && head.getX() == bx) {                   //Tarkasta onko maalisolmu
                    found = true;
                    break;
                }
                solver.observer.addAsExpanded(this, head);                      //...muuten lis�t��n solmu 'head' k�sitteltyjen listalle...
                head.addExpansion(System.nanoTime());
                for (Graphnode next: head.getNeighbors()) {                     //...k�yd��n l�pi sen naapurit...
                    if (next == head.getPrevious()) {                           //...ei palata edelt�j�solmuun...
                        continue;
                    }
                    next.addVisit(System.nanoTime());
                    int newDist = head.getDistance() +                          //...lasketaan uusi et�isyys naapurisolmulle solmun 'head' kautta...
                            (next.getX() != head.getX()
                                && next.getY() != head.getY() ? 141 : 100);
                    if (!adjust(next, newDist)) {                                //...yritet��n muuttaa naapurin et�isyysarvoa...
                        continue;
                    }
                    //now.remove(next); 
                    now.add(next);                                              //...ja jos se onnistuu, lis�t��n naapuri listalle 'now'...
                    reportSize();
                    next.addInQ();
                    next.setPrevious(head);                                     //...merkit��n naapurin edelt�j�ksi solmu 'head'
                    
                }
                now.poll();
            }
            if (found) {
                return true;
            }
            treshold = newTreshold;                                             //P�ivitet��n raja-arvo vastaamaan parasta l�ydetty� f-arvoa
            now = later;
            later = new ArrayDeque();
        }
        
        return false;
    }
    
    /**
     * Fringe Searchin adjust-metodi
     * @param node
     * @param newDist
     * @return 
     */
    public boolean adjust(Graphnode node, int newDist) {
        if (newDist >= node.getDistance()) {
            return false;
        }
        node.setDistance(newDist);
        return true;
    }
    
    
    /**
     * Raportoidaan muistissa olevien listojen koko. Now + Later.
     */
    public void reportSize() {
        solver.observer.saveSize(this, now);
    }
}
