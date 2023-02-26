package Pathfinder.algorithms;

import Pathfinder.domain.Graphnode;

/**
 * Rajapinta reitinhakualgoritmeille.
 * @author lasse
 */
public interface Calculable <T>{


    public boolean calculate(int ay, int ax, int by, int bx);
    
    boolean adjust(Graphnode next, int newVal);
    
    public void reportSize();
}
