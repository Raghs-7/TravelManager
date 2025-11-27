package Vehicle;
import myException.*;

import Abstract.WaterVehicle;
import Interface.CargoCarrier; 
import Interface.Maintainable;

public class CargoShipSail extends WaterVehicle implements Maintainable, CargoCarrier{

    private int CargoCapacity = 50000;
    private double CurrentCargo; // my variable
    private boolean maintenanceNeeded = false;

    
    public CargoShipSail(String id, String model, double maxSpeed, double CurrentCargo){
        super(id, model, maxSpeed, true);
        this.CurrentCargo = CurrentCargo;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Current Cargo: %.2f, Cargo Capacity: %d, Maintenance Needed: %b\n", CurrentCargo, CargoCapacity, maintenanceNeeded);
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
        setCurrentMileage(getCurrentMileage() + distance);
        this.updateTotalDistanceTraveled(distance);
        System.out.println("Vehicle VID: "+this.getId() + ", Sailing with cargo .." + distance + " km");
    }

    @Override
    public double calculateFuelEfficiency(){
        return 4.0;
    }

    
}