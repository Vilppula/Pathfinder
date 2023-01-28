package Pathfinder.algorithms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lasse
 */
public class DijkstraTest {
    
    private Dijkstra dijkstra;
    
    public DijkstraTest() {
        this.dijkstra = new Dijkstra();
    }
    
    @Test
    public void someTest() {
        String name = "nimi";
        assertEquals(name, dijkstra.name(name));
    }
}
