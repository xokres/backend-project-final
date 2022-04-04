package academy.mindswap.flight.exceptions;

public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException(String rolename){
        super(String.format("That particular role %s can't be found around here. Did he run away?", rolename));
    }
}
