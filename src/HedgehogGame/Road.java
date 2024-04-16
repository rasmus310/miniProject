package HedgehogGame;

import java.util.ArrayList;

public class Road {
    private int length;
    private int width;
    private ArrayList<Vehicle> vehicles;

public Road(int length, int width) {
    this.length = length;
    this.width = width;
    this.vehicles = new ArrayList<Vehicle>();
    }

}