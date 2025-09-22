package evaluacijski_zadatak.services.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import evaluacijski_zadatak.dtos.UserDto;
import evaluacijski_zadatak.entities.User;
import evaluacijski_zadatak.exceptions.IllegalArgumentConflictException;
import evaluacijski_zadatak.mappers.UserMapper;
import evaluacijski_zadatak.repositories.UserRepository;
import evaluacijski_zadatak.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDto register(UserDto userDto) {
        if(userRepository.existsByUsername(userDto.getUsername())){
            throw new IllegalArgumentConflictException("Username already taken");
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPasswordHash(encoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto login(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername());
        if(user == null){
            throw new IllegalArgumentException("Invalid username or passwords");
        }

        if(!encoder.matches(userDto.getPassword(), user.getPasswordHash())){
            throw new IllegalArgumentException("Invalid username or password");

        }

        return new UserDto(user.getId(), user.getUsername());
    }
    
}
