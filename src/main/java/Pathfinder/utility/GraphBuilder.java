package Pathfinder.utility;

import Pathfinder.domain.Graphnode;

/**
 * Luo graafin käyttöliittymään ladatun kuvan perusteella. Dijkstran ja A*:n käytössä.
 * @author lasse
 */
public class GraphBuilder {
    
    private int[][] map = null;
    private Graphnode[][] nodemap;
    private int height;
    private int width;

    /**
     * Luodaan GraphBuilder ilman karttapohjaa.
     */
    public GraphBuilder() {
    }
    
    /**
     * Luodaan GraphBuilder karttapohjan kanssa. Laskee korkeuden ja leveyden ja
     * varaa tilan tarvittavalle määrälle 'Graphnode'-olioita.
     * @param map 
     */
    public GraphBuilder(int[][] map) {
        if (validateMap(map)) {
            loadMap(map);
        }
    }
    
    /**
     * Lataa uuden kartan muuttujaan 'map'. Luodaan
     * sitä vastaava verkko.
     * @param map
     * @return 
     */
    public void loadMap(int[][] map) {
        this.map = map;
        this.height = map.length;
        this.width = map[0].length;
        this.nodemap = new Graphnode[height][width];
        createGraph();
    }
    
    /**
     * Luodaan kaksiulotteinen kartta solmuista kun karttapohja ladataan käyttöliittymään.
     * Lisätään kukin solmu sen naapurien listalle.
     * @return 
     */
    public boolean createGraph() {
        if (this.map == null) {
            return false;
        }
        for (int r = 0; r < 2; r++) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (r == 0) {
                        nodemap[i][j] = (
                                map[i][j] == 0 ? new Graphnode(i,j): null       //Luo solmu jos kartan piste on 0 (valkoinen pikseli)
                        );
                    }
                    else {
                        announceNode(i, j);                                     //Ilmoita solmu naapureiden listalle
                    }
                }
            }
        }
        return true;
    }

    /**
     * Asetetaan kullekin solmulle heuristinen arvio etäisyydestä valittuun
     * maalisolmuun (bx,by)
     * @param ax
     * @param ay
     * @param bx
     * @param by
     * @return 
     */
    public boolean heuristic(int by, int bx) {
        if (map == null || !validatePoint(by, bx)) {
            return false;
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                nodemap[i][j].setHDistance(Math.abs((i-by)+(j-bx)));    //Manhattan-etäisyys
            }
        }
        return true;
    }
    
    /**
     * Tarkista onko ladattava karttataulukko kelvollinen.
     * @return 
     */
    public boolean validateMap(int[][] map) {
        if (map != null && map.length > 0 && map[0].length > 0) {
            return true;
        }
        return false;
    }
    
    /**
     * Tarkista onko jokin piste kartan sisä- vai ulkopuolella, sekä onko
     * valitussa pisteessä olemassa solmu.
     * @param y
     * @param x
     * @return 
     */
    public boolean validatePoint(int y, int x) {
        if (y < 0 || x < 0 || y > height-1 || x > width-1
                || nodemap[y][x] == null) {
            return false;
        }
        return true;
    }

    /**
     * Ilmoita koordinaateissa oleva solmu sen naapureiden listalle.
     * @param y
     * @param x 
     */
    public void announceNode(int y, int x) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (nodemap[y][x] == null || (i == 0 && j == 0) 
                        || !validatePoint(y + i, x + j)) {       //Solmun lisäämisen ehdot
                    continue;
                }
                nodemap[y+i][x+j].addNeighbor(nodemap[y][x]);
            }
        }
    }
    //========================================================================== Get/set
    
    public int[][] getMap() {
        return map;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    /**
     * Palauttaa koordinaatteja vastaavan solmun.
     * @param y
     * @param x
     * @return 
     */
    public Graphnode getGraphnode(int y, int x) {
        if (map == null || !validatePoint(y, x)) {
            return null;
        }
        return nodemap[y][x];
    }
}
