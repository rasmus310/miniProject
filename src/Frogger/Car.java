package Frogger;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Car extends Vehicle {
    private double speed;
    private ImageView car;

    public Car(Pane root) {
        super(root, "file:src/Frogger/Enemy.png");

        double minSpeed = 3.0;
        double maxSpeed = 7.0;
        this.speed = minSpeed + Math.random() * (maxSpeed - minSpeed); // initial speed
    }
@Override
    public double getSpeed() {
        return this.speed;
    }
}