package MiniProject;

/**
 * Interface for setting the speed of a vehicle
 * An interface is used to ensure that all classes that implement this interface have the setSpeed method
 */
public interface DrivingBehaviour {
    void setSpeed(double speed);
}
