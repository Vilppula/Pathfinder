package Pathfinder.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Luokka piirt‰‰ diagrammeja
 * @author lasse
 */
public class ChartDraw {
    
    /**
     * Metodi luo listan diagrammeja Observerin tietojen perusteella.
     * @param observer
     * @return 
     */
    public List<Chart> draw(Observer observer) {
        List<Chart> charts = new ArrayList<>();
        Map<String, List<Integer>> listMap = new HashMap<>();
        Map<String, Number> singleMap = new HashMap<>();
        Map<Object, String> instances = observer.getRunInstances();

        // Jonon koon vaihtelua ilmaiseva diagrammi
        instances.keySet().forEach(c -> listMap.put(instances.get(c), observer.getQSizes(c)));
        charts.add(drawLineChart(listMap));
        // Jonon keskim‰‰r‰ist‰ kokoa ilmaiseva diagrammi
        instances.keySet().forEach(c -> singleMap.put(instances.get(c), observer.getAverageQSize(c)));
        charts.add(drawBarChart(singleMap));
        // Tarkastelujen m‰‰r‰‰ ilmaiseva diagrammi
        instances.keySet().forEach(c -> listMap.put(instances.get(c), observer.getExpansionsList(c)));
        charts.add(drawLineChart(listMap));
        // Suurin tarkastelujen m‰‰r‰
        instances.keySet().forEach(c -> singleMap.put(instances.get(c), observer.getMaxExpansions(c)));
        charts.add(drawBarChart(singleMap));
        return charts;
    }
    
    /**
     * Metodi piirt‰‰ viivadiagrammin
     * @param map
     * @return 
     */
    public LineChart<Number, Number> drawLineChart(Map<String, List<Integer>> map) {
        //Axis[] axises = getAxises(subject);
        //final LineChart chart = new LineChart<Number, Number>(axises[0], axises[1]);
        Axis xAxis = new NumberAxis();
        xAxis.setTickLabelsVisible(false);
        final LineChart chart = new LineChart<Number, Number>(xAxis, new NumberAxis());
        for (String s: map.keySet()) {
            XYChart.Series series = new XYChart.Series();
            series.setName(s);
            List<Integer> list = map.get(s);
            for (int i = 0, ii = 0; i < list.size(); i += list.size()/ 450, ii++) {
                series.getData().add(createData(ii, list.get(i)));
            }
            chart.getData().add(series);
        }
        chart.setCreateSymbols(false);
        chart.setVisible(false);
        return chart;
    }
    
    /**
     * Metodi luo pylv‰sdiagrammin
     * @param map
     * @return 
     */
    public BarChart<String, Number> drawBarChart(Map<String, Number> map) {
        //Axis[] axises = getAxises(subject);
        Axis xAxis = new CategoryAxis();
        final BarChart<String, Number> chart = new BarChart<String, Number>(xAxis, new NumberAxis());
        XYChart.Series series = new XYChart.Series();
        for (String s: map.keySet()) {
            series.getData().add(new XYChart.Data(s, map.get(s)));
        }
        chart.getData().add(series);
        chart.setVisible(false);
        chart.setLegendVisible(false);
        return chart;
    }
    
    /**
     * Luo dataobjektin diagrammia varten.
     * @param i1
     * @param i2
     * @return 
     */
    public XYChart.Data<Number, Number> createData(int i1, int i2) {
        XYChart.Data<Number, Number> data = new XYChart.Data(i1, i2);
        return data;
    }
}
