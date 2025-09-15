package Vehicle;

import myException.*;

import Abstract.LandVehicle;
import Interface.*;

public class Bus extends LandVehicle implements FuelConsumable, PassengerCarrier, CargoCarrier, Maintainable{

    private double fuellevel = 0;
    private int PassengerCapacity = 50;
    private int Currentpassengers;
    private double CurrentCargo; // my variable
    private int CargoCapacity = 500;
    private boolean maintenanceNeeded = false;

    public Bus(String id, String model, double maxSpeed,int numWheels, double fuellevel, int Currentpassengers, double CurrentCargo){
        super(id, model, maxSpeed, numWheels);
        this.fuellevel = fuellevel;
        this.Currentpassengers = Currentpassengers;
        this.CurrentCargo = CurrentCargo;
    }

    // Maintainable methods
    public void scheduleMaintenance(){
        this.maintenanceNeeded = true;
    }
    public boolean needsMaintencance(){
        double Mileage = getCurrentMileage();
        if (Mileage>10000){
            return true;
        }
        else return false;
    }
    public void performMaintenance(){
        this.maintenanceNeeded = false;
        System.out.println("Maintenance is Done ");
        setCurrentMileage(0.0);
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

    //CargoCarrier Methods
    public void loadCargo(double weight) throws OverloadException {
        if (this.CurrentCargo + weight > this.CargoCapacity){
            throw new OverloadException();
        }
        this.CurrentCargo += weight;
    }
    public void unloadCargo(double weight) throws OverloadException{
        if (weight > this.CurrentCargo){
            throw new OverloadException();
        }
        this.CurrentCargo -= weight;
    }
    public double getCargoCapacity() {
        return this.CargoCapacity;
    }
    public double getCurrentCargo() {
        return this.CurrentCargo;
    }

    // Passenger Carrier
    public void boardPassengers(int count) throws OverloadException{
        if (this.Currentpassengers+count<=this.PassengerCapacity){
            this.Currentpassengers += count;
        }
        else {
            throw new OverloadException();
        }
    }
    public void disembarkPassengers(int count) throws InvalidOperationException{
        if (count<=this.PassengerCapacity){
            this.Currentpassengers -= count;
        }
        else{
            throw new InvalidOperationException();
        }
    }
    public int getPassengerCapacity(){
        return this.PassengerCapacity;
    }
    public int getCurrentpassengers(){
        return this.Currentpassengers;
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
        System.out.println(distance + " km, Transporting passengers and cargo ..");
    }

    @Override
    public double calculateFuelEfficiency(){
        return 10.0;
    }


}




