package Interface;

import myException.*;

public interface FuelConsumable {
    void refuel(double amount) throws InvalidOperationException;
    double getFuelLevel();
    double consumeFuel(double distance) throws InsufficientFuelException;
}