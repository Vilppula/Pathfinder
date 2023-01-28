package Pathfinder.utility;

import Pathfinder.domain.Graphnode;

/**
 * Luo graafin käyttöliittymään ladatun kuvan perusteella. Dijkstran ja A*:n käytössä.
 * @author lasse
 */
public class GraphBuilder {
    
    private int[][] map;
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
        if (!validateMap()) {
            this.map = map;
            this.height = map.length;
            this.width = map[0].length;
            this.nodemap = new Graphnode[height][width];
        }
    }
    
    /**
     * Luodaan kaksiulotteinen kartta solmuista kun karttapohja ladataan käyttöliittymään.
     * @return 
     */
    public boolean create() {
        if (!validateMap()) {
            return false;
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                nodemap[i][j] = new Graphnode(i,j,
                        (map[i][j] == 0 ? true: false)      //Luo joko kuljettava tai läpäisemätön solmu (0 = kuljettava)
                );
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
        if (!validateMap() || !validatePoint(by, bx)) {
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
     * Tarkista onko ladattu karttataulukko kelvollinen.
     * @return 
     */
    public boolean validateMap() {
        if (map != null && map.length > 0 && map[0].length > 0) {
            return true;
        }
        return false;
    }
    
    /**
     * Tarkista onko jokin piste kartan sisä- vai ulkopuolella
     * @param y
     * @param x
     * @return 
     */
    public boolean validatePoint(int y, int x) {
        if (y < 0 || x < 0 || y > height-1 || x > width-1) {
            return false;
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
    
    public Graphnode getGraphnode(int y, int x) {
        if (!validateMap() || !validatePoint(y, x)) {
            return null;
        }
        
        return nodemap[y][x];
    }
}
