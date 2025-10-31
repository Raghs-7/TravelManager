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
(type, ID, model, speed, Specialized)

Notes:
- I have implemented unique through TreeSet
- `id` is a unique identifier.
- `model` is a string, e.g., "VolvoTruck".
- 'speed' is double
- 'Specialized' depends on type of vehicle you are adding 

Example Entry for Car:
Car,C001,HondaCivic,180,4

--------------------------------------------------------------------------------------------------------------


- Example Walkthrough --->

(1) Load Fleet from File
- Choose option: `8 (Load Fleet)`
- Enter filename: `fleetdata.csv`

Expected Output:
Vehicle  VID:C001 added Successfully
Loaded vehicle from line No. 1
Vehicle  VID:T001 added Successfully
Loaded vehicle from line No. 2
Vehicle  VID:B001 added Successfully
Loaded vehicle from line No. 3
Vehicle  VID:A001 added Successfully
Loaded vehicle from line No. 4
Invalid number format in line No. 5
Invalid number format in line No. 6
Invalid data in line No. 7
Invalid data in line No. 8
Invalid number format in line No. 9
Invalid number format in line No. 10
Invalid number format in line No. 11
Loaded vehicle from line No. 12
Invalid number format in line No. 13
Invalid number format in line No. 14
Vehicle  VID:C004 added Successfully
Loaded vehicle from line No. 15
Data Loaded from fleetdata.csv successfully


(2) Generate Initial Report
- Choose option: `6 (Generate Report)`

Expected Output:
Total Vehicles: 5
By Type -> Cars:2 Trucks:1 Buses:1 Planes:1 Ships:0
Vehicle Needs Maintenance : 0
Fastest Vehicle: 900.00
Slowest Vehicle: 175.00

AirPlane
ID: A001, Model: Boeing747, Max Speed: 900.00, Current Mileage: 0.00, Total Distance Traveled: 0.00, Max Altitude: 10000.00, Fuel Level: 0.00, Current Passengers: 0, Passenger Capacity: 200, Current Cargo: 0.00, Cargo Capacity: 10000, Maintenance Needed: false

Truck
ID: T001, Model: VolvoFH, Max Speed: 120.00, Current Mileage: 0.00, Total Distance Traveled: 0.00, Number of Wheels: 6, Fuel Level: 0.00, Current Cargo: 0.00, Cargo Capacity: 500, Maintenance Needed: false

Bus
ID: B001, Model: TataBus, Max Speed: 100.00, Current Mileage: 0.00, Total Distance Traveled: 0.00, Number of Wheels: 8, Fuel Level: 0.00, Current Passengers: 0, Passenger Capacity: 50, Current Cargo: 0.00, Cargo Capacity: 500, Maintenance Needed: false

Car
ID: C001, Model: HondaCivic, Max Speed: 180.00, Current Mileage: 0.00, Total Distance Traveled: 0.00, Number of Wheels: 4, Fuel Level: 0.00, Current Passengers: 0, Passenger Capacity: 4, Maintenance Needed: false

Car
ID: C004, Model: ToyotaCorolla, Max Speed: 175.00, Current Mileage: 0.00, Total Distance Traveled: 0.00, Number of Wheels: 4, Fuel Level: 0.00, Current Passengers: 0, Passenger Capacity: 4, Maintenance Needed: false


(3) Start Journey (Distance: 200.00)
- Choose option: `3 (Start Journey)`
- Enter distance: `200.00`

Expected Output:
A001 ID Vehicle don't have enough Fuel
T001 ID Vehicle don't have enough Fuel
B001 ID Vehicle don't have enough Fuel
C001 ID Vehicle don't have enough Fuel
C004 ID Vehicle don't have enough Fuel

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
Successfully performed Maintenance

(5) Start Journey (Distance: 100.00)
- Choose option: `3 (Start Journey)`
- Enter distance: `100.00`

Expected Output:
Vehicle VID: A001, Flying at .. 10000.0 km
Vehicle VID: T001, Hauling Cargo .. 100.0 km
Vehicle VID: B001, Transporting passengers and cargo ..100.0 km
Vehicle VID: C001, Driving on road ..100.0 km
Vehicle VID: C004, Driving on road ..100.0 km

(6) Generate Updated Report
- Choose option: `6 (Generate Report)`

Expected Output:
Total Vehicles: 5
By Type -> Cars:2 Trucks:1 Buses:1 Planes:1 Ships:0
Vehicle Needs Maintenance : 0
Fastest Vehicle: 900.00
Slowest Vehicle: 175.00


AirPlane
ID: A001, Model: Boeing747, Max Speed: 900.00, Current Mileage: 100.00, Total Distance Traveled: 100.00, Max Altitude: 10000.00, Fuel Level: 480.00, Current Passengers: 0, Passenger Capacity: 200, Current Cargo: 0.00, Cargo Capacity: 10000, Maintenance Needed: false

Truck
ID: T001, Model: VolvoFH, Max Speed: 120.00, Current Mileage: 100.00, Total Distance Traveled: 100.00, Number of Wheels: 6, Fuel Level: 487.50, Current Cargo: 0.00, Cargo Capacity: 500, Maintenance Needed: false

Bus
ID: B001, Model: TataBus, Max Speed: 100.00, Current Mileage: 100.00, Total Distance Traveled: 100.00, Number of Wheels: 8, Fuel Level: 490.00, Current Passengers: 0, Passenger Capacity: 50, Current Cargo: 0.00, Cargo Capacity: 500, Maintenance Needed: false

Car
ID: C001, Model: HondaCivic, Max Speed: 180.00, Current Mileage: 100.00, Total Distance Traveled: 100.00, Number of Wheels: 4, Fuel Level: 493.33, Current Passengers: 0, Passenger Capacity: 4, Maintenance Needed: false

Car
ID: C004, Model: ToyotaCorolla, Max Speed: 175.00, Current Mileage: 100.00, Total Distance Traveled: 100.00, Number of Wheels: 4, Fuel Level: 493.33, Current Passengers: 0, Passenger Capacity: 4, Maintenance Needed: false

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
ID: C001, Model: HondaCivic, Max Speed: 180.00, Current Mileage: 100.00, Total Distance Traveled: 100.00, Number of Wheels: 4, Fuel Level: 493.33, Current Passengers: 0, Passenger Capacity: 4, Maintenance Needed: false
Car
ID: C004, Model: ToyotaCorolla, Max Speed: 175.00, Current Mileage: 100.00, Total Distance Traveled: 100.00, Number of Wheels: 4, Fuel Level: 493.33, Current Passengers: 0, Passenger Capacity: 4, Maintenance Needed: false

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