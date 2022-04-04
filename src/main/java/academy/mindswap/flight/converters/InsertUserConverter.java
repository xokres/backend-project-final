package academy.mindswap.flight.converters;

import academy.mindswap.flight.commands.InsertUserDto;
import academy.mindswap.flight.persistence.models.User;
import org.modelmapper.ModelMapper;

public class InsertUserConverter {

    ModelMapper modelMapper = new ModelMapper();

    public InsertUserDto newUser (User user){
        return modelMapper.map(user, InsertUserDto.class);
    }

    public User newUser (InsertUserDto user){
        return modelMapper.map(user, User.class);
    }

}
