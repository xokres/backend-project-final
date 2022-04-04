package academy.mindswap.flight.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id){
        super(String.format("That particular user %s can't be found around here. Did he run away?", id) );
    }
}
