package FleetManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import Vehicle.*;
import myException.InvalidOperationException;

public class FleetManager {
    private ArrayList<Vehicle> fleet= new ArrayList<>();
    private HashSet<String> Present = new HashSet<>();

    public void addVehicle(Vehicle v){
        // if (Present.contains(v.getId())){
        //     throw new InvalidOperationException();
        // }
        Present.add(v.getId());
        fleet.add(v);
        System.out.printf("Vehicle  VID:%s added Successfully\n", v.getId());
    }

    public boolean IsPresent(String id){
        return Present.contains(id);
    }

    public void removeVehicle(String id) throws InvalidOperationException{
        if (IsPresent(id)==false){
            throw new InvalidOperationException();
        }
        Present.remove(id);
        for (int i=0; i<fleet.size(); i++){
            Vehicle temp = fleet.get(i);
            if (id.equals(temp.getId())){
                fleet.remove(temp);
            }
        }
    }

    public void startAllJourneys(double distance){
        for (int i=0; i<fleet.size(); i++){
            Vehicle temp = fleet.get(i);
            try{
                temp.move(distance);
            }
            catch (Exception e){
                System.out.printf("%s ID Vehicle don't have enough Fuel\n", temp.getId());
            }
        }
    }

    public double getTotalFuelConsumption(double distance){
        double result = 0;
        for (int i=0; i<fleet.size(); i++){
            
            Vehicle v = fleet.get(i);
            if (v instanceof Car){ 
                Car c = (Car) v;
                result += c.consumeFuel(distance);
            }
            else if (v instanceof Truck){ 
                Truck t = (Truck) v;
                result += t.consumeFuel(distance);
            }
            else if (v instanceof Bus){
                Bus b = (Bus) v;
                result += b.consumeFuel(distance);
            }
            else if (v instanceof AirPlane){ 
                AirPlane a = (AirPlane) v;
                result += a.consumeFuel(distance);
            }
            else if (v instanceof CargoShipFuel){
                CargoShipFuel c = (CargoShipFuel) v;
                result += c.consumeFuel(distance); 
            }
        }
        return result;
    }

    public void maintainAll(){
        for (int i=0; i<fleet.size(); i++){
            Vehicle v = fleet.get(i);
            
            if (v instanceof Car){ 
                Car c = (Car) v;
                c.performMaintenance();
            }
            else if (v instanceof Truck){ 
                Truck t = (Truck) v;
                t.performMaintenance();
            }
            else if (v instanceof Bus){
                Bus b = (Bus) v;
                b.performMaintenance();
            }
            else if (v instanceof AirPlane){ 
                AirPlane a = (AirPlane) v;
                a.performMaintenance();
            }
            else if (v instanceof CargoShipFuel){
                CargoShipFuel c = (CargoShipFuel) v;
                c.performMaintenance(); 
            }
            else if (v instanceof CargoShipSail){
                CargoShipSail c = (CargoShipSail) v;
                c.performMaintenance(); 
            }
        }
    }

