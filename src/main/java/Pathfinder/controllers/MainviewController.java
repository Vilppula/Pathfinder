package Pathfinder.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author lasse
 */
public class MainviewController implements Initializable {

    @FXML Button dijkstra, aStar, fringeSearch;
    @FXML Pane mainArea;
    
    private Button toggleButton;
    
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
    
    public void changeToggle() {
         if (toggleButton.getStyleClass().contains("toggledTopbutton")) {
           toggleButton.getStyleClass().remove("toggledTopbutton");
        } else toggleButton.getStyleClass().add("toggledTopbutton");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       mainArea.setCursor(new ImageCursor(new Image("/A.png")));
    }
    
}
