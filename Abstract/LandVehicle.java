package Abstract;

import Vehicle.Vehicle;

public abstract class LandVehicle extends Vehicle{
    private int numWheels;


    protected LandVehicle(String id, String model, double maxSpeed,int numWheels){
        super(id, model, maxSpeed);
        this.numWheels = numWheels;
    }

    @Override
    public double estimateJourneyTime(double distance){
        double Max_Speed = getMaxSpeed();
        double result = distance/Max_Speed;
        return result*1.1;
    }

    public int getnumWheels(){
        return this.numWheels;
    }

}