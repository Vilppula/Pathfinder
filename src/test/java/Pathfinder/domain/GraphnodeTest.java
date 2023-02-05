package Pathfinder.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testiluokka verkon solmua edustavalle luokalle 'Graphnode'
 * @author lasse
 */
public class GraphnodeTest {
    
    private Graphnode node;
    
    public GraphnodeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        node = new Graphnode(1,1);
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void uusiNodeVoidaanLuoda() {
        assertEquals(Graphnode.class, node.getClass());
    }

    @Test
    public void uusiNaapuriVoidaanLisataSolmuun() {
        Graphnode neighbor = new Graphnode(0, 1);
        this.node.addNeighbor(neighbor);
        assertTrue(this.node.getNeighbors().contains(neighbor));
    }
    
    @Test
    public void solmunAloitusEtaisyysOnMahdollisimmanSuuri() {
        assertTrue(this.node.getDistance() == Integer.MAX_VALUE);
        assertTrue(this.node.getHDistance() == Integer.MAX_VALUE);
    }
    
    @Test
    public void solmulleVoidaanLaskeaUusiEtaisyys() {
        assertTrue(this.node.getDistance() == Integer.MAX_VALUE);
        this.node.setDistance(10);
        assertEquals(10, this.node.getDistance());
    }
    
    @Test
    public void solmulleVoidaanAntaaEtaisyysArvio() {
        assertTrue(this.node.getHDistance() == Integer.MAX_VALUE);
        this.node.setHDistance(10);
        assertEquals(10, this.node.getHDistance());
    }
}
