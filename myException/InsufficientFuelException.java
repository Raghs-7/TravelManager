package myException;

public class InsufficientFuelException extends Exception{
    public String toString(){
        return super.toString() + "Insufficient Fuel";
    }
    
}
