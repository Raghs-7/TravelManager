package Interface;

import myException.*;

public interface CargoCarrier {
    void loadCargo(double weight) throws OverloadException;
    void unloadCargo(double weight) throws OverloadException;
    double getCargoCapacity();
    double getCurrentCargo();
}
