import java.util.ArrayList;
import java.util.Scanner;

import FleetManager.FleetManager;
import Vehicle.*;


public class Main {

   private static void printCli(){
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
   }

   private static void option1(FleetManager fleetManager, Scanner sc) {
      int type;
      while(true){
         try{
            System.out.println("What type of Vehicle You want to add ?");
            System.out.println("1. Car");
            System.out.println("2. Truck");
            System.out.println("3. Bus");
            System.out.println("4. AirPlane");
            System.out.println("5. CargoShip");
            type = sc.nextInt();
            if (type>5) throw new Exception();
            break;
         }
         catch (Exception e){
            System.out.println("Invalid type");
            sc.nextLine();
         }
      }

      String id;
      while (true){
         try{
            System.out.println("Enter ID:");
            id = sc.next();
            if (fleetManager.IsPresent(id)){
               System.out.println("Another entry of " + id + " is present");
               throw new Exception();
            }
            break;
         }
         catch (Exception e){
            System.out.println("Invalid ID");
         }
      }

      System.out.println("Enter Model: ");
      String model = sc.next();

      double maxSpeed;
      while (true){
         try{
            System.out.println("Enter Max Speed (km/h): ");
            maxSpeed = sc.nextDouble();
            break;
         }
         catch (Exception e){
            System.out.println("Invalid Max Speed");
            sc.nextLine();
         }
      }

      if (type == 1) { // Car
         int num;
         while(true){
            try{
               System.out.println("Number of Wheels: ");
               num = sc.nextInt();
               break;
            }
            catch (Exception e){
               System.out.println("Invalid Number of Wheels");
               sc.nextLine();
            }
         }
         
         Car car = new Car(id, model, maxSpeed, num, 0.0, 5, 0);
         fleetManager.addVehicle(car);

      } else if (type == 2) { // Truck
         int num;
         while(true){
            try{
               System.out.println("Number of Wheels: ");
               num = sc.nextInt();
               break;
            }
            catch (Exception e){
               System.out.println("Invalid Number of Wheels");
               sc.nextLine();
            }
         }
         Truck truck = new Truck(id, model, maxSpeed, num, 0.0, 0.0);
         fleetManager.addVehicle(truck);

      } else if (type == 3) { // Bus
         int num;
         while(true){
            try{
               System.out.println("Number of Wheels: ");
               num = sc.nextInt();
               break;
            }
            catch (Exception e){
               System.out.println("Invalid Number of Wheels");
               sc.nextLine();
            }
         }
         Bus bus = new Bus(id, model, maxSpeed, num, 0.0, 0, 0.0);
         fleetManager.addVehicle(bus);

      } else if (type == 4) { // AirPlane
         int maxAltitude;
         while(true){
            try{
               System.out.println("Max altitude:");
               maxAltitude = sc.nextInt();
               if (maxAltitude <= 0){
                  System.out.println("Max Altitude should be positive");
                  throw new Exception();
               }
               break;
            }
            catch (Exception e){
               System.out.println("Invalid maxAltitude");
               sc.nextLine();
            }
         }
         AirPlane airplane = new AirPlane(id, model, maxSpeed, maxAltitude, 0.0, 0, 0.0);
         fleetManager.addVehicle(airplane);
      
      } else if (type == 5) { // CargoShip
         System.out.println("Has sail? (true/false):");
         boolean hasSail = Boolean.parseBoolean(sc.next());

         if (hasSail){
            CargoShipSail cargoShip= new CargoShipSail(id, model, maxSpeed, 0);
            fleetManager.addVehicle(cargoShip);
         }
         else {   
            CargoShipFuel cargoShip = new CargoShipFuel(id, model, maxSpeed, 0, 0);
            fleetManager.addVehicle(cargoShip);
         }

      } else System.out.println("Invalid Input");
   }

