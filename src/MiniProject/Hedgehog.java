package MiniProject;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Hedgehog {
    private Node hedgehog;
    private ImageView hedgehog;

    public Hedgehog() {
        this.hedgehog = initHedgehog();
    }
    // Need to change it to an image instead of a rectangle
    private Node initHedgehog() {
        Rectangle hedgehog = new Rectangle(38, 38, Color.BROWN);
        hedgehog.setTranslateY(600 - 39);
        return hedgehog;
    }

    public Node getHedgehog() {
        return this.hedgehog;
    }

    /**
     * @param x
     * @param y
     * Moves the hedgehog by x and y
     */
    public void moveHedgehog(double x, double y) {
        hedgehog.setTranslateX(hedgehog.getTranslateX() + x);
        hedgehog.setTranslateY(hedgehog.getTranslateY() + y);
    }
}