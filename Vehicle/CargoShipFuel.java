package Vehicle;


import myException.*;

import Abstract.WaterVehicle;
import Interface.*;


public class CargoShipFuel extends WaterVehicle implements CargoCarrier, Maintainable, FuelConsumable {

    private double fuellevel = 0;
    private int CargoCapacity = 50000;
    private double CurrentCargo; // my variable
    private boolean maintenanceNeeded = false;

    public CargoShipFuel(String id, String model, double maxSpeed, double CurrentCargo, double fuellevel){
        super(id, model, maxSpeed, false);
        this.fuellevel = fuellevel;
        this.CurrentCargo = CurrentCargo;
    }

    // Maintainable methods
    public void scheduleMaintenance(){
        this.maintenanceNeeded = this.needsMaintencance();
    }
    public boolean needsMaintencance(){
        double Mileage = this.getCurrentMileage();
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

    @Override
    public void move(double distance) throws InsufficientFuelException{
        double FuelNedded = consumeFuel(distance);
        if (FuelNedded>this.fuellevel){
            throw new InsufficientFuelException();
        }
        setCurrentMileage(getCurrentMileage() + distance);
        this.fuellevel -= FuelNedded;
        this.updateTotalDistanceTraveled(distance);
        System.out.println(distance + " km, Sailing with cargo .." );
    }

    @Override
    public double calculateFuelEfficiency(){
        return 4.0;
    }

}
