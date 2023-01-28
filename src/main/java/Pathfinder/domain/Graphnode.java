package Pathfinder.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Yksittäistä verkon solmua edustava luokka.
 * @author lasse
 */
public class Graphnode {
    
    private List<Graphnode> neighbors;
    private int x;
    private int y;
    private boolean passable;
    private int distance;
    private int HDistance;

    /**
     * Luo uuden verkon solmun. Tarvitsee koordinaatit.
     * @param y
     * @param x 
     */
    public Graphnode(int y, int x, boolean passable) {
        this.neighbors = new ArrayList<>();
        this.distance = -1;
        this.HDistance = -1;
        this.x = x;
        this.y = y;
        this.passable = passable;
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

    public int getDistance() {
        return distance;
    }

    public int getHDistance() {
        return HDistance;
    }
    
    public List<Graphnode> getNeighbors() {
        return neighbors;
    }

    public boolean isPassable() {
        return passable;
    }

    
}
