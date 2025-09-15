package myException;

public class OverloadException extends Exception{
    public String toString(){
        return super.toString() + "Overload";
    }
}
