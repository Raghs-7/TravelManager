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
Vehicle  VID:C001 added Successfully
Vehicle  VID:C002 added Successfully
Vehicle  VID:T001 added Successfully
Vehicle  VID:T002 added Successfully
Vehicle  VID:B001 added Successfully
Vehicle  VID:B002 added Successfully
Vehicle  VID:A001 added Successfully
Vehicle  VID:C003 added Successfully
Vehicle  VID:C004 added Successfully
Vehicle  VID:C005 added Successfully
Data Loaded from Demo.csv successfully


(2) Generate Initial Report
- Choose option: `6 (Generate Report)`

Expected Output:
Choose an option (1-11): 6
Total Vehicles: 10
By Type -> Cars:2 Trucks:2 Buses:2 Planes:1 Ships:3
Average Fuel Efficiency   : 8.30 km/l
Total Mileage             : 0.00 km
Vehicle Needs Maintenance : 0


Car
ID: C001, Model: HondaCivic, Max Speed: 180.00, Current Mileage: 0.00, Total Distance Traveled: 0.00, Number of Wheels: 4, Fuel Level: 50.00, Current Passengers: 3, Passenger Capacity: 5, Maintenance Needed: false

Car
ID: C002, Model: ToyotaCorolla, Max Speed: 170.00, Current Mileage: 0.00, Total Distance Traveled: 0.00, Number of Wheels: 4, Fuel Level: 45.00, Current Passengers: 4, Passenger Capacity: 5, Maintenance Needed: false

Truck
ID: T001, Model: VolvoTruck, Max Speed: 120.00, Current Mileage: 0.00, Total Distance Traveled: 0.00, Number of Wheels: 6, Fuel Level: 100.00, Current Cargo: 5000.00, Cargo Capacity: 500, Maintenance Needed: false

Truck
ID: T002, Model: ScaniaTruck, Max Speed: 110.00, Current Mileage: 0.00, Total Distance Traveled: 0.00, Number of Wheels: 8, Fuel Level: 120.00, Current Cargo: 8000.00, Cargo Capacity: 500, Maintenance Needed: false

Bus
ID: B001, Model: MercedesBus, Max Speed: 100.00, Current Mileage: 0.00, Total Distance Traveled: 0.00, Number of Wheels: 8, Fuel Level: 200.00, Current Passengers: 40, Passenger Capacity: 50, Current Cargo: 3000.00, Cargo Capacity: 500, Maintenance Needed: false

Bus
ID: B002, Model: VWBus, Max Speed: 90.00, Current Mileage: 0.00, Total Distance Traveled: 0.00, Number of Wheels: 6, Fuel Level: 180.00, Current Passengers: 30, Passenger Capacity: 50, Current Cargo: 2500.00, Cargo Capacity: 500, Maintenance Needed: false

AirPlane
ID: A001, Model: Boeing747, Max Speed: 900.00, Current Mileage: 0.00, Total Distance Traveled: 0.00, Max Altitude: 10000.00, Fuel Level: 10000.00, Current Passengers: 300, Passenger Capacity: 200, Current Cargo: 5000.00, Cargo Capacity: 10000, Maintenance Needed: false

CargoShip
ID: C003, Model: WindSail, Max Speed: 30.00, Current Mileage: 0.00, Total Distance Traveled: 0.00, Has Sail: true, Current Cargo: 0.00, Cargo Capacity: 50000, Maintenance Needed: false

CargoShip
ID: C004, Model: Maersk, Max Speed: 40.00, Current Mileage: 0.00, Total Distance Traveled: 0.00, Has Sail: false, Fuel Level: 5000.00, Current Cargo: 0.00, Cargo Capacity: 50000, Maintenance Needed: false

CargoShip
ID: C005, Model: HapagLloyd, Max Speed: 35.00, Current Mileage: 0.00, Total Distance Traveled: 0.00, Has Sail: false, Fuel Level: 4500.00, Current Cargo: 0.00, Cargo Capacity: 50000, Maintenance Needed: false


(3) Start Journey (Distance: 200.00)
- Choose option: `3 (Start Journey)`
- Enter distance: `200.00`

Expected Output:
Vehicle VID: C001, Driving on road ..200.0 km
Vehicle VID: C002, Driving on road ..200.0 km
Vehicle VID: T001, Hauling Cargo .. 200.0 km
Vehicle VID: T002, Hauling Cargo .. 200.0 km
Vehicle VID: B001, Transporting passengers and cargo ..200.0 km
Vehicle VID: B002, Transporting passengers and cargo ..200.0 km
Vehicle VID: A001, Flying at .. 10000.0 km
Vehicle VID: C003, Sailing with cargo ..200.0 km
Vehicle VID: C004, Sailing with cargo ..200.0 km
Vehicle VID: C005, Sailing with cargo ..200.0 km
Started all the journeys

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
Vehicle VID: C001, Driving on road ..100.0 km
Vehicle VID: C002, Driving on road ..100.0 km
Vehicle VID: T001, Hauling Cargo .. 100.0 km
Vehicle VID: T002, Hauling Cargo .. 100.0 km
Vehicle VID: B001, Transporting passengers and cargo ..100.0 km
Vehicle VID: B002, Transporting passengers and cargo ..100.0 km
Vehicle VID: A001, Flying at .. 10000.0 km
Vehicle VID: C003, Sailing with cargo ..100.0 km
Vehicle VID: C004, Sailing with cargo ..100.0 km
Vehicle VID: C005, Sailing with cargo ..100.0 km
Started all the journeys

