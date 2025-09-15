package Vehicle;

import myException.*;

public abstract class Vehicle implements Comparable<Vehicle>{
    private String id;
    private String model;
    private double maxSpeed;
    private double currentMileage;
    private double totalDistanceTraveled;

    protected Vehicle(String id, String model, double maxSpeed){
        this.id = id;
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.currentMileage = 0;
        this.totalDistanceTraveled = 0;
    }

    public abstract void move(double distance) throws InvalidOperationException, InsufficientFuelException;
    public abstract double calculateFuelEfficiency();
    public abstract double estimateJourneyTime(double distance);

    public void displayInfo() {
        System.out.printf("ID: %s, Model: %s, Max Speed: %.2f, Current Mileage: %.2f, Total Distance Traveled: %.2f, ", id, model, maxSpeed, currentMileage, totalDistanceTraveled);
    }

    public void updateTotalDistanceTraveled(double distance){
        this.totalDistanceTraveled += distance;
    }

    protected void setCurrentMileage(double curr){
            this.currentMileage = curr;
    }

    public double getCurrentMileage(){
        return this.currentMileage;
    }

    public String getId(){
        return this.id;
    }

    public String getModel(){
        return this.model;
    }

    public double getMaxSpeed(){
        return this.maxSpeed;
    }

    public void UpdateDistance(double value){
        this.totalDistanceTraveled += value; 
    }

    @Override
    public int compareTo(Vehicle other) {
        // Higher efficiency comes later in sort order
        return Double.compare(this.calculateFuelEfficiency(),other.calculateFuelEfficiency());
    }

}