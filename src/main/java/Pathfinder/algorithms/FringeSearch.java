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
     * Etsi lyhyin polku pisteest‰ (ax,ay) pisteeseen (bx,by)
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
        Graphnode root = builder.getGraphnode(ay, ax);
        treshold = root.getHDistance();                                         //Ensimm‰isen iteraation raja-arvoksi tulee l‰htˆsolmun heuristinen et‰isyys maalisolmusta..
        root.setDistance(0);                                                    //Alkusomun et‰isyys on 0
        now.add(root);
        root.isInQ();
        
        while (!now.isEmpty()) {
            int newTreshold = Integer.MAX_VALUE;                                //Talletetaan uusi totuudenmukaisempi raja-arvo t‰h‰n muuttujaan.
            addNode(null);                                                      //Lis‰t‰‰n null merkiksi uuden iteraation alkamisesta. T‰m‰ on visualisoinnin kannalta parasta.
            while (!now.isEmpty()) {                                            //Niin kauan kuin jonossa 'now' on solmuja, k‰yd‰‰n ne l‰pi
                Graphnode head = now.poll();
                addNode(head);
                if (head.getY() == by && head.getX() == bx) {                   //Tarkasta onko maalisolmu
                    return true;
                }
                int f = head.getDistance() + head.getHDistance();               //Arvo f on summa 'solmun head et‰isyys l‰htˆsolmusta + heuristinen et‰isyys maaliin'.
                if (f > treshold) {                                             //Jos solmun f-arvo ylitt‰‰ raja-arvon, siirr‰ listalle 'later'...
                    later.add(head);
                    newTreshold = Math.min(newTreshold, f);
                    continue;
                }
                addNode(head);                                                  //...muuten lis‰t‰‰n solmu 'head' k‰sitteltyjen listalle...
                for (Graphnode next: head.getNeighbors()) {                     //...k‰yd‰‰n l‰pi sen naapurit...
                    if (next == head.getPrevious()) {
                        continue;
                    }
                    int newDist = head.getDistance() +                          //...lasketaan uusi et‰isyys naapurisolmulle solmun 'head' kautta...
                            (next.getX() != head.getX()
                                && next.getY() != head.getY() ? 141 : 100);
                    if (adjust(next, newDist)) {                                //...yritet‰‰n muuttaa naapurin et‰isyysarvoa...
                        now.add(next);                                          //...ja jos se onnistuu, lis‰t‰‰n naapuri listalle 'now'...
                        next.isInQ();
                        next.setPrevious(head);                                 //...merkit‰‰n naapurin edelt‰j‰ksi solmu 'head'
                    }
                }
            }
            while (!later.isEmpty()) {
                now.add(later.poll());
            }
            treshold = newTreshold;                                             //P‰ivitet‰‰n raja-arvo vastaamaan parasta lˆydetty‰ f-arvoa
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
     * Lis‰‰ solmun solverin FringeSearchNodes-listalle. Solmut tulevat listalle k‰sittelyj‰rjestyksess‰.
     * @param node 
     */
    public void addNode(Graphnode node) {
        solver.addFringeSearchNode(node);
    }
    
    /**
     * Raportoidaan muistissa olevien listojen koko. Now + Later
     */
    public void reportSize() {
        solver.observer.saveSize(this, now, later);
    }
}
