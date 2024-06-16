package MiniProject;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    private Hedgehog hedgehog;
    private int lives = 3;

    private Parent HedgehogApp() {
        root = new Pane();
        root.setPrefSize(800, 600);

        hedgehog = new Hedgehog();


        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        // Start Button and removes it once the button has been pressed
        Button startButton = new Button("Start");
        startButton.setTranslateX(350);
        startButton.setTranslateY(300);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.start();
                root.getChildren().remove(startButton);
                root.getChildren().add(hedgehog.getHedgehog());
            }
        });

        root.getChildren().add(startButton);
        return root;
    }

    /**
     * @return a new vehicle
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

    /**
     * Moves the vehicles and checks if the hedgehog has collided with a vehicle
     * spawns a new vehicle with a 7% chance every frame
     * determines if vehicle is a car or truck with a 50% chance
     */
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

    /**
     * Checks if the hedgehog has collided with a vehicle
     * If the hedgehog has collided with a vehicle, the hedgehog is moved to the starting position and looses a life
     * if lives = 0 the game is over
     * if the hedgehog reaches the top of the screen the game is won
     * This method was created with the help of the CO-pilot
     */
    private void checkState() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleImage().getBoundsInParent().intersects(hedgehog.getHedgehog().getBoundsInParent())) {
                hedgehog.getHedgehog().setTranslateX(0);
                hedgehog.getHedgehog().setTranslateY(600 - 39);
                lives--;
                return;
            }
        }

        if (lives == 0){
            timer.stop();
            String lose = "You lose!";
            HBox hbox = new HBox();
            hbox.setTranslateX(325);
            hbox.setTranslateY(250);

            root.getChildren().add(hbox);


            for (int i = 0; i < lose.length(); i++) {
                char c = lose.charAt(i);

                Text text = new Text(String.valueOf(c));
                text.setFont(Font.font(48));
                text.setOpacity(0);
                hbox.getChildren().add(text);

                FadeTransition ft = new FadeTransition(Duration.seconds(0.66), text);
                ft.setToValue(1);
                ft.setDelay(Duration.seconds(i * 0.15));
                ft.play();

                Button restartButton = new Button("Restart");
                restartButton.setTranslateX(350);
                restartButton.setTranslateY(300);
                root.getChildren().add(restartButton);
                restartButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        root.getChildren().remove(hbox);
                        root.getChildren().remove(restartButton);
                        restartGame();
                    }
                });
            }

            root.getChildren().remove(hedgehog.getHedgehog());
        }

        if (hedgehog.getHedgehog().getTranslateY() <= 0) {
            timer.stop();
            String win = "You win!";
            HBox hbox = new HBox();
            hbox.setTranslateX(325);
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
                    hedgehog.moveHedgehog(0, -40);
                    break;
                case DOWN:
                    hedgehog.moveHedgehog(0, 40);
                    break;
                case LEFT:
                    hedgehog.moveHedgehog(-40, 0);
                    break;
                case RIGHT:
                    hedgehog.moveHedgehog(40, 0);
                    break;
            }

        });
        primaryStage.show();
    }

    private void restartGame() {
        lives = 3;

        for (Vehicle vehicle : vehicles) {
            root.getChildren().remove(vehicle.getVehicleImage());
        }
        vehicles.clear();
        //
        root.getChildren().add(hedgehog.getHedgehog());
        hedgehog.getHedgehog().setTranslateX(0);
        hedgehog.getHedgehog().setTranslateY(600 - 39);
        //

        //
        timer.start();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
