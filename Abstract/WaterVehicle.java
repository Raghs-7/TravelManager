package Abstract;

import Vehicle.Vehicle;

public abstract class WaterVehicle extends Vehicle {
    private boolean hasSail;

    protected WaterVehicle(String id, String model, double maxSpeed, boolean hasSail){
        super(id, model, maxSpeed);
        this.hasSail = hasSail;
    }

    @Override
    public double estimateJourneyTime(double distance){
        double Max_Speed = getMaxSpeed();
        double result = distance/Max_Speed;
        return result*1.15;
    }

    public boolean gethasSail(){
        return this.hasSail;
    }
}