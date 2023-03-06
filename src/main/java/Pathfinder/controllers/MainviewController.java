package Pathfinder.controllers;

import Pathfinder.Main;
import Pathfinder.algorithms.Calculable;
import Pathfinder.algorithms.Solver;
import Pathfinder.utility.Visualizer;
import Pathfinder.utility.GraphBuilder;
import Pathfinder.utility.MapHandler;
import Pathfinder.utility.Observer;
import Pathfinder.utility.Settings;
import Pathfinder.utility.Settings.Heuristic;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * P‰‰n‰kym‰n kontrolleriluokka.
 * @author lasse
 */
public class MainviewController implements Initializable {

    public GraphBuilder graph;
    private Settings settings;
    private Visualizer visualizer;
    private Observer observer;
    
    private ImageCursor cursorA = new ImageCursor(new Image("/from.png"), 24, 44);
    private ImageCursor cursorB = new ImageCursor(new Image("/to.png"), 24, 44);
    private ImageCursor cursorHand = new ImageCursor(new Image("/hand.png"), 290, 350);
    private ImageCursor cursor;
    private ImageView aLocation;
    private ImageView bLocation;
    private MapHandler mapHandler;
    private Solver solver;
    
    @FXML public Button dijkstra, aStar, fringeSearch, bawButton, animateButton;
    @FXML Pane mainArea;
    @FXML public ImageView mapImage, bawImage, routeImage, dotImage, dotImage2, dotImage3, heuristicImage;
    @FXML Pane map, stickerLayer;
    @FXML Label coordinates, colorLabel;
    @FXML Canvas canvas;
    
    private Button toggleButton;
    
    //==========================================================================
    /**
     * Lis‰t‰‰n tarvittavat apuluokat.
     * @param graph
     * @param settings 
     */
    public void loadUtil(GraphBuilder graph, Settings settings) {
        this.graph = graph;
        this.settings = settings;
        
    }
    
