/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pathfinder.algorithms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * Testiluokka A*:lle. Perii testit DijkstraTest-luokasta.
 * @author lasse
 */
public class AStarTest extends DijkstraTest {
   
    Calculable algorithm = new AStar(builder, solver);

    @BeforeEach
    public void setUp() {
        observer.start(algorithm);
    }
    
//    @Test
//    public void algoritmiEiMuutaSolmunEtaisyyttaHuonommaksi() {
//        algorithm.calculate(0, 0, 2, 6);
//        assertFalse(algorithm.adjust(builder.getGraphnode(1, 0), 110));
//    }
//    
//    @Test
//    public void algoritmiParantaaSolmunEtaisyytta() {
//        algorithm.calculate(0, 0, 2, 6);
//        assertTrue(algorithm.adjust(builder.getGraphnode(1, 0), 90));
//    }
    
    @AfterEach
    public void tearDown() {
        observer.stop(algorithm);
    }
}
