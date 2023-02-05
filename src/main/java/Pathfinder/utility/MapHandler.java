package Pathfinder.utility;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * T‰m‰ luokka lukee kuvatiedostoja ja muuntaa ne taulukoiksi, joita
 * GraphBuilder k‰ytt‰‰ verkon muodostamiseen.
 * @author lasse
 */
public class MapHandler {
    
    private Image orig;
    private PixelReader reader;
    private WritableImage baw;
    private PixelWriter bawWriter;
    private Image blackAndWhite;
    private int height;
    private int width;
    private int[][] map;
    
    public MapHandler(Image image) {
        loadImage(image);
    }
    
    /**
     * Korvaa nykyisen karttakuvan uudella kuvalla ja luo mustavalkokuvan.
     * @param image
     * @return 
     */
    public boolean loadImage(Image image) {
        this.orig = image;
        this.reader = image.getPixelReader();
        this.height = (int) orig.getHeight();
        this.width = (int) orig.getWidth();
        this.baw = new WritableImage(width, height);
        this.bawWriter = baw.getPixelWriter();
        createBlackAndWhite();
        return true;
    }
    
    /**
     * Muodostaa mustavalkokuvaa vastaavan taulukon. Toimii verkon luomisessa
     * GraphBuilder-instanssissa.
     * @return 
     */
    public int[][] pixelsToArray() {
        return null;
    }
    
    /**
     * Muodostaa ladatusta karttakuvasta mustavalkokuvan.
     * @return 
     */
    public boolean createBlackAndWhite() {
        map = new int[height][width];
        for (int i = 0; i < orig.getHeight(); i++) {
            for (int j = 0; j < orig.getWidth(); j++) {
                Color c = blackOrWhitePixel(j, i);
                bawWriter.setColor(j, i, c);
                map[i][j] = (c == Color.BLACK ? 1 : 0);
            }
        }
        this.blackAndWhite = (Image) baw;
        return true;
    }
    
    /**
     * Valitun pikselin arviointi joko mustaksi tai valkoiseksi.
     * @param y
     * @param x
     * @return 
     */
    public Color blackOrWhitePixel(int x, int y) {
        int pixel = reader.getArgb(x, y);
        int red = ((pixel >> 16) & 0xff);
        int green = ((pixel >> 8) & 0xff);
        int blue = (pixel & 0xff);
        int grayLevel = (int) (0.68*(double)red + 0.9*(double)green + (double)blue) / 3;
        grayLevel = 255 - grayLevel;
        int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
        return (gray > 4000000 ? Color.BLACK : Color.WHITE);
    }
    
    /**
     * Palauttaa ladatun karttakuvan
     * @return 
     */
    public Image getOriginal() {
        return this.orig;
    }
    
    /**
     * Palauttaa ladatun karttakuvan mustavalkoversion
     * @return 
     */
    public Image getBlackAndWhite() {
        return this.blackAndWhite;
    }
    
    /**
     * Palauttaa mustavalkokarttaa vastaavan taulukon (alkiot joko 0 = valkoinen, 1 = musta)
     * @return 
     */
    public int[][] getMapArray() {
        return this.map;
    }
}
