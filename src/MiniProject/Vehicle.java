package MiniProject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Vehicle implements DrivingBehaviour{
    private double speed;
    private ImageView vehicleImage;

    /**
     * @param root
     * @param imagePath
     * Spawns the vehicle at a random position on the road, but not on the lowest "edge"
     */
    public Vehicle(Pane root, String imagePath){
        Image image = new Image(imagePath);
        this.vehicleImage = new ImageView(image);
        this.vehicleImage.setFitWidth(40);
        this.vehicleImage.setFitHeight(40);
        vehicleImage.setTranslateY((int) (Math.random() * 14) * 40);
        root.getChildren().add(vehicleImage);
    }

    public ImageView getVehicleImage(){
        return this.vehicleImage;
    }

    /**
     * @param speed
     * Sets the speed of the vehicle
     */
    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * @return the speed of the vehicle
     * Gets the speed of the vehicle from
     */
    public double getSpeed() {
        return speed;
    }
}

