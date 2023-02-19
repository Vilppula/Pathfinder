package Pathfinder.utility;

import Pathfinder.domain.Graphnode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * GraphBuilder luokan testit
 * @author lasse
 */
public class GraphBuilderTest {
    
    GraphBuilder builder;
    int[][] map = new int[100][100];
    int[][] map2 = {
        {0, 0, 0},
        {0, 1, 0},
        {0, 0, 0}
    };
    
    @BeforeEach
    public void setup() {
        this.builder = new GraphBuilder(new Settings(), map);
    }
    
    @Test
    public void builderVoidaanLuodaTaulukolla() {
        assertEquals(this.map, builder.getMap());
    }
    
    @Test
    public void builderPalauttaaKartanKorkeuden() {
        assertEquals(100, builder.getHeight());
    }
    
    @Test
    public void builderPalauttaaKartanLeveyden() {
        assertEquals(100, builder.getWidth());
    }
    
    @Test
    public void createMetodiPalauttaaFalseJosKarttaaEiOleLadattu() {
        builder.eraseMap();
        assertFalse(this.builder.createGraph());
    }
    
    @Test
    public void createMetodiPalauttaaTrueJosKarttaOnLadattuJaSenKokoEiOleNolla() {
        assertTrue(this.builder.createGraph());
    }
    
    @Test
    public void createMetodiLuoTaulukonJokaSisaltaaGraphnodeOlioita() {
        assertFalse(builder.getGraphnode(50, 50) == null);
    }
    
    @Test
    public void karttaVoidaanPoistaa() {
        assertFalse(builder.getMap() == null);
        builder.eraseMap();
        assertTrue(builder.getMap() == null);
    }
    
    @Test
    public void heuristicMetodiEiLaskeEtaisyyksiaJosKarttaaEiOleLadattu() {
        builder.eraseMap();
        assertFalse(this.builder.heuristic(10, 10));
    }
    
    @Test
    public void heuristicMetodiEiLaskeEtaisyyksiaJosValittuPisteOnKartanUlkopuolella() {
        assertFalse(this.builder.heuristic(-1, -1));
        assertFalse(this.builder.heuristic(101, 101));
    }
    
    @Test
    public void getGraphnodePalauttaaNullJosKarttaaEiOleLadattu() {
        builder.eraseMap();
        assertEquals(null, builder.getGraphnode(10, 10));
    }
    
    @Test
    public void getGraphnodePalauttaaNullJosPisteOnKartanUlkopuolella() {
        assertEquals(null, builder.getGraphnode(101, 101));
    }
    
    @Test
    public void builderLaskeeSopivanHeuristisenEtaisyysarvionSolmuille() {
        assertTrue(builder.heuristic(10, 10));
        Graphnode node1 = builder.getGraphnode(20, 20);
        Graphnode node2 = builder.getGraphnode(60, 88);
        assertEquals((int)(Math.sqrt(10*10+10*10)*100), node1.getHDistance());
        assertEquals((int)(Math.sqrt(50*50+78*78)*100), node2.getHDistance());
    }
    
    @Test
    public void numeroNollaKartassaLuoSolmun() {
        builder.loadMap(map2);
        assertEquals(Graphnode.class, builder.getGraphnode(1, 0).getClass());
    }
    
    @Test
    public void numeroYksiKartassaEiLuoSolmua() {
        builder.loadMap(map2);
        assertEquals(null, builder.getGraphnode(1, 1));
    }
    
    @Test
    public void solmuIlmoitetaanNaapurinListalle() {
        builder.loadMap(map2);
        assertTrue(builder.getGraphnode(1,0).getNeighbors().contains(builder.getGraphnode(0, 0)));
    }
    
    @Test
    public void solmuOnVainSenNaapureidenListalla() {
        builder.loadMap(map2);
        Graphnode node = builder.getGraphnode(1, 0);
        assertTrue(builder.getGraphnode(0,0).getNeighbors().contains(node));
        assertTrue(builder.getGraphnode(0,1).getNeighbors().contains(node));
        assertFalse(builder.getGraphnode(2,2).getNeighbors().contains(node));
    }
    
    @Test
    public void solmullaOnOikeaMaaraNaapureita() {
        builder.loadMap(map2);
        Graphnode node = builder.getGraphnode(1, 0);
        assertEquals(4, node.getNeighbors().size());
    }
    
    @Test
    public void josKarttaaEiOleResetointiEiToimi() {
        builder = new GraphBuilder(new Settings());
        assertFalse(builder.reset());
    }
    
    @Test
    public void graafinResetoiminenPalauttaaSolmujenEtaisyydenMaksimiin() {
        builder.getGraphnode(50, 50).setDistance(1000);
        builder.getGraphnode(80, 77).setDistance(1000);
        builder.reset();
        assertEquals(Settings.maxInt, builder.getGraphnode(50, 50).getDistance());
        assertEquals(Settings.maxInt, builder.getGraphnode(80, 77).getDistance());
    }
    
    @Test
    public void metodiIsPassablePalauttaaOikeanArvon() {
        builder.loadMap(map2);
        assertFalse(builder.isPassable(1, 1));
        assertTrue(builder.isPassable(0, 1));
    }
}
