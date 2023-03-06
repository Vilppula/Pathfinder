package Pathfinder.domain;

import Pathfinder.utility.Settings;
import java.util.ArrayList;
import java.util.List;

/**
 * Yksitt‰ist‰ verkon solmua edustava luokka. Useita oliomuuttujia tarvitaan 
 * tilastointitiedon tallentamisessa, joten t‰m‰n luokan p‰‰asiallinen tarkoitus
 * on mahdollistaa j‰rkev‰ vertailu algoritmien v‰lill‰.
 * @author lasse
 */
public class Graphnode {
    
    private List<Graphnode> neighbors;
    private boolean expanded;
    private int x;
    private int y;
    private int distance;       //Laskettu paras et‰isyys t‰lle solmulle
    private int HDistance;      //Heuristinen et‰isyys t‰lle solmulle
    private int inQ;            //T‰m‰ luku kertoo montako kertaa t‰m‰ solmu esiintyy jonossa
    private int maxInQ;         
    private Graphnode previous; //Viite edelt‰v‰‰n solmuun lyhyimm‰ll‰ reitill‰.
    private List<Long> visitTimes;    //T‰m‰ luku kertoo ajan jolloin t‰ss‰ solmussa vierailtiin
    private List<Long> expansionTimes;  //T‰m‰ luku kertoo ajan jolloin t‰m‰ solmu k‰siteltiin.
    private int expansions;     //T‰m‰ luku kertoo kuinka monta kertaa t‰m‰ solmu k‰siteltiin (Fringe Search)
    private int visits;         //Montako kertaa solmussa vierailtiin
    
    /**
     * Luo uuden verkon solmun. Tarvitsee koordinaatit, jotka ovat suhteellisia
     * k‰yttˆliittym‰‰n ladattuun karttapohjaan. Et‰isyydet ovat aluksi
     * n‰enn‰isesti ‰‰rettˆmi‰.
     * @param y
     * @param x 
     */
    public Graphnode(int y, int x) {
        this.neighbors = new ArrayList<>();
        this.HDistance = Settings.maxInt;
        this.x = x;
        this.y = y;
        reset();
    }
    
    /**
     * Nollataan solmu.
     */
    public void reset() {
        this.expansions = 0;
        this.expanded = false;
        this.distance = Settings.maxInt;  //K‰ytet‰‰n omaa maksimiarvoa ettei kokonaisluvuilla laskeminen vie arvoa yli kokonaislukumaksimin
        this.inQ = 0;
        this.visitTimes = new ArrayList<>();
        this.expansionTimes = new ArrayList<>();
        this.previous = null;
        this.visits = 0;
        this.maxInQ = 0;
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

    public void notExpanded() {
        this.expanded = false;
    }

    public void setPrevious(Graphnode previous) {
        this.previous = previous;
    }

    public void addVisit(Long time) {
        this.visitTimes.add(time);
        this.visits++;
    }

    public void addExpansion(Long time) {
        this.expanded = true;
        this.expansionTimes.add(time);
        this.expansions++;
    }
    
    public void addInQ() {
        this.inQ++;
        this.maxInQ = Math.max(maxInQ, inQ);
    }
    
    public void takeFromQ() {
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

    public List<Long> getInListValues() {
        return visitTimes;
    }

    public List<Long> getExpandedValues() {
        return expansionTimes;
    }

    public int getMaxInQ() {
        return maxInQ;
    }

    public int getVisits() {
        return visits;
    }

    public int getExpansions() {
        return expansions;
    }
    
    @Override
    public String toString() {
        return "(x:" + x + ", y:" + y + ", d:"+distance+")";
    }
}
