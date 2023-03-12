package Pathfinder.utility;

import Pathfinder.domain.Graphnode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Luokka sis‰lt‰‰ suoritusta mittaavia metodeja.
 * @author lasse
 */
public class Observer {
    
    private Map<Class, List<Integer>> qSizes = new HashMap<>();                 //Algoritmikohtainen lista johon talletetaan tilanvarauksia
    private Map<Class, Long[]> durations = new HashMap<>();                     //--""-- talletetaan ajankulutus. Aloitus ja lopetus.
    private Map<Class, Integer> stepCounters = new HashMap<>();                 //Laskuri jolla voidaan tauottaa algoritmien tekemi‰ metodikutsuja t‰ss‰ luokassa
    private Map<Class, Set<Graphnode>> seenNodes = new HashMap<>();
    private Map<Class, List<Graphnode>> expandedNodes = new HashMap<>();
    private Map<Class, Integer> maxExpansions = new HashMap<>();
    private Map<Class, Integer> totalVisits = new HashMap<>();
    private Map<Class, Integer> visitedNodes = new HashMap<>();
    private Map<Object, String> runInstances = new HashMap<>();
    private Map<Class, List<Integer>> expansions = new HashMap<>();
    private Map<Class, List<Integer>> visits = new HashMap<>();
    private Settings settings;
    private GraphBuilder builder;
    
    
    public Observer(Settings settings, GraphBuilder builder) {
        this.settings = settings;
        this.builder = builder;
    }
    
    //========================================================================== Start ja Stop
    /**
     * Kutsutaan kun olioon o liittyv‰ laskenta alkaa. Kaikki luokkaan liittyv‰t
     * tietorakenteet alustetaan.
     */
    public void start(Object o) {
        System.out.println("K‰ynnistet‰‰n "+o.getClass());
        stepCounters.put(o.getClass(),0);
        qSizes.put(o.getClass(), new ArrayList<>());
        expandedNodes.put(o.getClass(), new ArrayList());
        runInstances.put(o, o.getClass().getName());
        expansions.put(o.getClass(), new ArrayList<>());
        visits.put(o.getClass(), new ArrayList<>());
        durations.put(o.getClass(), new Long[]{System.nanoTime(),(long) 0});
        maxExpansions.put(o.getClass(), 0);
    }
    
    /**
     * Kutsutaan kun olioon o liittyv‰n laskenta on p‰‰ttynyt
     * @param o 
     */
    public void stop(Object o) {
        durations.get(o.getClass())[1] = System.nanoTime();
        List<Graphnode> seenNodes = new ArrayList<>();
        for (int i = 0; i < builder.getHeight(); i++) {
            for (int j = 0; j < builder.getWidth(); j++) {
                Graphnode node = builder.getGraphnode(i, j);
                if (node != null && node.getVisits() > 0) {
                    seenNodes.add(node);
                }
            }
        }
        
        for (Graphnode node: expandedNodes.get(o.getClass())) {
            if (node == null) {
                continue;
            }
            maxExpansions.put(o.getClass(), Math.max(node.getExpansions(), maxExpansions.getOrDefault(o.getClass(), 0)));
        }
                
        totalVisits.put(o.getClass(), seenNodes.stream()
                .map(n -> n.getVisits())
                .reduce(0, (v1, v2) -> v1 + v2)
                .intValue());
        visitedNodes.put(o.getClass(), seenNodes.size());
        
    }
    
    public void clear(Object o) {
        if (runInstances.containsKey(o)) {
            runInstances.remove(o);
        }
    }
    
    //========================================================================== Suorituksen aikainen raportionti
    /**
     * Tallettaa luokkaan liittyv‰n jonon koon eri aikoina. Avaimena toimii luokka (c), eli esim.
     * Dijkstran algoritmin prioriteettijonon koko talletetaan hajautustaulun listaan,
     * jonka avaimena toimii Dijkstran algoritmin toteuttava luokka 'Dijkstra'
     * Tallennetaan myˆs parametrina annettuun objektiin liittyvien tarkisteltujen
     * solmujen m‰‰r‰n kyseisell‰ ajanhetkell‰.
     * @param o
     * @return 
     */
    public void saveSize(Object c, Queue q) {
        if (cycle(c.getClass())) {
            qSizes.get(c.getClass()).add(q.size());
            expansions.get(c.getClass()).add(expandedNodes.get(c.getClass()).size());
        }
    }
    
