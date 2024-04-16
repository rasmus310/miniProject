package HedgehogGame;

import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {

        private Hedgehog hedgehog;
        private Road road;
        private int level;

        public Game(){
            this.hedgehog = new Hedgehog();
            this.road = new Road();
            this.level = 1;
        }



    public static void main(String[] args) {
        launch(args);
    }
}
