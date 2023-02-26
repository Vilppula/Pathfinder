package Pathfinder.utility;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Luokka sis‰lt‰‰ suoritusta mittaavia metodeja.
 * @author lasse
 */
public class Observer {
    
    private static Instrumentation instrum;
    private Map<Class, List<Long>> objectSizes = new HashMap<>();
    private Map<Class, Long> durations = new HashMap<>();
    
    /**
     * K‰ynnist‰‰ instrumentit ennen main-metodin kutsumista.
     * @param args
     * @param inst 
     */
    public static void premain(String args, Instrumentation inst) {
        instrum = inst;
    }
    
    /**
     * Tallettaa hetkellisen muistivarauksen. Avaimena toimii luokka (c), eli esim.
     * Dijkstran algoritmin prioriteettijonon (o) muistivaraus talletetaan hajautustaulun listaan,
     * jonka avaimena toimii Dijkstran algoritmin toteuttava luokka 'Dijkstra'
     * @param o
     * @return 
     */
    public void saveSize(Object c, Object o) {
        List<Long> cList = objectSizes.getOrDefault(c.getClass(), new ArrayList<>());
        cList.add(instrum.getObjectSize(o));
    }
    
    /**
     * Kuten ylemp‰n‰, mutta kahden eri instanssin yhteenlaskettu muistivaraus.
     * @param c
     * @param o1
     * @param o2 
     */
    public void saveSize(Object c, Object o1, Object o2) {
        List<Long> cList = objectSizes.getOrDefault(c.getClass(), new ArrayList<>());
        cList.add(instrum.getObjectSize(o1) + instrum.getObjectSize(o2));
    }

    /**
     * Pyydet‰‰n statistiikkaa kirjaamaan olioon liittyv‰ aloitusaika.
     */
    public void start(Object o) {
        durations.put(o.getClass(), System.currentTimeMillis());
    }
    
    /**
     * Pyydet‰‰n statistiikkaa kirjaamaan olioon liittyv‰ lopetusaika
     * @param o 
     */
    public void stop(Object o) {
        durations.put(o.getClass(), System.currentTimeMillis() - durations.get(o.getClass()));
    }
    
    
    //========================================================================== Getterit
    /**
     * Palauttaa yhteen olioon liittyv‰t muistivaraukset listana.
     * @param o
     * @return 
     */
    public List<Long> getSizes(Object o) {
        return objectSizes.get(o.getClass());
    }
    
    /**
     * Pyydet‰‰n statistiikalta olioon liittyv‰ suoritusaika.
     * @param o
     * @return 
     */
    public Long getDuration(Object o) {
        return durations.get(o.getClass());
    }
}
