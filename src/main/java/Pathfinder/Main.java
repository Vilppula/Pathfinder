package Pathfinder;

import Pathfinder.controllers.MainviewController;
import Pathfinder.utility.GraphBuilder;
import Pathfinder.utility.Settings;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Pathfinder applikaation käynnistävä luokka.
 * @author lasse
 */
public class Main extends Application {

    private static Scene scene;
    private static GraphBuilder graph;
    private static Settings settings;
    private static MainviewController mainView;

    @Override
    public void start(Stage stage) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/FXML/Mainview.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        mainView = fxmlLoader.getController();
        //mainView.loadUtil(graph, settings);
    }

    

    public static void main(String[] args) {
        launch();
    }
}
