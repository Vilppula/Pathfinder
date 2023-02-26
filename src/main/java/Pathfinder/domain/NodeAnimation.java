package Pathfinder.domain;

import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Luokka havainnollistaa algoritmine etenemist‰ animaation avulla.
 * @author lasse
 */
public class NodeAnimation {

    private Button button;
    private List<Graphnode> nodes;
    private int frame;
    private int height, width;
    private Color color;
    private Timeline animation;
    private WritableImage animationView;
    private int frames;
    private double blue, red;
    
    /**
     * Luo uuden yht‰ algoritmia vastaavan animaation.
     * @param nodes
     * @param height
     * @param width
     * @param color 
     */
    public NodeAnimation(Button button, List<Graphnode> nodes, int height, int width, Color color) {
        this.button = button;
        this.nodes = nodes;
        this.frames = nodes.size()-1;
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
     * Aloitetaan animaatio, jossa piirret‰‰n solmut pikselein‰ niiden 
     * k‰sittelyj‰rjestyksess‰. Fringe Searchin tapauksessa piirret‰‰n 
     * kullakin iteraatiolla k‰sitelty solmujoukko.
     */
    public void animate() {

        blue = 0; red = 255;
        PixelWriter writer = animationView.getPixelWriter();
        EventHandler<ActionEvent> handler = e -> {
            if (frame < nodes.size()) {
                Graphnode node = nodes.get(frame);
                writer.setColor(node.getX(), node.getY(), color);
                frame++;
            }
        };
        
        EventHandler<ActionEvent> handler2 = e -> {
            if (nodes.get(frame) == null) {
                frame++;
            }
            while (nodes.get(frame) != null) {
                Graphnode node = nodes.get(frame);
                writer.setColor(node.getX(), node.getY(), Color.rgb(50, (int) red,(int) blue));
                frame++;
            }
            blue = (blue >= 255 ? 0 : blue+.5);
            red = (red == 0 ? 255: red-.5);
        };
        
        animation = new Timeline(new KeyFrame(Duration.millis(
                (nodes.get(0) == null ? 1 : .1)),(nodes.get(0) != null ? handler : handler2)));
        animation.setCycleCount(countCycles());
        animation.setOnFinished(event -> {
            button.setStyle("-fx-background-color:lightgreen");
        });
        animation.play();
    }

    /**
     * Laske tarvittavien syklien m‰‰r‰ solmulistan pituuden perusteella.
     * @return 
     */
    public int countCycles() {
        if (nodes.get(0) == null) {
            return (int) nodes.stream().filter(node -> node == null).count()-1;
        } else {
            return nodes.size();
        }
    }
    
    /**
     * Palauttaa luodun Timeline instanssin
     * @return 
     */
    public Timeline getAnimation() {
        return animation;
    }
}
