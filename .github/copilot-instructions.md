# Copilot Instructions for Transportation Fleet Management System

## Project Overview
This is a Java-based Transportation Fleet Management System. It models various vehicle types (land, air, water) and manages them via a CLI-driven FleetManager. The system supports loading/saving fleet data, running journeys, refueling, maintenance, and reporting.

## Architecture & Key Components
- **Abstract Classes**: `Vehicle` (base), `LandVehicle`, `AirVehicle`, `WaterVehicle` (specialized).
- **Concrete Classes**: `Car`, `Truck`, `Bus`, `AirPlane`, `CargoShipFuel`, `CargoShipSail` (in `Vehicle/`).
- **Interfaces**: `FuelConsumable`, `CargoCarrier`, `PassengerCarrier`, `Maintainable` (in `Interface/`).
- **FleetManager**: Central class for managing the fleet, located in `FleetManager/FleetManager.java`. Uses `List<Vehicle>` for polymorphic operations.
- **Custom Exceptions**: Defined in `myException/` for domain-specific error handling.

## Developer Workflows
- **Build**: Compile all sources with:
  ```
  javac Abstract/*.java Interface/*.java myException/*.java Vehicle/*.java *.java
  ```
- **Run**: Execute the CLI via `Main.java`.
- **Persistence**: Use CLI options to load/save fleet data from/to CSV files (see sample format in `README.txt`).
- **Testing**: Manual via CLI walkthrough (see example flows in `README.txt`). No automated test suite present.

## Project-Specific Patterns
- **Polymorphism**: All vehicle operations (move, refuel, maintenance) are performed via the base `Vehicle` reference in FleetManager.
- **Interfaces**: Only relevant vehicles implement specific interfaces (e.g., only ships implement `CargoCarrier`).
- **Input Format**: CSV, with strict field order per vehicle type (see `README.txt`).
- **Error Handling**: Uses custom exceptions for invalid operations, overloads, and insufficient fuel.
- **Reporting**: Fleet statistics and search features are CLI-driven.

## Integration Points
- **File I/O**: Fleet data is loaded/saved as CSV. Use CLI options for these actions.
- **No external dependencies**: Pure Java, no third-party libraries.

## Conventions & Tips
- Keep new vehicle types consistent with the abstract/interface structure.
- Update CLI menu and FleetManager logic when adding new features.
- Reference `README.txt` for input/output formats and example flows.
- Exception classes should extend Java's Exception and reside in `myException/`.

## Key Files & Directories
- `Abstract/` — Abstract vehicle classes
- `Vehicle/` — Concrete vehicle implementations
- `Interface/` — Behavior interfaces
- `FleetManager/` — Fleet management logic
- `myException/` — Custom exceptions
- `README.txt` — Architecture, workflow, and data format reference

---
For questions or unclear conventions, review `README.txt` or ask for clarification.
