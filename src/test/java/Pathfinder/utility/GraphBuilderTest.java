package Pathfinder.utility;

import Pathfinder.domain.Graphnode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * GraphBuilder luokan testit
 * @author lasse
 */
public class GraphBuilderTest {
    
    private GraphBuilder builder;
    private int[][] map;
    
    public GraphBuilderTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        map = new int[100][100];
    }
    
    @AfterEach
    public void tearDown() {
    }

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
        assertFalse(this.builder.create());
    }
    
    @Test
    public void createMetodiPalauttaaTrueJosKarttaOnLadattuJaSenKokoEiOleNolla() {
        this.builder = new GraphBuilder(map);
        assertTrue(this.builder.create());
    }
    
    @Test
    public void createMetodiLuoTaulukonJokaSis‰lt‰‰GraphnodeOlioita() {
        this.builder = new GraphBuilder(map);
        builder.create();
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
    public void builderLaskeeSopivanHeuristisenEt‰isyysarvionSolmuille() {
        this.builder = new GraphBuilder(map);
        builder.create();
        builder.heuristic(10, 10);
        Graphnode node1 = builder.getGraphnode(20, 20);
        Graphnode node2 = builder.getGraphnode(60, 88);
        assertEquals(20, node1.getHDistance());
        assertEquals(128, node2.getHDistance());
    }
}
