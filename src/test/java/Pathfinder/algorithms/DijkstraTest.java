package Pathfinder.algorithms;

import Pathfinder.domain.Graphnode;
import Pathfinder.utility.GraphBuilder;
import Pathfinder.utility.Settings;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testiluokka luokkaan 'Dijkstra' laaditun Dijkstran algoritmin testaamiseen
 * @author lasse
 */
public class DijkstraTest {
    
    Settings settings = new Settings();
    GraphBuilder builder = new GraphBuilder(settings);
    Solver solver = new Solver(builder, settings);
    Calculable algorithm = new Dijkstra(builder, solver);
    int[][] map = new int[][]{
            {0,0,0,0,0,0,1,0},
            {0,0,1,0,0,1,1,1},
            {0,1,1,0,1,1,0,0},
            {0,0,1,0,0,0,0,0},
            };
    
    @BeforeEach
    public void reset() {
        builder.loadMap(map);
    }
    
    @Test
    public void dijkstraPalauttaaFalseJosMaalisolmunKoordinaatitKartanUlkopuolella() {
        assertFalse(algorithm.calculate(0, 0, 10, 10));
    }
    
    @Test
    public void dijkstraPalauttaaTrueKunLoytaaMaalisolmun() {
        assertTrue(algorithm.calculate(0, 0, 2, 6));
    }
    
    @Test
    public void dijkstraPalauttaaFalseJosMaalisolmuaEiLoydy() {
        assertFalse(algorithm.calculate(0, 0, 0, 7));
    }
    
    @Test
    public void etaisyysViereisiinSolmuihinOnOikein() {
        assertTrue(algorithm.calculate(1, 1, 2, 6));
        assertEquals(141, builder.getGraphnode(0, 0).getDistance());
        assertEquals(100, builder.getGraphnode(1, 0).getDistance());
        assertEquals(141, builder.getGraphnode(2, 0).getDistance());
        assertEquals(100, builder.getGraphnode(0, 1).getDistance());
        assertEquals(141, builder.getGraphnode(0, 2).getDistance());
    }
    
    @Test
    public void maalisolmunEtaisyysLasketaanOikein() {
        assertEquals(Settings.maxInt, builder.getGraphnode(2, 6).getDistance());
        algorithm.calculate(0, 0, 2, 6);
        assertEquals(823, builder.getGraphnode(2, 6).getDistance());
    }
    
    @Test
    public void algoritmiEiLaskeEnempaaEtaisyyksiaKunMaalisolmunOnLoydetty() {
        assertEquals(Settings.maxInt, builder.getGraphnode(2, 7).getDistance());
        algorithm.calculate(0, 0, 2, 6);
        assertEquals(Settings.maxInt, builder.getGraphnode(2, 7).getDistance());
    }
    
    @Test
    public void algoritmiMerkitseeEdeltajasolmut() {
        algorithm.calculate(0, 0, 2, 6);
        Graphnode node = builder.getGraphnode(2, 6);
        String route = "(" + node.getY() + "," + node.getX() + ")";;
        do {
            node = node.getPrevious();
            route += "(" + node.getY() + "," + node.getX() + ")";
        } while (node.getDistance() != 0);
        assertEquals("(2,6)(3,5)(3,4)(2,3)(1,3)(0,2)(0,1)(0,0)", route);
    }
    
    @Test
    public void algoritmiEiMuutaSolmunEtaisyyttaHuonommaksi() {
        algorithm.calculate(0, 0, 2, 6);
        assertFalse(algorithm.adjust(builder.getGraphnode(1, 0), 110));
    }
    
    @Test
    public void algoritmiParantaaSolmunEtaisyytta() {
        algorithm.calculate(0, 0, 2, 6);
        assertTrue(algorithm.adjust(builder.getGraphnode(1, 0), 90));
    }
}
