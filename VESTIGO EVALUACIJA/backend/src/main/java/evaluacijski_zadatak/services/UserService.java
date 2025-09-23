package evaluacijski_zadatak.services;

import evaluacijski_zadatak.dtos.UserDto;

public interface UserService {
    UserDto register(UserDto userDto);
    UserDto login(UserDto userDto);
}
