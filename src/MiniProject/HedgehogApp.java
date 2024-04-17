package MiniProject;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class HedgehogApp extends Application {
    private AnimationTimer timer;
    private Pane root;
    private List<Vehicle> vehicles = new ArrayList<>();
    private Node hedgehog;

    private Parent HedgehogApp() {
        root = new Pane();
        root.setPrefSize(800, 600);

        hedgehog = initHedgehog();

        root.getChildren().add(hedgehog);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();
        return root;
    }

    private Node initHedgehog() {
        Rectangle hedgehog = new Rectangle(38, 38, Color.BROWN);
        hedgehog.setTranslateY(600 - 39);
        return hedgehog;
    }

    /**
     * @return a new car
     * Spawns the car at a random position on the road
     */
    private Vehicle spawnVehicle(String type) {
        if (type.equals("Car")) {
            return new Car(root);
        } else if (type.equals("Truck")) {
            return new Truck(root);
        } else {
            return null;
        }
    }

    private void onUpdate() {
        for (Vehicle vehicle : vehicles)
            vehicle.getVehicleImage().setTranslateX(vehicle.getVehicleImage().getTranslateX() + vehicle.getSpeed());

        if (Math.random() < 0.075) {
            if (Math.random() < 0.5) {
                vehicles.add(spawnVehicle("Car"));
            } else {
                vehicles.add(spawnVehicle("Truck"));
            }
            checkState();
        }
    }

    private void checkState() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleImage().getBoundsInParent().intersects(hedgehog.getBoundsInParent())) {
                hedgehog.setTranslateX(0);
                hedgehog.setTranslateY(600 - 39);
                return;
            }
        }

        if (hedgehog.getTranslateY() <= 0) {
            timer.stop();
            String win = "You win!";
            HBox hbox = new HBox();
            hbox.setTranslateX(350);
            hbox.setTranslateY(250);


            root.getChildren().add(hbox);


            for (int i = 0; i < win.length(); i++) {
                char c = win.charAt(i);

                Text text = new Text(String.valueOf(c));
                text.setFont(Font.font(48));
                text.setOpacity(0);
                hbox.getChildren().add(text);

                FadeTransition ft = new FadeTransition(Duration.seconds(0.66), text);
                ft.setToValue(1);
                ft.setDelay(Duration.seconds(i * 0.15));
                ft.play();
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(HedgehogApp()));
        primaryStage.getScene().setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    hedgehog.setTranslateY(hedgehog.getTranslateY() - 40);
                    break;
                case DOWN:
                    hedgehog.setTranslateY(hedgehog.getTranslateY() + 40);
                    break;
                case LEFT:
                    hedgehog.setTranslateX(hedgehog.getTranslateX() - 40);
                    break;
                case RIGHT:
                    hedgehog.setTranslateX(hedgehog.getTranslateX() + 40);
                    break;
            }
        });


        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
