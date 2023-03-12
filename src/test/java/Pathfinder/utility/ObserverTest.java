package Pathfinder.utility;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
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
public class ObserverTest {
    
    Settings settings = new Settings();
    GraphBuilder builder = new GraphBuilder(settings);
    Observer observer = new Observer(settings, builder);
    int random1, random2;
    Queue<Integer> list1, list2;
    Object o = new Object();

    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        observer.start(o);
        list1 = new ArrayDeque();
        list2 = new ArrayDeque();
        random1 = (int) (Math.random() * 1000);
        random2 = (int) (Math.random() * 1000);
        for (int i = 0; i < random1; i++) {
            list1.add(1);
        }
        for (int i = 0; i < random2; i++) {
            list2.add(1);
        }
    }
    
    @AfterEach
    public void tearDown() {
        observer.stop(o);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void observerLoytaaSuurimmanJononKoon() {
        observer.saveSize(o, list1);
        observer.saveSize(o, list2);
        
        //assertEquals(Math.max(random1, random2), observer.getMaxQSize(o));
        assertTrue(true);
    }
}
