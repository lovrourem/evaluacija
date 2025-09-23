package evaluacijski_zadatak.mappers;

import evaluacijski_zadatak.dtos.UserDto;
import evaluacijski_zadatak.entities.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user){
        return new UserDto(
            user.getId(),
            user.getUsername()
        );
    }

}
