package Vehicle;


import myException.*;
import Abstract.LandVehicle;
import Interface.CargoCarrier;
import Interface.FuelConsumable;
import Interface.Maintainable;

public class Truck extends LandVehicle implements FuelConsumable, CargoCarrier, Maintainable {
    private double fuellevel = 0;
    private double CurrentCargo; // my variable
    private int CargoCapacity = 500;
    private boolean maintenanceNeeded = false;

    public Truck(String id, String model, double maxSpeed,int numWheels, double fuellevel, double CurrentCargo){
        super(id, model, maxSpeed, numWheels);
        this.fuellevel = fuellevel;
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
        else{
            throw new InvalidOperationException();
        }
    }
    public double getFuelLevel(){
        return this.fuellevel;
    }
    public double consumeFuel(double distance){
        double x = 1.0;
        if (this.CurrentCargo>0.5*this.CargoCapacity) x = 1.1;
        return x*distance/calculateFuelEfficiency();
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

    @Override
    public void move(double distance) throws InsufficientFuelException{
        double FuelNedded = consumeFuel(distance);
        if (FuelNedded>this.fuellevel){
            throw new InsufficientFuelException();
        }
        setCurrentMileage(getCurrentMileage() + distance);
        this.fuellevel -= FuelNedded;
        this.updateTotalDistanceTraveled(distance);
        System.out.println(distance + " km, Hauling Cargo ..");
    }

    @Override
    public double calculateFuelEfficiency(){
        return 8.0;
    }

}
