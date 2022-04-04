package academy.mindswap.flight.exceptions;

public class FlightNotFoundException extends RuntimeException{

    public FlightNotFoundException(String id){
        super(String.format("That particular flight %s can't be found around here. Did he run away?", id) );
    }
}
