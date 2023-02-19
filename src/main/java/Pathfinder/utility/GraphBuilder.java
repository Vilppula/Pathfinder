package Pathfinder.utility;

import Pathfinder.domain.Graphnode;
import Pathfinder.utility.Settings.Heuristic;
import java.util.List;

/**
 * Luo graafin k�ytt�liittym��n ladatun kuvan perusteella. Dijkstran ja A*:n k�yt�ss�.
 * @author lasse
 */
public class GraphBuilder {
    
    private Settings settings;
    private int[][] map = null;
    private Graphnode[][] nodemap;
    private int height = 0;
    private int width = 0;
    
    
    /**
     * Luodaan GraphBuilder ilman karttapohjaa.
     */
    public GraphBuilder(Settings settings) {
        this.settings = settings;
    }
    
    /**
     * Luodaan GraphBuilder karttapohjan kanssa. Laskee korkeuden ja leveyden ja
     * varaa tilan tarvittavalle m��r�lle 'Graphnode'-olioita.
     * @param map 
     */
    public GraphBuilder(Settings settings, int[][] map) {
        this.settings = settings;
        if (validateMap(map)) {
            loadMap(map);
        }
    }
    
    /**
     * Lataa uuden kartan muuttujaan 'map'. Luodaan
     * sit� vastaava verkko.
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
     * Poista nykyinen kartta
     */
    public void eraseMap() {
        this.map = null;
        this.nodemap = null;
    }
    
    /**
     * Luodaan kaksiulotteinen kartta solmuista kun karttapohja ladataan k�ytt�liittym��n.
     * Lis�t��n kukin solmu sen naapurien listalle.
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
                            map[i][j] == 0 ? new Graphnode(i,j): null           //Luo solmu jos kartan piste on 0 (valkoinen pikseli)
                        );
                    } else {
                        announceNode(i, j);                                     //Ilmoita solmu naapureiden listalle
                    }
                }
            }
        }
        return true;
    }

    /**
     * Asetetaan kullekin solmulle heuristinen arvio et�isyydest� valittuun
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
                if (nodemap[i][j] == null) {
                    continue;
                }
                if (settings.getHeuristic() == Heuristic.MANHATTAN) {
                    nodemap[i][j].setHDistance((Math.abs(i-by)+Math.abs(j-bx))*100);        //Manhattan-et�isyys
                } 
                else if (settings.getHeuristic() == Heuristic.EUCLIDEAN) {
                    nodemap[i][j].setHDistance(
                        (int) (Math.sqrt(
                            ((i-by)*(i-by) + (j-bx)*(j-bx))                     //Euklidinen et�isyys (py�ristys kokonaisluvuksi)
                        )*100)               
                    );
                }
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
     * Tarkista onko jokin piste kartan sis�- vai ulkopuolella, sek� onko
     * valitussa pisteess� olemassa solmu.
     * @param y
     * @param x
     * @return 
     */
    public boolean validatePoint(int y, int x) {
        if (y < 0 || x < 0 || y >= height || x >= width
                || map == null || nodemap[y][x] == null) {
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
                        || !validatePoint(y + i, x + j)) {       //Solmun lis��misen ehdot
                    continue;
                }
                nodemap[y+i][x+j].addNeighbor(nodemap[y][x]);
            }
        }
    }
    
    /**
     * Asettaa kartan solmujen et�isyydeksi suurimmaksi mahdolliseksi kokonaisluvun.
     */
    public boolean reset() {
        if (this.map == null) {
            return false;
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Graphnode node = nodemap[i][j];
                if (node != null) {
                    node.reset();
                }
            }
        }
        return true;
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

    public Settings getSettings() {
        return settings;
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
    
    /**
     * Tarkasta onko taulukon kohdassa (x,y) kuljettava solmu.
     * @param x
     * @param y
     * @return 
     */
    public boolean isPassable(double y, double x) {
        return map[(int) y][(int) x] == 0;
    }
}
