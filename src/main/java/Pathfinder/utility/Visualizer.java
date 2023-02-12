package Pathfinder.utility;

import Pathfinder.algorithms.Solver;
import Pathfinder.controllers.MainviewController;
import Pathfinder.domain.Graphnode;
import Pathfinder.domain.NodeAnimation;
import java.util.List;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * T�m� luokka vastaa algoritmin polkuja tai etenemist� vastaavan 
 * graafisen esityksen luomisesta.
 * @author lasse
 */
public class Visualizer {

    private MainviewController controller;
    private int width, height;
    private NodeAnimation[] animations;
    private ImageView[] dotImages;
    
    /**
     * Konstruktori saa parametreinaan verkon ja viitteen kuvan�kym�st� johon
     * piirretty reittikuva sijoitetaan
     * @param controller 
     */
    public Visualizer(MainviewController controller) {
        this.controller = controller;
        this.dotImages = new ImageView[] {
            controller.dotImage,
            controller.dotImage2,
            controller.dotImage3
        };
        this.animations = new NodeAnimation[3]; 
    }
    
    /**
     * P�ivitet��n piirrett�v�n kuvan koko vastaamaan k�ytt�liittym��n ladatun
     * karttapohjan kokoa.
     * @param image 
     */
    public void setImageSize(Image image) {
        this.height = (int) image.getHeight();
        this.width = (int) image.getWidth();
    }
    
    /**
     * Piirret��n reitti joka vastaa algoritmien l�yt�m�� optimaalista
     * reitti�.
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
     * Luo graafisen esityksen algoritmien etenemisest�. Toteutetaan tausta-
     * animaatioina Timeline-luokan avulla.
     */
    public void visualize(Solver solver) {
        if (controller.dijkstra.getStyleClass().contains("toggledTopbutton")
                && visualizeDijkstra(solver.getDijkstraNodes())) {
            animations[0].animate();
        }
        if (controller.aStar.getStyleClass().contains("toggledTopbutton")
                && visualizeAStar(solver.getAStarNodes())) {
            animations[1].animate();
        }
    }
    
    /**
     * Luo animaation Dijkstran algoritmin etenemisest�
     * @param nodes
     * @return 
     */
    public boolean visualizeDijkstra(List<Graphnode> nodes) {
        if (this.animations[0] != null && this.animations[0].getAnimation().getStatus() == Status.RUNNING) {
            return false;
        }
        this.animations[0] = new NodeAnimation(nodes, height, width, Color.BLACK);
        this.dotImages[0].setImage(animations[0].getAnimationView());
        return true;
    }
    
    /**
     * Luo animaation A* algoritmine etenemisest�
     * @param nodes
     * @return 
     */
    public boolean visualizeAStar(List<Graphnode> nodes) {
        if (this.animations[1] != null && this.animations[1].getAnimation().getStatus() == Status.RUNNING) {
            return false;
        }
        this.animations[1] = new NodeAnimation(nodes, height, width, Color.CORAL);
        this.dotImages[1].setImage(animations[1].getAnimationView());
        return true;
    }
}
