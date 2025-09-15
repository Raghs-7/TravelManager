package Vehicle;

import myException.*;

import Abstract.LandVehicle;
import Interface.*;

public class Car extends LandVehicle implements FuelConsumable, PassengerCarrier, Maintainable {
    private double fuellevel = 0;
    private int PassengerCapacity = 5;
    private int Currentpassengers;
    private boolean maintenanceNeeded = false;

    public Car(String id, String model, double maxSpeed,int numWheels, double fuellevel, int PassengerCapacity,int Currentpassengers){
        super(id, model, maxSpeed, numWheels);
        this.fuellevel = fuellevel;
        this.PassengerCapacity = PassengerCapacity;
        this.Currentpassengers = Currentpassengers;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Fuel Level: %.2f, Current Passengers: %d, Passenger Capacity: %d, Maintenance Needed: %b\n", fuellevel, Currentpassengers, PassengerCapacity, maintenanceNeeded);
    }

    // Maintainable methods
    public void scheduleMaintenance(){
        this.maintenanceNeeded = this.needsMaintencance();
    }
    public boolean needsMaintencance(){
        double Mileage = getCurrentMileage();
        if (Mileage>10000){
            return true;
        }
        else return false;
    }
    public void performMaintenance(){
        setCurrentMileage(0.0);
        this.maintenanceNeeded = false;
        System.out.println("Maintenance is Done ");
    }

    //PassengerCarrier method
    public void boardPassengers(int count) throws OverloadException{
        if (this.Currentpassengers+count<=this.PassengerCapacity){
            this.Currentpassengers += count;
        }
        else {
            throw new OverloadException();
        }
    }
    public void disembarkPassengers(int count) throws InvalidOperationException{
        if (count<=this.Currentpassengers){
            this.Currentpassengers -= count;
        }
        else {
            throw new InvalidOperationException();
        }
    }
    public int getPassengerCapacity(){
        return this.PassengerCapacity;
    }
    public int getCurrentpassengers(){
        return this.Currentpassengers;
    }

    //FuelConsumable
    public void refuel(double amount) throws InvalidOperationException{
        if (amount>0){
            this.fuellevel += amount;
        }
        else {
            throw new InvalidOperationException();
        }
    }
    public double getFuelLevel(){
        return this.fuellevel;
    }
    public double consumeFuel(double distance){
        return distance/calculateFuelEfficiency();
    }
    public double getfuelLevel(){
        return this.fuellevel;
    }


    @Override
    public void move(double distance) throws InsufficientFuelException{
        double FuelNedded = consumeFuel(distance);
        if (FuelNedded>this.fuellevel){
            throw new InsufficientFuelException();
        }
        setCurrentMileage(getCurrentMileage() + distance);
        this.fuellevel -= FuelNedded;
        this.updateTotalDistanceTraveled(distance);
        System.out.println("Vehicle VID: "+this.getId() + ", Driving on road .." + distance + " km");
    }

    @Override
    public double calculateFuelEfficiency(){
        return 15.0;
    }

}
