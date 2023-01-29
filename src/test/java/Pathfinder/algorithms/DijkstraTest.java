package Pathfinder.algorithms;

import Pathfinder.utility.GraphBuilder;
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
    
    Dijkstra dijkstra;
    GraphBuilder builder;
    
    @BeforeAll
    public void setUp() {
        builder = new GraphBuilder();
        dijkstra = new Dijkstra(builder);
    }
    
}