    public ArrayList<Vehicle> searchByType(Class<?> type) {
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle v : fleet) {
            if (type.isInstance(v)) {
                result.add(v);
            }
        }
        return result;
    }


    public void SortFleetByEfficiency(){
        Collections.sort(fleet);
    }

    public String generateReport(){
        StringBuilder report = new StringBuilder();
        double avgEfficiency = 0;
        double totalMileage = 0;
        int maintenanceCount = 0;

        int cars=0, trucks=0, buses=0, airplanes=0, cargoships=0;

        for (Vehicle v : fleet) {
            avgEfficiency += v.calculateFuelEfficiency();
            totalMileage += v.getCurrentMileage();

            if (v instanceof Car){ 
                cars++;
                Car c = (Car) v;
                maintenanceCount += c.needsMaintencance() ? 1: 0;
            }
            else if (v instanceof Truck){ 
                trucks++;
                Truck t = (Truck) v;
                maintenanceCount += t.needsMaintencance() ? 1: 0;
            }
            else if (v instanceof Bus){
                buses++;
                Bus b = (Bus) v;
                maintenanceCount += b.needsMaintencance() ? 1: 0;  
            }
            else if (v instanceof AirPlane){ 
                airplanes++;
                AirPlane a = (AirPlane) v;
                maintenanceCount += a.needsMaintencance() ? 1: 0;  
            }
            else if (v instanceof CargoShipFuel){
                cargoships++;
                CargoShipFuel c = (CargoShipFuel) v;
                maintenanceCount += c.needsMaintencance() ? 1: 0;  
            }
            else if (v instanceof CargoShipSail){
                cargoships++;
                CargoShipSail c = (CargoShipSail) v;
                maintenanceCount += c.needsMaintencance() ? 1: 0;  
            }
        }
        avgEfficiency /= fleet.size();
        report.append(String.format("Total Vehicles: %d%n", fleet.size()));
        report.append(String.format("By Type -> Cars:%d Trucks:%d Buses:%d Planes:%d Ships:%d %n",cars,trucks,buses,airplanes,cargoships));
        report.append(String.format("Average Fuel Efficiency   : %.2f km/l%n", avgEfficiency));
        report.append(String.format("Total Mileage             : %.2f km%n", totalMileage));
        report.append(String.format("Vehicle Needs Maintenance : %d%n", maintenanceCount));

        return report.toString();
    }

    public void generateIndividualReport(){
        for (Vehicle v : fleet) {
            if (v instanceof Car){
                Car c = (Car) v;
                System.out.println("Car");
                c.displayInfo();
            }
            else if (v instanceof Truck){
                Truck T = (Truck) v;
                System.out.println("Truck");
                T.displayInfo();
            }
            else if (v instanceof Bus){
                Bus B = (Bus) v;
                System.out.println("Bus");
                B.displayInfo();
            }
            else if (v instanceof AirPlane){
                AirPlane a = (AirPlane) v;
                System.out.println("AirPlane");
                a.displayInfo();
            }
            else if (v instanceof CargoShipFuel){
                CargoShipFuel c = (CargoShipFuel) v;
                System.out.println("CargoShip");
                c.displayInfo();
            }
            else if (v instanceof CargoShipSail){
                CargoShipSail c = (CargoShipSail) v;
                System.out.println("CargoShip");
                c.displayInfo();
            }
            System.out.println();
        }
    }

    public ArrayList<Vehicle> getVehicleNeedingMaintenance(){
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle v : fleet) {
            if (v instanceof Car){
                Car c = (Car) v;
                if (c.needsMaintencance()==true) {
                    result.add(v);
                }
            }
            else if (v instanceof Truck){
                Truck T = (Truck) v;
                if (T.needsMaintencance()==true) {
                    result.add(v);
                }
            }
            else if (v instanceof Bus){
                Bus B = (Bus) v;
                if (B.needsMaintencance()==true) {
                    result.add(v);
                }
            }
            else if (v instanceof AirPlane){
                AirPlane a = (AirPlane) v;
                if (a.needsMaintencance()==true) {
                    result.add(v);
                }
            }
            else if (v instanceof CargoShipFuel){
                CargoShipFuel c = (CargoShipFuel) v;
                if (c.needsMaintencance()==true) {
                    result.add(v);
                }
            }
            else if (v instanceof CargoShipSail){
                CargoShipSail c = (CargoShipSail) v;
                if (c.needsMaintencance()==true) {
                    result.add(v);
                }
            }
        }
        return result;
    }

    public void fuelAll(double amount) throws InvalidOperationException{
        for (Vehicle v: fleet){
            if (v instanceof Car){
                Car c = (Car) v;
                c.refuel(amount);
            }
            else if (v instanceof Truck){
                Truck T = (Truck) v;
                T.refuel(amount);
            }
            else if (v instanceof Bus){
                Bus B = (Bus) v;
                B.refuel(amount);
            }
            else if (v instanceof AirPlane){
                AirPlane a = (AirPlane) v;
                a.refuel(amount);
            }
            else if (v instanceof CargoShipFuel){
                CargoShipFuel c = (CargoShipFuel) v;
                c.refuel(amount);
            }
        }
    }

