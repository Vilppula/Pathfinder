/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pathfinder.algorithms;

import Pathfinder.utility.GraphBuilder;
import Pathfinder.utility.Settings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testiluokka A*:lle. Perii testit DijkstraTest-luokasta.
 * @author lasse
 */
public class AStarTest extends DijkstraTest {
    
    GraphBuilder builder = new GraphBuilder(new Settings());
    Calculable algorithm = new AStar(builder);
    
    
}
