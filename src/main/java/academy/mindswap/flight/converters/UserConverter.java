package academy.mindswap.flight.converters;


import academy.mindswap.flight.commands.InsertUserDto;
import academy.mindswap.flight.commands.UserDto;
import academy.mindswap.flight.persistence.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public InsertUserDto convertToInsertDto(User user) {
        return modelMapper.map(user, InsertUserDto.class);
    }
    public User convertToEntity(InsertUserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

}
