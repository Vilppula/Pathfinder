package Pathfinder.utility;

import Pathfinder.algorithms.Solver;
import Pathfinder.controllers.MainviewController;
import Pathfinder.domain.Graphnode;
import Pathfinder.domain.NodeAnimation;
import java.util.List;
import javafx.animation.Animation.Status;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Tämä luokka vastaa algoritmin polkuja tai etenemistä kuvaavan 
 * graafisen esityksen luomisesta.
 * @author lasse
 */
public class Visualizer {

    private MainviewController controller;
    private int width, height;
    private NodeAnimation[] animations;
    private ImageView[] dotImages;
    private Button[] buttons;
    
    /**
     * Konstruktori saa parametrikseen viitteen päänäkymän kontrollerista
     * @param controller 
     */
    public Visualizer(MainviewController controller) {
        this.controller = controller;
        this.dotImages = new ImageView[] {
            controller.dotImage,
            controller.dotImage2,
            controller.dotImage3
        };
        this.buttons = new Button[] {
            controller.dijkstra,
            controller.aStar,
            controller.fringeSearch
        };
        this.animations = new NodeAnimation[3]; 
    }
    
    /**
     * Päivitetään piirrettävän kuvan koko vastaamaan käyttöliittymään ladatun
     * karttapohjan kokoa.
     * @param image 
     */
    public void setImageSize(Image image) {
        this.height = (int) image.getHeight();
        this.width = (int) image.getWidth();
    }
    
    /**
     * Piirretään reitti joka vastaa algoritmien löytämää optimaalista
     * reittiä.
     * @param bx
     * @param by 
     */
    public void drawRoute(int by, int bx) {
        WritableImage image = new WritableImage(width, height);
        PixelWriter writer = image.getPixelWriter();
        Graphnode node = controller.graph.getGraphnode(by, bx);
        while (node.getPrevious() != null) {
            writer.setColor(node.getX(), node.getY(), Color.RED);
            writer.setColor(node.getX()-1, node.getY(), Color.RED);
            writer.setColor(node.getX()+1, node.getY(), Color.RED);
            writer.setColor(node.getX(), node.getY()-1, Color.RED);
            writer.setColor(node.getX(), node.getY()+1, Color.RED);
            node = node.getPrevious();
        }
        controller.routeImage.setImage(image);
    }
    
    /**
     * Luo graafisen esityksen algoritmien etenemisestä. Toteutetaan tausta-
     * animaatioina Timeline-luokan avulla.
     */
    public void visualize(Solver solver) {
        if (controller.dijkstra.getStyleClass().contains("toggledButton")
                && visualizeAlgorithm(solver.getDijkstraNodes(), 0)) {
            animations[0].animate();
        }
        if (controller.aStar.getStyleClass().contains("toggledButton")
                && visualizeAlgorithm(solver.getAStarNodes(), 1)) {
            animations[1].animate();
        }
        if (controller.fringeSearch.getStyleClass().contains("toggledButton")
                && visualizeAlgorithm(solver.getFringeSearchNodes(), 2)) {
            animations[2].animate();
        }
    }
    
    /**
     * Yksittäisen algoritmin esityksen luominen (num: 0 = Dijkstra, 1 = A* 2 = Fringe search)
     * @param nodes
     * @param num
     * @return 
     */
    private boolean visualizeAlgorithm(List<Graphnode> nodes, int num) {
        if (this.animations[num] != null) {
            clearVisualization(num);
        }
        Color c = (num == 0 ? Color.BLACK : (num == 1 ? Color.CORAL : Color.BLACK));
        this.animations[num] = new NodeAnimation(buttons[num], nodes, height, width, c);
        this.dotImages[num].setImage(animations[num].getAnimationView());
        return true;
    }
    
    /**
     * Poistaa edellisen visualisoinnin
     * @param algorithmNumber 
     */
    public void clearVisualization(int algorithmNumber) {
        if (this.dotImages[algorithmNumber].getImage() == null) {
            return;
        }
        this.animations[algorithmNumber].getAnimation().stop();
        this.dotImages[algorithmNumber].setImage(null);
        this.animations[algorithmNumber] = null;
        buttons[algorithmNumber].setStyle("-fx-background-color:#999999");
    }
}
