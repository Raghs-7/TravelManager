package myException;

public class InvalidOperationException extends Exception {

    public String toString(){
        return super.toString() + "Invalid input";
    }
    
}
