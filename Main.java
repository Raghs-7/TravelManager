import java.util.ArrayList;
import java.util.Scanner;

import FleetManager.FleetManager;
import Vehicle.*;
import myException.InvalidOperationException;


public class Main {
   public static void main(String[] args) {
      FleetManager fleetManager = new FleetManager();
      Scanner sc = new Scanner(System.in);

      while (true) {
         System.out.println("1. Add Vehicle");
         System.out.println("2. Remove Vehicle");
         System.out.println("3. Start Journey");
         System.out.println("4. Refuel All");
         System.out.println("5. Perform Maintenance");
         System.out.println("6. Generate Report");
         System.out.println("7. Save Fleet");
         System.out.println("8. Load Fleet");
         System.out.println("9. Search by Type");
         System.out.println("10. List Vehicles Needing Maintenance");
         System.out.println("11. Exit");
         System.out.print("Choose an option (1-11): ");
         int option = sc.nextInt();

         if (option == 1) {
            System.out.println("What type of Vehicle You want to add ?");
            System.out.println("1. Car");
            System.out.println("2. Truck");
            System.out.println("3. Bus");
            System.out.println("4. AirPlane");
            System.out.println("5. CargoShip");
            int type = sc.nextInt();
            System.out.println("Enter ID:");
            String id = sc.next();
            while(fleetManager.IsPresent(id)) {
               System.out.println("Another entry of this ID is present");
               System.out.println("Enter Unique ID:");
               id = sc.next();
            }
            System.out.println("Enter Model:");
            String model = sc.next();
            System.out.println("Enter Max Speed (km/h):");
            double maxSpeed = sc.nextDouble();
            double fuelLevel;
            int passengers;

            if (type == 1) { // Car
               System.out.println("Number of Wheels:");
               int num = sc.nextInt();
               System.out.println("Fuel level:");
               fuelLevel = sc.nextDouble();
               System.out.println("Current passengers:");
               passengers = sc.nextInt();
               Car car = new Car(id, model, maxSpeed, num, fuelLevel, 5, passengers);
               fleetManager.addVehicle(car);

            } else if (type == 2) { // Truck
               System.out.println("Number of Wheels:");
               int num = sc.nextInt();
               System.out.println("Fuel level:");
               fuelLevel = sc.nextDouble();
               System.out.println("Current cargo (kg):");
               double cargoWeight = sc.nextDouble();
               Truck truck = new Truck(id, model, maxSpeed, num, fuelLevel, cargoWeight);
               fleetManager.addVehicle(truck);

            } else if (type == 3) { // Bus
               System.out.println("Number of Wheels:");
               int num = sc.nextInt();
               System.out.println("Fuel level:");
               fuelLevel = sc.nextDouble();
               System.out.println("Current passengers:");
               passengers = sc.nextInt();
               System.out.println("Current cargo (kg):");
               double cargoWeight = sc.nextDouble();
               Bus bus = new Bus(id, model, maxSpeed, num, fuelLevel, passengers, cargoWeight);
               fleetManager.addVehicle(bus);

            } else if (type == 4) { // AirPlane
               System.out.println("Max altitude:");
               int maxAltitude = sc.nextInt();
               System.out.println("Fuel level:");
               fuelLevel = sc.nextDouble();
               System.out.println("Current passengers:");
               passengers = sc.nextInt();
               System.out.println("Current cargo (kg):");
               double CurrentCargo = sc.nextDouble();
               AirPlane airplane = new AirPlane(id, model, maxSpeed, maxAltitude, fuelLevel, passengers, CurrentCargo);
               fleetManager.addVehicle(airplane);
            
            } else if (type == 5) { // CargoShip
               System.out.println("Has sail? (true/false):");
               boolean hasSail = Boolean.parseBoolean(sc.next());
               System.out.println("Current cargo (kg):");
               double CurrentCargo = sc.nextDouble();

               if (hasSail){
                  CargoShipSail cargoShip= new CargoShipSail(id, model, maxSpeed, CurrentCargo);
                  fleetManager.addVehicle(cargoShip);
               }
               else {   
                  System.out.println("Fuel level:");
                  fuelLevel = sc.nextDouble();
                  CargoShipFuel cargoShip = new CargoShipFuel(id, model, maxSpeed, CurrentCargo, fuelLevel);
                  fleetManager.addVehicle(cargoShip);
               }

            } else System.out.println("Invalid Input");
            
         } 
         else if (option == 2) {
            System.out.println("Enter the ID of the vehicle you want to delete ");
            String id = sc.next();

            try {
               fleetManager.removeVehicle(id);
               System.out.println("Vehicle removed successfully");
            } catch (InvalidOperationException e) {
               System.out.println("No Vehicle of this ID is present");
            } catch (Exception e) {
               System.out.println("Invalid Input");
            }
         } 
         else if (option == 3) {
            System.out.println("Enter the distance of the journey :");
            double distance = sc.nextDouble();

            fleetManager.startAllJourneys(distance);
            System.out.println("Started all the journeys");
         } 
         else if (option == 4) {
            System.out.println("Enter the amount of Fuel you want to fill");
            double fuelAmount = sc.nextDouble();

            try {
               fleetManager.fuelAll(fuelAmount);
               System.out.println("All Vehicle are refueled successfully");
            } catch (Exception e) {
               System.out.println("Invalid amount of fuel");
            }
         } 
         else if (option == 5) {
            fleetManager.maintainAll();
            System.out.println("Successfully performed Maintenance ");
         } 
         else if (option == 6) {
            System.out.println(fleetManager.generateReport());
            System.out.println();
            fleetManager.generateIndividualReport();
         } 
         else if (option == 7) {
            System.out.println("Enter the file name you want to save the data");
            String filename = sc.next();
            fleetManager.saveToFile(filename);
            System.out.printf("Data Saved to %s successfully%n", filename);
         } 
         else if (option == 8) {
            System.out.println("Enter the file name from which you want to load the data");
            String filename = sc.next();
            fleetManager.loadFromFile(filename);
            System.out.printf("Data Loaded from %s successfully%n", filename);
         } 
         else if (option == 9) {
            System.out.println("Enter type name (Car/Truck/Bus/AirPlane/CargoShip):");
            String typeName = sc.next().trim();
            Class<?> typeClass;

            if (typeName.equals("Car")) {
               typeClass = Car.class;
            } else if (typeName.equals("Truck")) {
               typeClass = Truck.class;
            } else if (typeName.equals("Bus")) {
               typeClass = Bus.class;
            } else if (typeName.equals("AirPlane")) {
               typeClass = AirPlane.class;
            } else if (typeName.equals("CargoShip")) {
               typeClass = CargoShipSail.class;
               ArrayList<Vehicle> searchResults = fleetManager.searchByType(typeClass);
               for (Vehicle v : searchResults) {
                  System.out.println(v.getClass().getSimpleName());
                  v.displayInfo();
               }
               typeClass = CargoShipFuel.class;
            } else {
               typeClass = null;
            }

            ArrayList<Vehicle> searchResults = fleetManager.searchByType(typeClass);
            for (Vehicle v : searchResults) {
               System.out.println(v.getClass().getSimpleName());
               v.displayInfo();
            }
         } else if (option == 10) {
            ArrayList<Vehicle> vehiclesNeedingMaintenance = fleetManager.getVehicleNeedingMaintenance();
            if (vehiclesNeedingMaintenance.size() == 0) {
               System.out.println("No vehicles need maintenance.");
               continue;
            }
            for (Vehicle v : vehiclesNeedingMaintenance) {
               System.out.println(v.getClass().getSimpleName());
               v.displayInfo();
               System.out.println();
            }
         } else if (option == 11) {
            System.out.println("Exiting .......");
            break;
         } else {
            System.out.println("Invalid input");
         }
      }
   }
}
