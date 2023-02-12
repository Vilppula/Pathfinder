package Pathfinder.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Yksitt�ist� verkon solmua edustava luokka. Useita oliomuuttujia tarvitaan 
 * tilastointitiedon tallentamisessa, joten t�m�n luokan p��asiallinen tarkoitus
 * on mahdollistaa j�rkev� vertailu algoritmien v�lill�.
 * @author lasse
 */
public class Graphnode {
    
    private List<Graphnode> neighbors;
    private boolean expanded;
    private int x;
    private int y;
    private int distance;       //Laskettu paras et�isyys t�lle solmulle
    private int HDistance;      //Heuristinen et�isyys t�lle solmulle
    private int inQ;            //T�m� luku kertoo montako kertaa t�m� solmu esiintyy jonossa
    private Graphnode previous; //Viite edelt�v��n solmuun lyhyimm�ll� reitill�.
    private List<Integer> inListValues;    //T�m� luku kertoo vuoronumeron jolla t�m� solmu lis�ttiin jonoon.
    private List<Integer> expandedValues;  //T�m� luku kertoo vuoronumeron jolla t�m� solmu k�siteltiin.
    private int expansions;     //T�m� luku kertoo kuinka monta kertaa t�m� solmu k�siteltiin (Fringe Search)
    
    /**
     * Luo uuden verkon solmun. Tarvitsee koordinaatit, jotka ovat suhteellisia
     * k�ytt�liittym��n ladattuun karttapohjaan. Et�isyydet ovat aluksi
     * n�enn�isesti ��rett�mi�.
     * @param y
     * @param x 
     */
    public Graphnode(int y, int x) {
        this.neighbors = new ArrayList<>();
        this.HDistance = Integer.MAX_VALUE;
        this.x = x;
        this.y = y;
        reset();
    }
    
    public void reset() {
        this.expansions = 0;
        this.expanded = false;
        this.distance = Integer.MAX_VALUE;
        this.inQ = 0;
        this.inListValues = new ArrayList<>();
        this.expandedValues = new ArrayList<>();
        this.previous = null;
    }
    
    public void addNeighbor(Graphnode neighbor) {
        this.neighbors.add(neighbor);
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setHDistance(int hDistance) {
        this.HDistance = hDistance;
    }

    public void setExpanded() {
        this.expanded = true;
        this.expansions++;
    }
    
    public void notExpanded() {
        this.expanded = false;
    }

    public void setPrevious(Graphnode previous) {
        this.previous = previous;
    }

    public void addInListValue(int inListValue) {
        this.inListValues.add(inListValue);
    }

    public void addExpandedValue(int expandedValue) {
        this.expandedValues.add(expandedValue);
    }
    
    public void isInQ() {
        this.inQ++;
    }
    
    public void notInQ() {
        this.inQ--;
    }
    
    public int[] getCoordinate() {
        return new int[] {this.y, this.x};
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getX() {
        return this.x;
    }

    public int getDistance() {
        return distance;
    }

    public int getHDistance() {
        return HDistance;
    }
    
    public List<Graphnode> getNeighbors() {
        return neighbors;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public int getInQ() {
        return inQ;
    }

    public Graphnode getPrevious() {
        return previous;
    }

    public List<Integer> getInListValues() {
        return inListValues;
    }

    public List<Integer> getExpandedValues() {
        return expandedValues;
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ", "+distance+")";
    }
}