   private static void option2(FleetManager fleetManager, Scanner sc) {
      System.out.println("Enter the ID of the vehicle you want to delete ");
      String id;
      while(true){
         try{
            id = sc.next();
            break;
         }
         catch (Exception e){
            System.out.println("Invalid ID");
            sc.nextLine();
         }
      }
      
      try {
         fleetManager.removeVehicle(id);
         System.out.println("Vehicle removed successfully");
      } catch (Exception e) {
         System.out.println("No Vehicle of this ID is present");
      }
   }
   
   private static void option3(FleetManager fleetManager, Scanner sc) {
      double distance;
      while (true){
         try{
            System.out.println("Enter the distance of the journey :");
            distance = sc.nextDouble();
            if (distance <= 0){
               System.out.println("Distance should be positive");
               throw new Exception();
            }
            break;
         }
         catch (Exception e){
            System.out.println("Invalid Distance");
            sc.nextLine();
         }
      }
      fleetManager.startAllJourneys(distance);
   }
   
   private static void option4(FleetManager fleetManager, Scanner sc) {
      System.out.println("Enter the amount of Fuel you want to fill");

      while (true){
         try {
            double fuelAmount = sc.nextDouble();
            fleetManager.fuelAll(fuelAmount);
            System.out.println("All Vehicle are refueled successfully");
            break;
         } catch (Exception e) {
            System.out.println("Invalid amount of fuel");
            sc.nextLine();
         }
      }
   }

   private static void option5(FleetManager fleetManager, Scanner sc) {
      fleetManager.maintainAll();
      System.out.println("Successfully performed Maintenance ");
   }

   private static void option6(FleetManager fleetManager) {
      System.out.println(fleetManager.generateReport());
      System.out.println();
      fleetManager.generateIndividualReport();
   }
   
   private static void option7(FleetManager fleetManager, Scanner sc) {
      System.out.println("Enter the file name you want to save the data");
      String filename = sc.next();
      fleetManager.saveToFile(filename);
      System.out.printf("Data Saved to %s successfully%n", filename);
   }

   private static void option8(FleetManager fleetManager, Scanner sc) {
      try{
         System.out.println("Enter the file name from which you want to load the data");
         String filename = sc.next();
         fleetManager.loadFromFile(filename);
         System.out.printf("Data Loaded from %s successfully%n", filename);
      }
      catch (Exception e){
         System.out.println("File not found");
      }
      
   }

   private static void option9(FleetManager fleetManager, Scanner sc) {
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
   }
   
   private static void option10(FleetManager fleetManager, Scanner sc) {
      ArrayList<Vehicle> vehiclesNeedingMaintenance = fleetManager.getVehicleNeedingMaintenance();
      if (vehiclesNeedingMaintenance.size() == 0) {
         System.out.println("No vehicles need maintenance.");
         return;
      }
      for (Vehicle v : vehiclesNeedingMaintenance) {
         System.out.println(v.getClass().getSimpleName());
         v.displayInfo();
         System.out.println();
      }
   }

   public static void main(String[] args) {
      FleetManager fleetManager = new FleetManager();
      Scanner sc = new Scanner(System.in);

      while (true) {
         printCli();
         int option;

         try{
            option = sc.nextInt();
         }
         catch (Exception e){
            System.out.println("Invalid input");
            sc.next(); // clear the invalid input
            continue;
         }

         if (option == 1) {
            option1(fleetManager, sc);
         } else if (option == 2) {
            option2(fleetManager, sc);
         } else if (option == 3) {
            option3(fleetManager, sc);
         } else if (option == 4) {
            option4(fleetManager, sc);
         } else if (option == 5) {
            option5(fleetManager, sc);
         } else if (option == 6) {
            option6(fleetManager);
         } else if (option == 7) {
            option7(fleetManager, sc);
         } else if (option == 8) {
            option8(fleetManager, sc);
         } else if (option == 9) {
            option9(fleetManager, sc);
         } else if (option == 10) {
            option10(fleetManager, sc);
         } else if (option == 11) {
            System.out.println("Exiting ......."); break;
         } else {
            System.out.println("Invalid input");
         }
      }
      sc.close();
   }
}