    /**
     * Kuten ylemp‰n‰, mutta kahden eri jonon yhteenlaskettu muistivaraus.
     * @param c
     * @param o1
     * @param o2 
     */
    public void saveSize(Object c, Queue q1, Queue q2) {
        if (cycle(c.getClass())) {
            int total = q1.size() + q2.size();
            qSizes.get(c.getClass()).add(total);
            expansions.get(c.getClass()).add(expandedNodes.get(c.getClass()).size()); //Tallentaa t‰h‰n objektiin liityvien tarkisteltujen solmujen m‰‰r‰ t‰ll‰ ajan hetkell‰
        }
    }

    /**
     * Lis‰‰ ilmoitettu solmu k‰siteltyjen listalle
     * @param c
     * @param node 
     */
    public void addAsExpanded(Object c, Graphnode node) {
        this.expandedNodes.get(c.getClass()).add(node);
    }
    
    /**
     * Kasvatetaan luokka-kohtaista laskuria yhdell‰. Jos Settings-luokan m‰‰rittelem‰
     * yl‰raja saavutetaan, annetaan lupa suorittaa muita metodeita ja nollataan laskuri.
     * @param c
     * @return 
     */
    public boolean cycle(Class c) {
        int i = stepCounters.get(c);
        i += 1;
        stepCounters.put(c, i+1);
        if (i < settings.cycleSteps) {
            return false;
        }
        i = 0;
        return true;
    }
    
    //========================================================================== Statistiikka kyselyt
    /**
     * Palauttaa yhteen luokkaan liittyv‰t laksennan aikana tallennetut
     * jonon koot. Voidaan esitt‰‰ kaaviona
     * @param o
     * @return 
     */
    public List<Integer> getQSizes(Object o) {
        return qSizes.get(o.getClass());
    }
    
    /**
     * Palauttaa suurimman luokkaan liittyv‰n jonon koon
     * @param o
     * @return 
     */
    public int getMaxQSize(Object o) {
        return qSizes.get(o.getClass()).stream()
                .max(Integer::compare).get();
    }
    
    /**
     * Palauttaa jonon kokojen keskiarvon laskennan ajalta
     * @param o
     * @return 
     */
    public double getAverageQSize(Object o) {
        long total = qSizes.get(o.getClass()).stream()
                .reduce(0, (q1, q2) -> q1 + q2)
                .intValue();
        return total / qSizes.get(o.getClass()).size();
    }
    
    /**
     * Pyydet‰‰n statistiikalta olioon liittyv‰ suoritusaika.
     * @param o
     * @return 
     */
    public Double getDuration(Object o) {
        if (!durations.containsKey(o.getClass())) {
            return null;
        }
        Long[] startStop = durations.get(o.getClass());
        return (double) (startStop[1] - startStop[0])/1000000;
    }
    
    /**
     * Pyydet‰‰n statistiikalta luokkaan liittyv‰ luku joka kuvaa
     * k‰siteltyjen solmujen m‰‰r‰‰.
     * @param o
     * @return 
     */
    public int getTotalExpansions(Object o) {
        return expandedNodes.get(o.getClass()).size();
    }
    
    /**
     * Palauttaa kaikkien solmujen yhteenlasketun vierailujen m‰‰r‰n.
     * @param o
     * @return 
     */
    public int getTotalVisits(Object o) {
        return totalVisits.get(o.getClass());
    }
    
    /**
     * Keskim‰‰r‰inen vierailum‰‰r‰ yhdell‰ solmulla
     * @param o
     * @return 
     */
    public double getAverageVisits(Object o) {
        return (double) getTotalVisits(o) / visitedNodes.get(o.getClass());
    }
    
    
    /**
     * Palauttaa suurimman tarkastelukertojen m‰‰r‰n yhdell‰ solmulla
     * @param o
     * @return 
     */
    public int getMaxExpansions(Object o) {
        return maxExpansions.get(o.getClass());
    }
    
    /**
     * Palauttaa algoritmin tarkastelemien solmujen listan
     * @param o
     * @return 
     */
    public List<Graphnode> getExpandedNodes(Object o) {
        return expandedNodes.get(o.getClass());
    }

    /**
     * Palauttaa listan start-metodissa suoritetuista instansseista
     * @return 
     */
    public Map<Object, String> getRunInstances() {
        return runInstances;
    }

    /**
     * Palauttaa objektiin liittyv‰n listan, jolla on tarkasteltujen solmujen
     * m‰‰r‰ eri ajanhetkill‰.
     * @param o
     * @return 
     */
    public List<Integer> getExpansionsList(Object o) {
        return expansions.get(o.getClass());
    }
    
    
    
}