public void saveToFile(String filename) {
    try (FileWriter writer = new FileWriter(filename)) {
        for (Vehicle v : fleet) {
            String type = v.getClass().getSimpleName();
            StringBuilder content = new StringBuilder();

            content.append(type).append(",");

            if (type.equals("Car")) {
                Car c = (Car) v;
                content.append(c.getId()).append(",");
                content.append(c.getModel()).append(",");
                content.append(c.getMaxSpeed()).append(",");
                content.append(c.getCurrentMileage()).append(",");
                content.append(c.getnumWheels()).append(",");
                content.append(c.getfuelLevel()).append(",");
                content.append(c.getPassengerCapacity()).append(",");
                content.append(c.getCurrentpassengers()).append("\n");
            } 
            else if (type.equals("Truck")) {
                Truck t = (Truck) v;
                content.append(t.getId()).append(",");
                content.append(t.getModel()).append(",");
                content.append(t.getMaxSpeed()).append(",");
                content.append(t.getCurrentMileage()).append(",");
                content.append(t.getnumWheels()).append(",");
                content.append(t.getfuelLevel()).append(",");
                content.append(t.getCargoCapacity()).append(",");
                content.append(t.getCurrentCargo()).append("\n");
            } 
            else if (type.equals("Bus")) {
                Bus b = (Bus) v;
                content.append(b.getId()).append(",");
                content.append(b.getModel()).append(",");
                content.append(b.getMaxSpeed()).append(",");
                content.append(b.getCurrentMileage()).append(",");
                content.append(b.getnumWheels()).append(",");
                content.append(b.getfuelLevel()).append(",");
                content.append(b.getPassengerCapacity()).append(",");
                content.append(b.getCurrentpassengers()).append(",");
                content.append(b.getCargoCapacity()).append(",");
                content.append(b.getCurrentCargo()).append("\n");
            } 
            else if (type.equals("AirPlane")) {
                AirPlane a = (AirPlane) v;
                content.append(a.getId()).append(",");
                content.append(a.getModel()).append(",");
                content.append(a.getMaxSpeed()).append(",");
                content.append(a.getCurrentMileage()).append(",");
                content.append(a.getmaxAltitude()).append(",");
                content.append(a.getfuelLevel()).append(",");
                content.append(a.getPassengerCapacity()).append(",");
                content.append(a.getCurrentpassengers()).append(",");
                content.append(a.getCargoCapacity()).append(",");
                content.append(a.getCurrentCargo()).append("\n");
            } 
            else if (type.equals("CargoShipSail")) {
                CargoShipSail c = (CargoShipSail) v;
                content.append(c.getId()).append(",");
                content.append(c.getModel()).append(",");
                content.append(c.getMaxSpeed()).append(",");
                content.append(c.getCurrentMileage()).append(",");
                content.append(c.gethasSail()).append(",");
                content.append(c.getCargoCapacity()).append(",");
                content.append(c.getCurrentCargo()).append("\n");
            } 
            else if (type.equals("CargoShipFuel")) {
                CargoShipFuel c = (CargoShipFuel) v;
                content.append(c.getId()).append(",");
                content.append(c.getModel()).append(",");
                content.append(c.getMaxSpeed()).append(",");
                content.append(c.getCurrentMileage()).append(",");
                content.append(c.gethasSail()).append(",");
                content.append(c.getCargoCapacity()).append(",");
                content.append(c.getCurrentCargo()).append("\n");
            }

            writer.write(content.toString());
        }
    } catch (IOException e) {
        System.out.println("Error writing file: " + e.getMessage());
    }
}


    public void loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();

            while (line != null) {
                String[] parts = line.split(",");
                String type = parts[0].trim();

                switch (type) {
                    case "Car": {
                        String id = parts[1].trim();
                        String model = parts[2].trim();
                        double maxSpeed = Double.parseDouble(parts[3].trim());
                        int numWheels = Integer.parseInt(parts[4].trim());
                        double fuelLevel = Double.parseDouble(parts[5].trim());
                        int passengerCapacity = Integer.parseInt(parts[6].trim());
                        int currentPassengers = Integer.parseInt(parts[7].trim());

                        Car car = new Car(id, model, maxSpeed, numWheels, fuelLevel, passengerCapacity, currentPassengers);
                        this.addVehicle(car);
                        break;
                    }

                    case "Truck": {
                        String id = parts[1].trim();
                        String model = parts[2].trim();
                        double maxSpeed = Double.parseDouble(parts[3].trim());
                        int numWheels = Integer.parseInt(parts[4].trim());
                        double fuelLevel = Double.parseDouble(parts[5].trim());
                        double currentCargo = Double.parseDouble(parts[6].trim());

                        Truck truck = new Truck(id, model, maxSpeed, numWheels, fuelLevel, currentCargo);
                        this.addVehicle(truck);
                        break;
                    }

                    case "Bus": {
                        String id = parts[1].trim();
                        String model = parts[2].trim();
                        double maxSpeed = Double.parseDouble(parts[3].trim());
                        int numWheels = Integer.parseInt(parts[4].trim());
                        double fuelLevel = Double.parseDouble(parts[5].trim());
                        int currentPassengers = Integer.parseInt(parts[6].trim());
                        double currentCargo = Double.parseDouble(parts[7].trim());

                        Bus bus = new Bus(id, model, maxSpeed, numWheels, fuelLevel, currentPassengers, currentCargo);
                        this.addVehicle(bus);
                        break;
                    }

                    case "AirPlane": {
                        String id = parts[1].trim();
                        String model = parts[2].trim();
                        double maxSpeed = Double.parseDouble(parts[3].trim());
                        int maxAltitude = Integer.parseInt(parts[4].trim());
                        double fuelLevel = Double.parseDouble(parts[5].trim());
                        int currentPassengers = Integer.parseInt(parts[6].trim());
                        double currentCargo = Double.parseDouble(parts[7].trim());

                        AirPlane airplane = new AirPlane(id, model, maxSpeed, maxAltitude, fuelLevel, currentPassengers, currentCargo);
                        this.addVehicle(airplane);
                        break;
                    }

                    case "CargoShip": {
                        String id = parts[1].trim();
                        String model = parts[2].trim();
                        double maxSpeed = Double.parseDouble(parts[3].trim());
                        boolean hasSail = Boolean.parseBoolean(parts[4].trim());
                        double currentCargo = Double.parseDouble(parts[5].trim());
                        double fuelLevel = Double.parseDouble(parts[6].trim());

                        if (hasSail==false){
                            CargoShipFuel ship = new CargoShipFuel(id, model, maxSpeed, currentCargo, fuelLevel);
                            this.addVehicle(ship);                   
                        } else{
                            CargoShipSail ship = new CargoShipSail(id, model, maxSpeed, currentCargo);
                            this.addVehicle(ship);
                        }
                        break;
                    }

                    default: 
                }

                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


}
