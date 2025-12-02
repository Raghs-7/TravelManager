package Simulation;

import Vehicle.*;
import myException.*;

public class VehicleThread extends Thread {
    private Vehicle vehicle;
    private volatile boolean running = true;
    private volatile boolean paused = false;
    private HighwaySimulator simulator;
    private String status = "Ready";

    public VehicleThread(Vehicle vehicle, HighwaySimulator simulator) {
        this.vehicle = vehicle;
        this.simulator = simulator;
    }

    @Override
    public void run() {
        while (running) {
            if (!paused) {
                try {
                    // Check if vehicle has fuel (for fuel-consumable vehicles)
                    if (vehicle instanceof Car) {
                        Car car = (Car) vehicle;
                        if (car.getFuelLevel() <= 0) {
                            status = "Out-of-Fuel";
                            paused = true;
                            continue;
                        }
                    } else if (vehicle instanceof Truck) {
                        Truck truck = (Truck) vehicle;
                        if (truck.getFuelLevel() <= 0) {
                            status = "Out-of-Fuel";
                            paused = true;
                            continue;
                        }
                    } else if (vehicle instanceof Bus) {
                        Bus bus = (Bus) vehicle;
                        if (bus.getFuelLevel() <= 0) {
                            status = "Out-of-Fuel";
                            paused = true;
                            continue;
                        }
                    } else if (vehicle instanceof AirPlane) {
                        AirPlane plane = (AirPlane) vehicle;
                        if (plane.getFuelLevel() <= 0) {
                            status = "Out-of-Fuel";
                            paused = true;
                            continue;
                        }
                    } else if (vehicle instanceof CargoShipFuel) {
                        CargoShipFuel ship = (CargoShipFuel) vehicle;
                        if (ship.getFuelLevel() <= 0) {
                            status = "Out-of-Fuel";
                            paused = true;
                            continue;
                        }
                    }

                    // Move the vehicle 1 km
                    status = "Running";
                    vehicle.move(1.0);

                    // Update shared highway counter (UNSYNCHRONIZED - will cause race condition)
                    simulator.incrementHighwayDistance(1);

                    Thread.sleep(1000); // 1 second delay

                } catch (InsufficientFuelException e) {
                    status = "Out-of-Fuel";
                    paused = true;
                } catch (Exception e) {
                    status = "Error: " + e.getMessage();
                }
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    public void pauseVehicle() {
        this.paused = true;
        if (!"Out-of-Fuel".equals(status)) {
            status = "Paused";
        }
    }

    public void resumeVehicle() {
        if ("Out-of-Fuel".equals(status)) {
            // Don't resume if out of fuel
            return;
        }
        this.paused = false;
        status = "Running";
    }

    public void stopVehicle() {
        this.running = false;
        status = "Stopped";
    }

    public boolean isPaused() {
        return paused;
    }

    public String getStatus() {
        return status;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void refuelVehicle(double amount) {
        try {
            if (vehicle instanceof Car) {
                ((Car) vehicle).refuel(amount);
            } else if (vehicle instanceof Truck) {
                ((Truck) vehicle).refuel(amount);
            } else if (vehicle instanceof Bus) {
                ((Bus) vehicle).refuel(amount);
            } else if (vehicle instanceof AirPlane) {
                ((AirPlane) vehicle).refuel(amount);
            } else if (vehicle instanceof CargoShipFuel) {
                ((CargoShipFuel) vehicle).refuel(amount);
            }

            if ("Out-of-Fuel".equals(status)) {
                paused = false;
                status = "Running";
            }
        } catch (Exception e) {
            status = "Error: " + e.getMessage();
        }
    }
}