    public void openStatistics() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/statistics.fxml"));
        StatisticsController statC = new StatisticsController(observer);
        loader.setController(statC);
        Scene scene;
        try {
            scene = new Scene(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(MainviewController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        Stage second = new Stage();
        second.setScene(scene);
        second.show();
        for (Calculable c: solver.getAlgorithms()) {
            statC.addRow(c);
        }
    }
    
    /**
     * Dijkstra p‰‰lle/pois
     */
    @FXML
    public void dijkstra() {
        toggleButton = dijkstra;
        solver.dijkstra();
        changeToggle();
        visualizer.clearVisualization(0);
    }
    
    /**
     * A* p‰‰lle/pois
     */
    @FXML
    public void aStar() {
        toggleButton = aStar;
        solver.aStar();
        changeToggle();
        visualizer.clearVisualization(1);
    }
    
    /**
     * Fringe Search p‰‰lle/pois
     */
    @FXML
    public void fringeSearch() {
        toggleButton = fringeSearch;
        solver.fringeSearch();
        changeToggle();
        visualizer.clearVisualization(2);
    }
    
    /**
     * Mustavalkokuva p‰‰lle/pois
     */
    @FXML
    public void changeBaw() {
        mapImage.setVisible(!mapImage.isVisible());
        bawImage.setVisible(!bawImage.isVisible());
        toggleButton = bawButton;
        changeToggle();
    }
    
    /**
     * Animaatio p‰‰lle/pois
     */
    @FXML
    public void changeAnimated() {
        settings.setAnimated(!settings.isAnimated());
        toggleButton = animateButton;
        changeToggle();
    }
    
    /**
     * Vaihtaa eri heuristiikkojen v‰lill‰.
     */
    public void changeHeuristic() {
        this.settings.changeHeuristic();
        if (settings.getHeuristic() == Heuristic.EUCLIDEAN) {
            heuristicImage.setImage(new Image("/euclidean.png"));
        }
        else if (settings.getHeuristic() == Heuristic.MANHATTAN) {
            heuristicImage.setImage(new Image("/manhattan.png"));
        }
    }   
    
    /**
     * Algoritmien valintapainikkeet: p‰‰ll‰/ pois p‰‰lt‰
     */
    public void changeToggle() {
        if (toggleButton.getStyleClass().contains("toggledButton")) {
            toggleButton.getStyleClass().remove("toggledButton");
        } else {
            toggleButton.getStyleClass().add("toggledButton");
        }
    }
    
    /**
     * Solve painikketta painettaessa kutsutaan t‰t‰ metodia.
     * Pyyt‰‰ apuluokkia ratkaisemaan ja tarvittaessa piirt‰m‰‰n reitin.
     */
    @FXML
    public void solve() {
        solver.solve();
        if (settings.isAnimated()) {
            visualizer.visualize(solver);       //Visualisoidaan algoritmien eteneminen
        }
        this.visualizer.drawRoute(solver.getBy(), solver.getBx());  //Piirret‰‰n reitti
    }
    
    
    /**
     * Sijoittaa l‰htˆ- tai maali-kuvakkeen
     * @param y
     * @param x 
     */
    @FXML
    public void mapClick(double y, double x) {
        if (!graph.isPassable(y, x)) {
            return;
        }
        if (cursor == cursorA) {
            if (aLocation == null) {
                aLocation = new ImageView(new Image("/from.png"));
                stickerLayer.getChildren().add(aLocation);
            }
            aLocation.setTranslateY(y-44);
            aLocation.setTranslateX(x-24);
            solver.addA((int) y, (int) x);
        } else {
            if (bLocation == null) {
                bLocation = new ImageView(new Image("/to.png"));
                stickerLayer.getChildren().add(bLocation);
            }
            bLocation.setTranslateY(y-44);
            bLocation.setTranslateX(x-24);
            solver.addB((int) y, (int) x);
        }
        changeAB();
        map.setCursor(cursor);
    }
    
    /**
     * Vaihtaa kursorin kuvaa A (punainen) <-> B (vihre‰).
     */
    public void changeAB() {
        if (cursor == cursorA) {
            cursor = cursorB;
        } else {
            cursor = cursorA;
        }
    }
    
    /**
     * Vaihtaa annetun kuvaobjektin uudeksi karttapohjaksi.
     * @param image 
     */
    public void changeMap(Image image) {
        mapHandler.loadImage(image);
        visualizer.setImageSize(image);
        graph.loadMap(mapHandler.getMap());
        this.mapImage.setImage(image);
        this.bawImage.setImage(mapHandler.getBlackAndWhite());
    }
    
    /**
     * Alustetaan n‰kym‰. Luodaan apuluokat. Asetetaan tapahtumank‰sittelij‰t.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settings = new Settings();                          //Luo asetukset
        graph = new GraphBuilder(settings);                 //Luo verkon rakentaja
        observer = new Observer(settings, graph);                  //Luo uusi statistiikka
        solver = new Solver(graph, settings, observer);     //Luo reitinratkaisija
        visualizer = new Visualizer(this);                  //Luo piirt‰j‰
        mapHandler = new MapHandler();
        changeMap(new Image("/testMap.jpg"));
        cursor = cursorA;
        map.setCursor(cursor);
        mapImage.setImage(mapHandler.getOriginal());
        bawImage.setImage(mapHandler.getBlackAndWhite());
        mapImage.setVisible(true);
        bawImage.setVisible(false);
        map.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                coordinates.setText(""+t.getX()+", "+t.getY());
            }
        });
        map.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                coordinates.setText("0.0, 0.0");
            }
        });
        map.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                map.setCursor(cursorHand);
            }
        });
        map.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.isStillSincePress()) {
                    mapClick(t.getY(), t.getX());
                } else {
                    map.setCursor(cursor);
                }
            }
        });
    }
}
