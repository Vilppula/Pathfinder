package Pathfinder.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Yksittäistä verkon solmua edustava luokka.
 * @author lasse
 */
public class Graphnode {
    
    private List<Graphnode> neighbors;
    private boolean expanded;
    private int x;
    private int y;
    private int distance;
    private int HDistance;

    /**
     * Luo uuden verkon solmun. Tarvitsee koordinaatit. Etäisyydet ovat aluksi
     * näennäisesti äärettömiä.
     * @param y
     * @param x 
     */
    public Graphnode(int y, int x) {
        this.neighbors = new ArrayList<>();
        this.expanded = false;
        this.distance = Integer.MAX_VALUE;
        this.HDistance = Integer.MAX_VALUE;
        this.x = x;
        this.y = y;
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
    }
    
    public int[] getCoordinate() {
        return new int[] {this.y, this.x};
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

    @Override
    public String toString() {
        return "Graphnode{" + "x=" + x + ", y=" + y + "} dist="+distance;
    }
}
