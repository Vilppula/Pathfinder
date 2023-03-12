package Pathfinder.algorithms;

import Pathfinder.domain.Graphnode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author lasse
 */
public class FringeSearchTest extends DijkstraTest {
    
    Calculable algorithm = new FringeSearch(builder, solver);
    int[][] testMap = {
        {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,1,0,0,1,1,0,1,0,1,1,1,1,1,0},
        {0,0,1,1,1,0,1,1,0,1,0,0,0,0,1},
        {1,0,1,0,1,0,1,1,0,0,1,0,1,0,1},
        {0,1,1,0,1,0,0,0,1,0,0,1,0,1,0},
        {0,1,0,1,0,1,1,1,1,0,1,1,0,1,0},
        {0,0,1,0,1,0,0,0,0,0,0,0,0,0,1} 
    };
    
    @BeforeEach
    public void setUp(){
        observer.start(algorithm);
    }
    
    @AfterEach
    public void tearDown() {
        observer.stop(algorithm);
    }
}
