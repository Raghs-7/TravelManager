Transportation Fleet Management System

- Inheritance: 
    - The abstract class `Vehicle` is the base class.
    - Specialized abstract classes (first children of Vehicle class): `LandVehicle`, `AirVehicle`, and `WaterVehicle`.
    - Concrete classes: `Car`, `Truck`, `Bus`, `Airplane`, and `CargoShip` extend these abstract classes.

- Polymorphism: The `FleetManager` manages a list of vehicles using a collection of type `List<Vehicle>`. Fleet-wide operations like `move()`, `consumeFuel()`, and `performMaintenance()` are implemented polymorphically. 
         Method calls are resolved at runtime depending on the object type.

- Abstract Classes(`LandVehicle`,`AirVehicle`,`WaterVehicle`): Abstract methods such as `move(double distance)`, `calculateFuelEfficiency()`, and `estimateJourneyTime(double distance)` enforce implementation in subclasses.

- Interfaces: Four interfaces are implemented:
    - `FuelConsumable`
    - `CargoCarrier`
    - `PassengerCarrier`
    - `Maintainable`
  These interfaces provide modular behaviors and are implemented only by applicable concrete classes.
-----------------------------------------------------------------------------------------------------------

Instructions to Compile and Run --->

1. Open the terminal and navigate to the project source directory.

2. javac Abstract/*.java Interface/*.java myException/*.java Vehicle/*.java FleetManager/*.java *.java

---------------------------------------------------------------------------------------------------------

Input Data Format --->

The input file should be a CSV file where each line represents a Vehicle in the following format:

1. **Car**
   Car,id,model,maxSpeed,numWheels,fuelLevel,passengerCapacity,currentPassengers

2. **Truck**
   Truck,id,model,maxSpeed,numWheels,fuelLevel,currentCargo

3. **Bus**
   Bus,id,model,maxSpeed,numWheels,fuelLevel,currentPassengers,currentCargo

4. **AirPlane**
   AirPlane,id,model,maxSpeed,maxAltitude,fuelLevel,currentPassengers,currentCargo

5. **CargoShipSail**
   CargoShip,true,id,model,maxSpeed,currentCargo

6. **CargoShipFuel**
   CargoShip,false,id,model,maxSpeed,currentCargo,fuelLevel

Notes:
- `id` is a unique identifier.
- `model` is a string, e.g., "VolvoTruck".
- `maxSpeed`, `maxAltitude`, `fuelLevel`, and `currentCargo` are decimals (double).
- `numWheels`, `passengerCapacity`, and `currentPassengers` are integers.
- `hasSail` is boolean: true or false.

Example Entry for Car:
Car,C001,HondaCivic,180.0,4,50.0,5,3

--------------------------------------------------------------------------------------------------------------

- Example Walkthrough --->

(1) Load Fleet from File
- Choose option: `8 (Load Fleet)`
- Enter filename: `Demo.csv`

Expected Output:
Vehicle added Successfully
Vehicle added Successfully
Vehicle added Successfully
Vehicle added Successfully
Vehicle added Successfully
Vehicle added Successfully
Vehicle added Successfully
Vehicle added Successfully
Vehicle added Successfully
Vehicle added Successfully
Data Loaded from Demo.csv successfully


(2) Generate Initial Report
- Choose option: `6 (Generate Report)`

Expected Output:
Total Vehicles: 10
By Type -> Cars:2 Trucks:2 Buses:2 Planes:1 Ships:3 
Average Fuel Efficiency   : 8.30 km/l
Total Mileage             : 0.00 km
Vehicle Needs Maintenance : 0


(3) Start Journey (Distance: 200.00)
- Choose option: `3 (Start Journey)`
- Enter distance: `200.00`

Expected Output:
200.0 km Driving on road
200.0 km Driving on road
200.0 km, Hauling Cargo ..
200.0 km, Hauling Cargo ..
200.0 km, Transporting passengers and cargo ..
200.0 km, Transporting passengers and cargo ..
200.0 km, Flying at ..10000.0
200.0 km, Sailing with cargo ..
200.0 km, Sailing with cargo ..
200.0 km, Sailing with cargo ..

- Choose option: `4 (Refuel All)`
- Enter fuel amount: `500`

Expected Output:
All Vehicle are refueled successfully


(4) Perform Maintenance
- Choose option: `5 (Perform Maintenance)`

Expected Output:
Maintenance is Done 
Maintenance is Done
Maintenance is Done
Maintenance is Done
Maintenance is Done
Maintenance is Done
Maintenance is Done
Maintenance is Done
Maintenance is Done
Maintenance is Done
Successfully performed Maintenance

(5) Start Journey (Distance: 100.00)
- Choose option: `3 (Start Journey)`
- Enter distance: `100.00`

Expected Output:
100.0 km Driving on road
100.0 km Driving on road
100.0 km, Hauling Cargo ..
100.0 km, Hauling Cargo ..
100.0 km, Transporting passengers and cargo ..
100.0 km, Transporting passengers and cargo ..
100.0 km, Flying at ..10000.0
100.0 km, Sailing with cargo ..
100.0 km, Sailing with cargo ..
100.0 km, Sailing with cargo ..
Started all the journeys

(6) Generate Updated Report
- Choose option: `6 (Generate Report)`

Expected Output:
Total Vehicles: 10
By Type -> Cars:2 Trucks:2 Buses:2 Planes:1 Ships:3
Average Fuel Efficiency   : 8.30 km/l
Total Mileage             : 1000.00 km
Vehicle Needs Maintenance : 0

(7) Save Fleet to File
- Choose option: `7 (Save Fleet)`
- Enter filename: `output.csv`

Expected Output:
Data Saved to output.csv successfully


(8) Search Vehicles by Type "Car"
- Choose option: `9 (Search by Type)`
- Enter type: `Car`

Expected Output:
Car
ID: C001, Model: HondaCivic, Max Speed: 180.00, Current Mileage: 100.00, Total Distance Traveled: 300.00
Car
ID: C002, Model: ToyotaCorolla, Max Speed: 170.00, Current Mileage: 100.00, Total Distance Traveled: 300.00


(9) List Vehicles Needing Maintenance
- Choose option: `10 (List Vehicles Needing Maintenance)`

Expected Output:
No vehicles need maintenance.

(10) Remove Vehicle by ID "T001"
- Choose option: `2 (Remove Vehicle)`
- Enter ID: `T001`

Expected Output:
Vehicle removed successfully

(11) now choose option 11 and Exit 