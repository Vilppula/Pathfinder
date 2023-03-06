package Pathfinder.controllers;

import Pathfinder.algorithms.AStar;
import Pathfinder.algorithms.Calculable;
import Pathfinder.algorithms.FringeSearch;
import Pathfinder.utility.ChartDraw;
import Pathfinder.utility.Observer;
import Pathfinder.utility.Settings;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.Chart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * Kontrolleriluokka statistiikkaa esitt‰v‰lle n‰kym‰lle
 * @author lasse
 */
public class StatisticsController implements Initializable  {

    @FXML Label chartLabel;
    @FXML GridPane table;
    @FXML StackPane chartView;
    @FXML ToggleButton queueSize, avgQueue, expansions, avgExpansions;
    
    private Observer observer;
    private ChartDraw chartDraw;
    private ToggleButton chosenButton;
    private Chart chosenChart;
    private Map<ToggleButton, Chart> buttonMapping;
    private List<Chart> charts;
    private List<ToggleButton> buttons; 
    
    public StatisticsController(Observer observer) {
        this.observer = observer;
        chartDraw = new ChartDraw();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        charts = chartDraw.draw(observer);
        chartView.getChildren().addAll(charts);
        buttons = new ArrayList<>(Arrays.asList(
                queueSize, avgQueue, expansions, avgExpansions));
        String[] labels = new String[] {
            "Time", "Max. Queue", "Avg. Queue", "Expansions", "Max. Exp", "Visits", "Avg. Visits"
        };
        Node[] nodes = new Node[8];
        int place = 1;
        nodes[0] = new Label("Algorithm");
        for (String str: labels) {
            Label label = new Label(str);
            nodes[place] = getCell(label, Pos.CENTER);
            place++;
        }
        table.addRow(table.getRowCount(), nodes);
        for (ToggleButton button: buttons) {
            button.setOnAction(e -> {
                switchButton(buttons.indexOf(button));
            }); 
        }
    }
    
    /**
     * Lis‰t‰‰n uusi rivi taulukkoon (gridpane)
     * @param observer 
     */
    public void addRow(Calculable c){
        Node[] nodes = new Node[]{
            getCell(new Label((c instanceof AStar ? "A*" : (c instanceof FringeSearch ? "FringeSearch" : "Dijkstra"))), Pos.CENTER),
            getCell(new Label(String.format("%.2f", observer.getDuration(c))), Pos.CENTER),
            getCell(new Label("" + observer.getMaxQSize(c)), Pos.CENTER),
            getCell(new Label(String.format("%.2f", observer.getAverageQSize(c)).toString()), Pos.CENTER),
            getCell(new Label("" + observer.getTotalExpansions(c)), Pos.CENTER),
            getCell(new Label("" + observer.getMaxExpansions(c)), Pos.CENTER),
            getCell(new Label("" + observer.getTotalVisits(c)), Pos.CENTER),
            getCell(new Label(String.format("%.2f", observer.getAverageVisits(c))), Pos.CENTER)
        };
        table.addRow(table.getRowCount(), nodes);
    }
    
    
    public HBox getCell(Node node, Pos pos) {
        HBox box = new HBox();
        box.getChildren().add(node);
        box.setPrefHeight(40);
        box.setPrefWidth(100);
        box.setAlignment(pos);
        return box;
    }
    
    /**
     * Vaihtaa n‰kym‰‰ eri diagrammien v‰lill‰ kun vastaavia painikkeita
     * painetaan.
     * @param button 
     */
    public void switchButton(int i) {
        if (chosenButton != null) {
            chosenButton.setSelected(false);
        }
        if (chosenChart != null) {
            chosenChart.setVisible(false);
        }
        chosenChart = charts.get(i);
        chosenChart.setVisible(true);
        System.out.println("ASetetaan diagrammi "+chosenChart+" n‰kyv‰ksi");
        chosenButton = buttons.get(i);
    }
}
