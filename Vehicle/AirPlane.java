package Vehicle;

import myException.*;

import Abstract.AirVehicle;
import Interface.*;


public class AirPlane extends AirVehicle implements FuelConsumable, PassengerCarrier, CargoCarrier, Maintainable{

    private double fuellevel = 0;
    private int PassengerCapacity = 200;
    private int CargoCapacity = 10000;
    private int Currentpassengers;
    private double CurrentCargo; // my variable
    private boolean maintenanceNeeded = false;

    public AirPlane(String id, String model, double maxSpeed,int maxAltitude, double fuellevel, int Currentpassengers, double CurrentCargo){
        super(id, model, maxSpeed, maxAltitude);
        this.fuellevel = fuellevel;
        this.Currentpassengers = Currentpassengers;
        this.CurrentCargo = CurrentCargo;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Fuel Level: %.2f, Current Passengers: %d, Passenger Capacity: %d, Current Cargo: %.2f, Cargo Capacity: %d, Maintenance Needed: %b\n", fuellevel, Currentpassengers, PassengerCapacity, CurrentCargo, CargoCapacity, maintenanceNeeded);
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
    public void loadCargo(double weight) throws OverloadException{
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

    @Override
    public void move(double distance) throws InsufficientFuelException{
        double FuelNedded = consumeFuel(distance);

        if (FuelNedded>this.fuellevel){
            throw new InsufficientFuelException();
        }
        setCurrentMileage(getCurrentMileage() + distance);
        this.fuellevel -= FuelNedded;
        this.updateTotalDistanceTraveled(distance);
        System.out.println("Vehicle VID: "+this.getId() + ", Flying at .. " +this.getmaxAltitude() + " km");
    }

    @Override
    public double calculateFuelEfficiency(){
        return 5.0;
    }
}