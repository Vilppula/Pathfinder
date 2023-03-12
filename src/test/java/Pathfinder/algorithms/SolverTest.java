/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pathfinder.algorithms;

import Pathfinder.domain.Graphnode;
import Pathfinder.utility.GraphBuilder;
import Pathfinder.utility.Observer;
import Pathfinder.utility.Settings;
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
public class SolverTest {
    
    Solver solver;
    GraphBuilder builder;
    Settings settings;
    int[][] map = new int[100][100];
    int[][] map2 = {
        {0,1,0,1,0,1,0,1,0,1,0,1},
        {0,1,0,1,0,1,0,1,0,1,0,1},
        {0,1,0,1,0,1,0,1,0,1,0,1},
        {0,1,0,1,0,1,0,1,0,1,0,1},
        {1,0,1,0,1,0,1,0,1,0,1,0}
    };
    public SolverTest() {
        settings = new Settings();
        builder  = new GraphBuilder(settings);
    }
    
    
    @BeforeEach
    public void setUp() {
        solver = new Solver(builder, settings, new Observer(settings, builder));
        builder.loadMap(map);
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void solvePalauttaaFalseJosYhtaanAlgoritmiaEiOleValittu() {
        assertFalse(solver.solve());
    }
    
    @Test
    public void solvePalauttaaFalseJosLahtoJaMaalipistettaEiOleValittu() {
        solver.dijkstra();
        assertFalse(solver.solve());
        solver.addA(10, 10);
        assertFalse(solver.solve());
    }
    
    @Test
    public void solvePalauttaaTrueJosSekaMaaliEttaLahtopisteOnValittu() {
        solver.dijkstra();
        validPoints();
        assertTrue(solver.solve());
    }
    
    @Test
    public void ensimmaisenaValittuVirheellinenPisteJaSolvePalauttaaFalse() {
        solver.dijkstra();
        solver.addA(-1, 101);
        solver.addB(50, 50);
        assertFalse(solver.solve());
    }
    
    @Test
    public void josPisteValitaanVaarinJaaVanhaPisteVoimaan() {
        solver.dijkstra();
        validPoints();
        solver.addA(-1, 10);
        assertTrue(solver.solve());
    }
    
    @Test
    public void dijkstraVoidaanKytkeaPaalleJaPois() {
        solver.dijkstra();
        validPoints();
        assertTrue(solver.solve());
        solver.dijkstra();
        assertFalse(solver.solve());
    }
    
    @Test
    public void aStarVoidaanKytkeaPaalleJaPois() {
        solver.aStar();
        validPoints();
        assertTrue(solver.solve());
        solver.aStar();
        assertFalse(solver.solve());
    }
    
    @Test
    public void algoritmienSuorittaminenTallettaaNiitaKuvaavatListat() {
        solver.dijkstra();
        solver.aStar();
        validPoints();
        solver.solve();
        assertFalse(solver.getDijkstraNodes().isEmpty());
        assertFalse(solver.getAStarNodes().isEmpty());
    }
    
    @Test
    public void kaikkiAlgoritmitLoytavatLyhyimmanReitin() {
        builder.loadMap(map2);
        solver.addA(0, 0);
        solver.addB(builder.getHeight()-1, builder.getWidth()-1);
        solver.dijkstra();
        solver.solve();
        int pathNodes = countRouteNodes(builder.getHeight()-1, builder.getWidth()-1);
        builder.reset();
        solver.dijkstra();
        solver.aStar();
        solver.solve();
        assertEquals(pathNodes, countRouteNodes(builder.getHeight()-1, builder.getWidth()-1));
    }
    
//    @Test
//    public void fringeSearchVoidaanKytkeaPaalleJaPois() {
//        solver.fringeSearch();
//        validPoints();
//        assertTrue(solver.solve());
//        solver.fringeSearch();
//        assertFalse(solver.solve());
//    }
    
    public int countRouteNodes(int by, int bx) {
        Graphnode node = builder.getGraphnode(by, bx);
        int nodes = 0;
        while(node.getPrevious() != null) {
            nodes++;
            node = node.getPrevious();
        }
        return nodes;
    }
    
    public void validPoints() {
        solver.addA(10, 10);
        solver.addB(50, 50);
    }
    
}
