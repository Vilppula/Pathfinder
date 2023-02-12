package Pathfinder.algorithms;

import Pathfinder.domain.Graphnode;

/**
 * Rajapinta reitinhakualgoritmeille.
 * @author lasse
 */
public interface Calculable {


    public boolean calculate(int ay, int ax, int by, int bx);
    
    public boolean adjust(Graphnode next, int newVal);
}
