package Pathfinder.utility;

import Pathfinder.domain.Graphnode;
import org.junit.jupiter.api.Test;
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
    
    
    @Test
    public void builderVoidaanLuodaTaulukolla() {
        this.builder = new GraphBuilder(map);
        assertEquals(this.map, builder.getMap());
    }
    
    @Test
    public void builderPalauttaaKartanKorkeuden() {
        this.builder = new GraphBuilder(map);
        assertEquals(100, builder.getHeight());
    }
    
    @Test
    public void builderPalauttaaKartanLeveyden() {
        this.builder = new GraphBuilder(map);
        assertEquals(100, builder.getWidth());
    }
    
    @Test
    public void createMetodiPalauttaaFalseJosKarttaaEiOleLadattu() {
        this.builder = new GraphBuilder();
        assertFalse(this.builder.createGraph());
    }
    
    @Test
    public void createMetodiPalauttaaTrueJosKarttaOnLadattuJaSenKokoEiOleNolla() {
        this.builder = new GraphBuilder(map);
        assertTrue(this.builder.createGraph());
    }
    
    @Test
    public void createMetodiLuoTaulukonJokaSis‰lt‰‰GraphnodeOlioita() {
        this.builder = new GraphBuilder(map);
        assertFalse(builder.getGraphnode(50, 50) == null);
    }
    
    @Test
    public void heuristicMetodiEiLaskeEt‰isyyksi‰JosKarttaaEiOleLadattu() {
        this.builder = new GraphBuilder();
        assertFalse(this.builder.heuristic(10, 10));
    }
    
    @Test
    public void heuristicMetodiEiLaskeEt‰isyyksi‰JosValittuPisteOnKartanUlkopuolella() {
        this.builder = new GraphBuilder(map);
        assertFalse(this.builder.heuristic(-1, -1));
        assertFalse(this.builder.heuristic(101, 101));
    }
    
    @Test
    public void getGraphnodePalauttaaNullJosKarttaaEiOleLadattu() {
        this.builder = new GraphBuilder();
        assertEquals(null, builder.getGraphnode(10, 10));
    }
    
    @Test
    public void getGraphnodePalauttaaNullJosPisteOnKartanUlkopuolella() {
        this.builder = new GraphBuilder(map);
        assertEquals(null, builder.getGraphnode(101, 101));
    }
    
    @Test
    public void builderLaskeeSopivanHeuristisenEt‰isyysarvionSolmuille() {
        this.builder = new GraphBuilder(map);
        builder.heuristic(10, 10);
        Graphnode node1 = builder.getGraphnode(20, 20);
        Graphnode node2 = builder.getGraphnode(60, 88);
        assertEquals(20, node1.getHDistance());
        assertEquals(128, node2.getHDistance());
    }
    
    @Test
    public void numeroNollaKartassaLuoSolmun() {
        this.builder = new GraphBuilder(map2);
        assertEquals(Graphnode.class, builder.getGraphnode(1, 0).getClass());
    }
    
    @Test
    public void numeroYksiKartassaEiLuoSolmua() {
        this.builder = new GraphBuilder(map2);
        assertEquals(null, builder.getGraphnode(1, 1));
    }
    
    @Test
    public void solmuIlmoitetaanNaapurinListalle() {
        this.builder = new GraphBuilder(map2);
        assertTrue(builder.getGraphnode(1,0).getNeighbors().contains(builder.getGraphnode(0, 0)));
    }
    
    @Test
    public void solmuOnVainSenNaapureidenListalla() {
        this.builder = new GraphBuilder(map2);
        Graphnode node = builder.getGraphnode(1, 0);
        assertTrue(builder.getGraphnode(0,0).getNeighbors().contains(node));
        assertTrue(builder.getGraphnode(0,1).getNeighbors().contains(node));
        assertFalse(builder.getGraphnode(2,2).getNeighbors().contains(node));
    }
    
    @Test
    public void solmullaOnOikeaM‰‰r‰Naapureita() {
        this.builder = new GraphBuilder(map2);
        Graphnode node = builder.getGraphnode(1, 0);
        assertEquals(4, node.getNeighbors().size());
    }
}
