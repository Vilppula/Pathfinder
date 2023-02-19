package Pathfinder.algorithms;

import Pathfinder.domain.Graphnode;
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
public class FringeSearchTest extends DijkstraTest {
    
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
    public void setUp() {
    }
    
    @Test
    public void algoritmiLoytaaLyhyimmanReitin() {
        builder.loadMap(testMap);
        solver.fringeSearch();
        solver.addA(0, 0);
        solver.addB(5, 12);
        solver.solve();
        int[][] route = new int[testMap.length][testMap[0].length];
        System.arraycopy(testMap, 0, route, 0, 6);
        
        Graphnode g = builder.getGraphnode(5, 12);
        while (g != null) {
            route[g.getY()][g.getX()] = 2;
            g = g.getPrevious();
        }
        for (int i=0;i < route.length; i++) {
            for (int j = 0; j < route[0].length; j++) {
                System.out.print((route[i][j] == 0 ? " " : (route[i][j] == 1 ? "#" : "s")));
            }
            System.out.println("");
        }
    }
}