(6) Generate Updated Report
- Choose option: `6 (Generate Report)`

Expected Output:
Total Vehicles: 10
By Type -> Cars:2 Trucks:2 Buses:2 Planes:1 Ships:3
Average Fuel Efficiency   : 8.30 km/l
Total Mileage             : 1000.00 km
Vehicle Needs Maintenance : 0


Car
ID: C001, Model: HondaCivic, Max Speed: 180.00, Current Mileage: 100.00, Total Distance Traveled: 300.00, Number of Wheels: 4, Fuel Level: 530.00, Current Passengers: 3, Passenger Capacity: 5, Maintenance Needed: false

Car
ID: C002, Model: ToyotaCorolla, Max Speed: 170.00, Current Mileage: 100.00, Total Distance Traveled: 300.00, Number of Wheels: 4, Fuel Level: 525.00, Current Passengers: 4, Passenger Capacity: 5, Maintenance Needed: false

Truck
ID: T001, Model: VolvoTruck, Max Speed: 120.00, Current Mileage: 100.00, Total Distance Traveled: 300.00, Number of Wheels: 6, Fuel Level: 558.75, Current Cargo: 5000.00, Cargo Capacity: 500, Maintenance Needed: false

Truck
ID: T002, Model: ScaniaTruck, Max Speed: 110.00, Current Mileage: 100.00, Total Distance Traveled: 300.00, Number of Wheels: 8, Fuel Level: 578.75, Current Cargo: 8000.00, Cargo Capacity: 500, Maintenance Needed: false

Bus
ID: B001, Model: MercedesBus, Max Speed: 100.00, Current Mileage: 100.00, Total Distance Traveled: 300.00, Number of Wheels: 8, Fuel Level: 670.00, Current Passengers: 40, Passenger Capacity: 50, Current Cargo: 3000.00, Cargo Capacity: 500, Maintenance Needed: false

Bus
ID: B002, Model: VWBus, Max Speed: 90.00, Current Mileage: 100.00, Total Distance Traveled: 300.00, Number of Wheels: 6, Fuel Level: 650.00, Current Passengers: 30, Passenger Capacity: 50, Current Cargo: 2500.00, Cargo Capacity: 500, Maintenance Needed: false

AirPlane
ID: A001, Model: Boeing747, Max Speed: 900.00, Current Mileage: 100.00, Total Distance Traveled: 300.00, Max Altitude: 10000.00, Fuel Level: 10440.00, Current Passengers: 300, Passenger Capacity: 200, Current Cargo: 5000.00, Cargo Capacity: 10000, Maintenance Needed: false

CargoShip
ID: C003, Model: WindSail, Max Speed: 30.00, Current Mileage: 100.00, Total Distance Traveled: 300.00, Has Sail: true, Current Cargo: 0.00, Cargo Capacity: 50000, Maintenance Needed: false

CargoShip
ID: C004, Model: Maersk, Max Speed: 40.00, Current Mileage: 100.00, Total Distance Traveled: 300.00, Has Sail: false, Fuel Level: 5425.00, Current Cargo: 0.00, Cargo Capacity: 50000, Maintenance Needed: false

CargoShip
ID: C005, Model: HapagLloyd, Max Speed: 35.00, Current Mileage: 100.00, Total Distance Traveled: 300.00, Has Sail: false, Fuel Level: 4925.00, Current Cargo: 0.00, Cargo Capacity: 50000, Maintenance Needed: false

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
ID: C001, Model: HondaCivic, Max Speed: 180.00, Current Mileage: 100.00, Total Distance Traveled: 300.00, Number of Wheels: 4, Fuel Level: 530.00, Current Passengers: 3, Passenger Capacity: 5, Maintenance Needed: false
Car
ID: C002, Model: ToyotaCorolla, Max Speed: 170.00, Current Mileage: 100.00, Total Distance Traveled: 300.00, Number of Wheels: 4, Fuel Level: 525.00, Current Passengers: 4, Passenger Capacity: 5, Maintenance Needed: false


(9) List Vehicles Needing Maintenance
- Choose option: `10 (List Vehicles Needing Maintenance)`

Expected Output:
No vehicles need maintenance.

(10) Remove Vehicle by ID "T001"
- Choose option: `2 (Remove Vehicle)`
- Enter ID: `T001`

Expected Output:
Vehicle removed successfully

(11) Exit Program
- Choose option: `11 (Exit)

Expected Output:
Exiting .......