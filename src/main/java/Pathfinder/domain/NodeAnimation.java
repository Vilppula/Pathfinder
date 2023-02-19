
package Pathfinder.domain;

import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Luokka havainnollistaa algoritmine etenemist‰ animaation avulla.
 * @author lasse
 */
public class NodeAnimation {

    private List<Graphnode> nodes;
    private int frame;
    private int height, width;
    private Color color;
    private Timeline animation;
    private WritableImage animationView;
    
    /**
     * Luo uuden yht‰ algoritmia vastaavan animaation.
     * @param nodes
     * @param height
     * @param width
     * @param color 
     */
    public NodeAnimation(List<Graphnode> nodes, int height, int width, Color color) {
        System.out.println("Luodaan uusi animaatio v‰rill‰ "+color+" listan pituudelle "+nodes.size());
        this.nodes = nodes;
        this.frame = 0;
        this.height = height;
        this.width = width;
        this.color = color;
        this.animationView = new WritableImage(width, height);
    }

    public WritableImage getAnimationView() {
        return animationView;
    }
    
    /**
     * Aloitetaan animaatio, jossa piirret‰‰n solmut siin‰ j‰rjestyksess‰,
     * jossa algoritmi ne k‰sittelee.
     */
    public void animate() {
        PixelWriter writer = animationView.getPixelWriter();
        EventHandler<ActionEvent> handler = e -> {
            if (frame < nodes.size()) {
                Graphnode node = nodes.get(frame);
                writer.setColor(node.getX(), node.getY(), color);
                frame++;
            }
        };
        animation = new Timeline(new KeyFrame(Duration.millis(0.1), handler));
        animation.setCycleCount(nodes.size());
        animation.play();
    }

    public Timeline getAnimation() {
        return animation;
    }
}
