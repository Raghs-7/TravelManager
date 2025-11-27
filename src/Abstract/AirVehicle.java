package Abstract;

import Vehicle.Vehicle;

public abstract class AirVehicle extends Vehicle {
    private double maxAltitude;

    protected AirVehicle(String id, String model, double maxSpeed, double maxAltitude){
        super(id, model, maxSpeed);
        this.maxAltitude = maxAltitude;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Max Altitude: %.2f, ", maxAltitude);
    }

    @Override
    public double estimateJourneyTime(double distance){
        double Max_Speed = getMaxSpeed();
        double result = distance/Max_Speed;
        return result*0.95;
    }

    public double getmaxAltitude(){
        return maxAltitude;
    }

}
