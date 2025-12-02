# Fleet Highway Simulator - Assignment 3
## Multithreading and GUI with Race Condition Handling

---

## Table of Contents
1. [Overview](#overview)
2. [Features Implemented](#features-implemented)
3. [System Architecture](#system-architecture)
4. [Compilation and Execution](#compilation-and-execution)
5. [GUI Layout and Controls](#gui-layout-and-controls)
6. [Race Condition Demonstration](#race-condition-demonstration)
7. [Synchronization Fix](#synchronization-fix)
8. [Thread Safety Considerations](#thread-safety-considerations)
9. [Screenshots and Evidence](#screenshots-and-evidence)
10. [Project Structure](#project-structure)

---

## Overview

This project extends the Fleet Management System (Assignments 1 & 2) into a **multithreaded graphical simulation** of vehicles traveling on a highway. The system demonstrates:

- **Concurrent execution** of multiple vehicle threads
- **Race condition** caused by unsynchronized access to shared resources
- **Synchronization techniques** to fix race conditions
- **GUI-based control** using Java Swing
- **Real-time visualization** of vehicle states

The simulator runs at least **5 vehicles** concurrently, each updating its state every second while contributing to a shared highway distance counter.

---

## Features Implemented

### ✅ R1: Multithreaded Simulation
- Each vehicle runs in its own thread (`VehicleThread.java`)
- Updates occur approximately once per second (1 km per second)
- Each vehicle independently tracks mileage and fuel consumption

### ✅ R2: Shared Highway Counter
- All vehicles update a common `highwayDistance` counter
- Initially implemented **without synchronization** to cause race condition
- Counter accessible through `HighwaySimulator` class

### ✅ R3: Race Condition Demonstration and Fix
- **Unsynchronized Mode**: Race condition visible when multiple threads update shared counter
- **Synchronized Mode**: Two synchronization mechanisms implemented:
    - `synchronized` keyword (method-level synchronization)
    - `ReentrantLock` (explicit locking mechanism)
- Toggle between modes via GUI button

### ✅ R4: Graphical User Interface
Built using **Java Swing** with the following controls:

**Control Buttons:**
- **Start Simulation**: Begin all vehicle threads
- **Pause All**: Pause all running vehicles
- **Resume All**: Resume paused vehicles
- **Stop Simulation**: Stop all threads permanently
- **Toggle Synchronization**: Switch between unsafe and safe modes
- **Reset Simulation**: Clear all data and restart

**Real-time Display:**
- Individual vehicle panels showing:
    - Vehicle ID, Model, Max Speed
    - Current Status (Running, Paused, Out-of-Fuel, Stopped)
    - Mileage traveled
    - Fuel level (color-coded)
    - Refuel button for each vehicle

**Statistics Panel:**
- Shared Highway Distance Counter (from all threads)
- Total Individual Mileage (sum of all vehicle mileage)
- Synchronization Status indicator
- **Discrepancy highlighting** when race condition occurs

### ✅ R5: Integration with Previous Work
- Reuses complete class hierarchy from Assignments 1 & 2
- Vehicle classes: `Car`, `Truck`, `Bus`, `AirPlane`, `CargoShipFuel`, `CargoShipSail`
- Abstract classes: `Vehicle`, `LandVehicle`, `AirVehicle`, `WaterVehicle`
- Interfaces: `FuelConsumable`, `PassengerCarrier`, `CargoCarrier`, `Maintainable`

---

## System Architecture

```
┌─────────────────────────────────────────────────────────┐
│              FleetSimulatorGUI (Swing)                  │
│  ┌────────────────────────────────────────────────┐    │
│  │  Control Panel (Start/Pause/Resume/Stop/Sync)  │    │
│  └────────────────────────────────────────────────┘    │
│  ┌────────────────────────────────────────────────┐    │
│  │   Vehicle Display Area (Real-time Updates)     │    │
│  │  ┌──────────────────────────────────────────┐  │    │
│  │  │ Vehicle 1: Car - Status, Mileage, Fuel  │  │    │
│  │  │ Vehicle 2: Car - Status, Mileage, Fuel  │  │    │
│  │  │ Vehicle 3: Truck - Status, Mileage, Fuel│  │    │
│  │  │ Vehicle 4: Bus - Status, Mileage, Fuel  │  │    │
│  │  │ Vehicle 5: Plane - Status, Mileage, Fuel│  │    │
│  │  └──────────────────────────────────────────┘  │    │
│  └────────────────────────────────────────────────┘    │
│  ┌────────────────────────────────────────────────┐    │
│  │  Statistics Panel                              │    │
│  │  - Shared Highway Distance: XXX km             │    │
│  │  - Total Individual Mileage: YYY km            │    │
│  │  - Synchronization Status                      │    │
│  └────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────┘
                          │
                          │ Creates & Controls
                          ▼
        ┌─────────────────────────────────────┐
        │      VehicleThread 1 (Thread)       │
        │      VehicleThread 2 (Thread)       │
        │      VehicleThread 3 (Thread)       │◄──┐
        │      VehicleThread 4 (Thread)       │   │
        │      VehicleThread 5 (Thread)       │   │ Update
        └─────────────────────────────────────┘   │ Shared
                          │                         │ Counter
                          │ Updates                 │
                          ▼                         │
        ┌─────────────────────────────────────┐   │
        │      HighwaySimulator               │   │
        │  - highwayDistance (shared)         │───┘
        │  - incrementHighwayDistance()       │
        │  - Synchronization control          │
        └─────────────────────────────────────┘
```

---

## Compilation and Execution

### Prerequisites
- **Java Development Kit (JDK)**: Version 8 or higher
- **Operating System**: Windows, macOS, or Linux

### Step 1: Extract the Project
Extract the ZIP file to a directory, e.g., `FleetSimulator/`

### Step 2: Navigate to Source Directory
```bash
cd FleetSimulator/src
```

### Step 3: Compile All Files
```bash
javac Abstract/*.java Interface/*.java myException/*.java Vehicle/*.java FleetManager/*.java Simulation/*.java GUI/*.java
```

### Step 4: Run the GUI Application
```bash
java GUI.FleetSimulatorGUI
```

### Alternative: Using an IDE
1. Import the project into **Eclipse**, **IntelliJ IDEA**, or **NetBeans**
2. Set `GUI.FleetSimulatorGUI` as the main class
3. Run the project

---

## GUI Layout and Controls

### Main Window Components

#### 1. Control Panel (Top)
Located at the top of the window with two rows:

**Row 1 - Simulation Controls:**
- **Start Simulation** (Green): Starts all vehicle threads
- **Pause All** (Orange): Pauses all vehicles without stopping threads
- **Resume All** (Blue): Resumes paused vehicles
- **Stop Simulation** (Red): Stops all threads permanently

**Row 2 - Configuration:**
- **Enable/Disable Synchronization**: Toggles between unsafe and safe modes
- **Reset Simulation**: Resets all counters and recreates vehicles

#### 2. Vehicle Display Area (Center)
Scrollable panel showing real-time status of all vehicles:

Each vehicle panel displays:
- **Vehicle Type and ID**: e.g., "Car - V001"
- **Model and Max Speed**: e.g., "Honda Civic | 180 km/h"
- **Status**: Running, Paused, Out-of-Fuel, Stopped
- **Mileage**: Current distance traveled (e.g., "156.0 km")
- **Fuel Level**: Remaining fuel with color coding:
    - Green: >20 liters
    - Orange: 1-20 liters
    - Red: 0 liters (Out-of-Fuel)
- **Individual Control Buttons**:
    - **⛽ Refuel**: Opens dialog to enter custom fuel amount
    - **⏸ Pause**: Pauses this specific vehicle
    - **▶ Resume**: Resumes this specific vehicle
    - **⏹ Stop**: Permanently stops this specific vehicle

**Background Color Coding:**
- Light Green: Vehicle running
- Light Yellow: Vehicle paused
- Light Red: Vehicle out of fuel
- Light Gray: Vehicle stopped

#### 3. Statistics Panel (Bottom)
Displays three key metrics:

- **Shared Highway Distance**: Total kilometers from shared counter
    - Turns RED when discrepancy detected (race condition)
- **Total Individual Mileage**: Sum of all individual vehicle mileages
    - Turns RED when discrepancy detected
- **Synchronization Status**:
    - RED: "DISABLED (Race Condition Active)"
    - GREEN: "ENABLED (Race Condition Fixed)"

---

## Race Condition Demonstration

### What is the Race Condition?

A **race condition** occurs when multiple threads access and modify a shared resource concurrently without proper synchronization, leading to unpredictable and incorrect results.

### How It Occurs in This System

1. **Shared Resource**: `highwayDistance` counter (static integer)
2. **Multiple Threads**: 5 vehicle threads running concurrently
3. **Concurrent Updates**: Each thread increments the counter every second

### The Problem (Without Synchronization)

When synchronization is **DISABLED**, the following happens:

```java
// Thread 1 reads: highwayDistance = 100
// Thread 2 reads: highwayDistance = 100 (before Thread 1 writes)
// Thread 1 writes: highwayDistance = 101
// Thread 2 writes: highwayDistance = 101 (overwrites Thread 1's update!)
// Expected: 102, Actual: 101 (Lost Update!)
```

### Observable Symptoms

1. **Discrepancy in Counters**:
    - Shared Highway Distance: 453 km
    - Total Individual Mileage: 500 km
    - **Difference**: 47 km lost due to race condition!

2. **Visual Indicators**:
    - Both counters turn RED when discrepancy exceeds 5 km
    - The gap widens over time
    - Non-deterministic behavior (different results each run)

### Steps to Observe Race Condition

1. Launch the application
2. Ensure synchronization is **DISABLED** (red status)
3. Click **Start Simulation**
4. Watch the statistics panel for 30-60 seconds
5. Observe growing discrepancy between:
    - Shared Highway Distance (lower than expected)
    - Total Individual Mileage (accurate sum)
6. **Take Screenshot** showing the discrepancy

### Example Evidence (Before Fix)

```
Shared Highway Distance: 453 km (RED)
Total Individual Mileage: 500 km (RED)
Synchronization: DISABLED (Race Condition Active) (RED)

Discrepancy: 47 km lost due to concurrent unsynchronized updates
```

---

## Synchronization Fix

### Solution 1: Using `synchronized` Keyword

**Implementation** (in `HighwaySimulator.java`):

```java
public synchronized void incrementHighwayDistanceSynchronized(int distance) {
    highwayDistance += distance;
}
```

**How It Works:**
- Only ONE thread can execute this method at a time
- Other threads wait in queue
- Mutual exclusion guarantees atomic updates
- Simple and efficient for this use case

### Solution 2: Using `ReentrantLock`

**Implementation** (in `HighwaySimulator.java`):

```java
private ReentrantLock lock = new ReentrantLock();

public void incrementHighwayDistanceLocked(int distance) {
    lock.lock();
    try {
        highwayDistance += distance;
    } finally {
        lock.unlock();
    }
}
```

**How It Works:**
- Explicit lock acquisition and release
- `try-finally` ensures lock is always released
- More flexible than synchronized (supports timeouts, fairness policies)
- Preferred for complex synchronization scenarios

### Toggle Between Modes

The system allows runtime switching:

```java
public void incrementHighwayDistance(int distance) {
    if (useSynchronization) {
        incrementHighwayDistanceSynchronized(distance);
    } else {
        incrementHighwayDistanceUnsafe(distance);
    }
}
```

### Steps to Verify Fix

1. Observe race condition (as described above)
2. Click **"Enable Synchronization"** button
3. Button turns GREEN, status shows "ENABLED"
4. Continue watching statistics panel
5. Observe that counters now match:
    - Shared Highway Distance: 500 km
    - Total Individual Mileage: 500 km
    - **No discrepancy!**
6. **Take Screenshot** showing correct behavior

### Example Evidence (After Fix)

```
Shared Highway Distance: 500 km (GREEN)
Total Individual Mileage: 500 km (BLUE)
Synchronization: ENABLED (Race Condition Fixed) (GREEN)

Discrepancy: 0 km - Perfect synchronization!
```

---

## Thread Safety Considerations

### 1. Event Dispatch Thread (EDT)

**Problem**: Swing components are **not thread-safe**. All GUI updates must occur on the EDT.

**Solution Implemented**:
- GUI initialization uses `SwingUtilities.invokeLater()`
- Timer-based updates run on EDT automatically
- No direct GUI manipulation from vehicle threads

```java
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        FleetSimulatorGUI gui = new FleetSimulatorGUI();
        gui.setVisible(true);
    });
}
```

### 2. Volatile Variables

**Usage** in `VehicleThread.java`:

```java
private volatile boolean running = true;
private volatile boolean paused = false;
```

**Why Volatile?**
- Ensures visibility of changes across threads
- Prevents caching in thread-local memory
- Critical for control flags (pause/resume/stop)

### 3. Timer-Based Updates

**Implementation**:
```java
private Timer updateTimer = new Timer(500, e -> updateDisplay());
```

**Benefits**:
- Runs on EDT automatically
- Periodic GUI updates (every 500ms)
- Decouples vehicle threads from GUI updates
- Prevents excessive repainting

### 4. Thread Lifecycle Management

**Proper Shutdown**:
- `stopVehicle()` sets running flag to false
- Threads exit gracefully from run() loop
- No forced thread interruption
- Clean resource cleanup

### 5. Atomic Operations

**Shared Counter Updates**:
- Critical section protected by synchronization
- Prevents partial reads/writes
- Guarantees atomicity of increment operations

---

## Screenshots and Evidence

### Required Screenshots

#### 1. Race Condition (Before Fix)
**Filename**: `race_condition_before.png`

**What to Show**:
- Synchronization DISABLED (red status)
- Visible discrepancy between counters (RED indicators)
- Multiple vehicles running
- Timestamp showing simulation running for 30+ seconds

#### 2. Synchronized Operation (After Fix)
**Filename**: `race_condition_after.png`

**What to Show**:
- Synchronization ENABLED (green status)
- Counters matching perfectly
- Same vehicles still running
- Demonstrates fix working correctly

#### 3. Vehicle Refueling with Custom Amount
**Filename**: `vehicle_refuel.png`

**What to Show**:
- At least one vehicle with "Out-of-Fuel" status
- Vehicle panel highlighted in red
- Dialog box showing custom fuel amount input
- Other vehicles still running

#### 4. Individual Vehicle Control
**Filename**: `individual_control.png`

**What to Show**:
- Different vehicles in different states:
    - One or more running (green background)
    - One paused (yellow background)
    - One stopped (gray background)
- Individual control buttons visible on each panel
- Demonstrates independent vehicle control

#### 5. Global Pause/Resume Functionality (Optional)
**Filename**: `global_pause_resume.png`

**What to Show**:
- All vehicles paused simultaneously (yellow backgrounds)
- Global "Pause All" button pressed
- Statistics panel still updating
- Demonstrates master control functionality

### How to Capture Screenshots

1. **Windows**: Press `Win + Shift + S` or use Snipping Tool
2. **macOS**: Press `Cmd + Shift + 4`
3. **Linux**: Press `PrtSc` or use Screenshot tool

Place all screenshots in a `screenshots/` folder in the submission.

---

## Project Structure

```
FleetSimulator/
│
├── src/
│   ├── Abstract/
│   │   ├── AirVehicle.java
│   │   ├── LandVehicle.java
│   │   └── WaterVehicle.java
│   │
│   ├── Interface/
│   │   ├── CargoCarrier.java
│   │   ├── FuelConsumable.java
│   │   ├── Maintainable.java
│   │   └── PassengerCarrier.java
│   │
│   ├── Vehicle/
│   │   ├── Vehicle.java (abstract base)
│   │   ├── Car.java
│   │   ├── Truck.java
│   │   ├── Bus.java
│   │   ├── AirPlane.java
│   │   ├── CargoShipFuel.java
│   │   └── CargoShipSail.java
│   │
│   ├── myException/
│   │   ├── InsufficientFuelException.java
│   │   ├── InvalidOperationException.java
│   │   └── OverloadException.java
│   │
│   ├── FleetManager/
│   │   └── FleetManager.java (from Assignment 1 & 2)
│   │
│   ├── Simulation/ (NEW)
│   │   ├── HighwaySimulator.java
│   │   └── VehicleThread.java
│   │
│   ├── GUI/ (NEW)
│   │   └── FleetSimulatorGUI.java
│   │
│   └── Main.java (original CLI - still functional)
│
├── screenshots/ (to be added)
│   ├── race_condition_before.png
│   ├── race_condition_after.png
│   ├── vehicle_refuel.png
│   └── pause_resume.png
│
└── README.md (this file)
```

---

## Key Implementation Details

### VehicleThread.java
- Extends `Thread` class
- Contains `run()` method with main simulation loop
- Manages vehicle state (running, paused, out-of-fuel)
- Updates vehicle mileage and fuel
- Calls `simulator.incrementHighwayDistance()`
- Handles fuel exhaustion and refueling

### HighwaySimulator.java
- Manages shared `highwayDistance` counter
- Provides three increment methods:
    - `incrementHighwayDistanceUnsafe()` - demonstrates race condition
    - `incrementHighwayDistanceSynchronized()` - uses synchronized
    - `incrementHighwayDistanceLocked()` - uses ReentrantLock
- Toggle flag to switch between synchronized/unsynchronized modes

### FleetSimulatorGUI.java
- Main GUI application window
- Creates and manages vehicle threads
- Provides control buttons (start, pause, resume, stop)
- Updates display via Timer (EDT-safe)
- Detects and highlights race condition discrepancies
- Inner class `VehiclePanel` for individual vehicle display

---

## Testing Procedure

### Test 1: Race Condition Visibility
1. Launch application
2. Ensure synchronization is DISABLED
3. Start simulation
4. Wait 30 seconds
5. **Expected**: Counters diverge, turn red
6. **Actual**: [Record observation]

### Test 2: Synchronization Fix
1. Enable synchronization
2. Reset simulation
3. Start simulation
4. Wait 30 seconds
5. **Expected**: Counters match perfectly
6. **Actual**: [Record observation]

### Test 3: Vehicle Refueling
1. Start simulation with low initial fuel
2. Wait for vehicles to run out of fuel
3. Observe "Out-of-Fuel" status
4. Click refuel button
5. **Expected**: Vehicle resumes automatically
6. **Actual**: [Record observation]

### Test 4: Pause/Resume
1. Start simulation
2. Click "Pause All"
3. **Expected**: All vehicles show "Paused" status
4. Click "Resume All"
5. **Expected**: All vehicles resume
6. **Actual**: [Record observation]

### Test 5: Stop and Reset
1. Start simulation
2. Let it run for 20 seconds
3. Click "Stop Simulation"
4. **Expected**: All threads terminate
5. Click "Reset Simulation"
6. **Expected**: Counters reset, new vehicles created
7. **Actual**: [Record observation]

---

## Known Issues and Limitations

1. **Thread Termination**: Stopped threads cannot be restarted (by design)
2. **Fuel Consumption Rate**: Fixed at ~6.67 liters per km (may need adjustment)
3. **Visual Representation**: No graphical animation (vehicles shown as panels only)
4. **Scrolling**: Required for more than 5-6 vehicles

---

## Future Enhancements

1. **Animated Vehicles**: Add moving graphics on a highway visual
2. **Variable Speed**: Allow users to set different speeds per vehicle
3. **Collision Detection**: Simulate vehicle interactions
4. **Advanced Metrics**: Track average speed, fuel efficiency over time
5. **Save/Load State**: Persist simulation state to file
6. **Network Mode**: Distributed simulation across multiple machines

---

## Conclusion

This project successfully demonstrates:
- **Multithreading** with concurrent vehicle operations
- **Race condition** caused by unsynchronized shared resource access
- **Synchronization techniques** to resolve concurrency issues
- **GUI development** using Java Swing
- **Thread-safe programming** practices
- **Integration** of previous OOP design

The system clearly shows the problem (race condition) and solution (synchronization), meeting all requirements of Assignment 3.

---

## Contact and Support

For questions or issues:
- Review this README thoroughly
- Check compilation steps
- Verify Java version (JDK 8+)
- Ensure all packages are in correct directories

---

**Assignment 3 - AP-M2025**  
**Fleet Highway Simulator**  
**Multithreading & GUI with Race Condition Handling**

---