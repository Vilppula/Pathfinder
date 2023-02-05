package Pathfinder.controllers;

import Pathfinder.utility.GraphBuilder;
import Pathfinder.utility.MapHandler;
import Pathfinder.utility.Settings;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * P‰‰n‰kym‰n kontrolleriluokka.
 * @author lasse
 */
public class MainviewController implements Initializable {

    private GraphBuilder graph;
    private Settings settings;
    
    private ImageCursor cursorA = new ImageCursor(new Image("/from.png"), 24, 44);
    private ImageCursor cursorB = new ImageCursor(new Image("/to.png"), 24, 44);
    private ImageCursor cursorHand = new ImageCursor(new Image("/hand.png"), 290, 350);
    private ImageCursor cursor;
    private ImageView aLocation;
    private ImageView bLocation;
    private MapHandler mapHandler; 
    
    @FXML Button dijkstra, aStar, fringeSearch;
    @FXML Pane mainArea;
    @FXML ImageView mapImage, bawImage;
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
    
    @FXML
    public void dijkstra() {
        toggleButton = dijkstra;
        changeToggle();
    }
    
    @FXML
    public void aStar() {
        toggleButton = aStar;
        changeToggle();
    }
    
    @FXML
    public void fringeSearch() {
        toggleButton = fringeSearch;
        changeToggle();
    }
    
    /**
     * Sijoittaa l‰htˆ- tai maali-kuvakkeen
     * @param y
     * @param x 
     */
    @FXML
    public void mapClick(double y, double x) {
        if (cursor == cursorA) {
            if (aLocation == null) {
                aLocation = new ImageView(new Image("/from.png"));
                stickerLayer.getChildren().add(aLocation);
            }
            aLocation.setTranslateY(y-44);
            aLocation.setTranslateX(x-24);
        } else {
            if (bLocation == null) {
                bLocation = new ImageView(new Image("/to.png"));
                stickerLayer.getChildren().add(bLocation);
            }
            bLocation.setTranslateY(y-44);
            bLocation.setTranslateX(x-24);
        }
        changeAB();
        map.setCursor(cursor);
    }
    
   
    /**
     * Algoritmien valintapainikkeet: p‰‰ll‰/ pois p‰‰lt‰
     */
    public void changeToggle() {
        if (toggleButton.getStyleClass().contains("toggledTopbutton")) {
            toggleButton.getStyleClass().remove("toggledTopbutton");
        } else {
            toggleButton.getStyleClass().add("toggledTopbutton");
        }
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
     * Vaihda karttan‰kym‰‰ alkuper‰inen <-> mustavalkoinen
     */
    public void swapColorBaw() {
        if (this.mapImage.isVisible()) {
            this.mapImage.setVisible(false);
            this.bawImage.setVisible(true);
        } else {
            this.mapImage.setVisible(true);
            this.bawImage.setVisible(false);
        }
    }
    
    /**
     * Vaihtaa annetun kuvaobjektin uudeksi karttapohjaksi.
     * @param image 
     */
    public void changeMap(Image image) {
        mapHandler.loadImage(image);
        graph.loadMap(mapHandler.getMapArray());
        this.mapImage.setImage(image);
        this.bawImage.setImage(mapHandler.getBlackAndWhite());
    }
    
    /**
     * Alustetaan n‰kym‰. Asetetaan tapahtumank‰sittelij‰t.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cursor = cursorA;
        map.setCursor(cursor);
        mapHandler = new MapHandler(new Image("/testMap.jpg"));
        mapImage.setImage(mapHandler.getOriginal());
        bawImage.setImage(mapHandler.getBlackAndWhite());
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
