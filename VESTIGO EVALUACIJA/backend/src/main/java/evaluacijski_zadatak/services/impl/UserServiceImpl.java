package evaluacijski_zadatak.services.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import evaluacijski_zadatak.dtos.UserDto;
import evaluacijski_zadatak.entities.User;
import evaluacijski_zadatak.exceptions.IllegalArgumentConflictException;
import evaluacijski_zadatak.mappers.UserMapper;
import evaluacijski_zadatak.repositories.UserRepository;
import evaluacijski_zadatak.services.JwtService;
import evaluacijski_zadatak.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private JwtService jwtService;
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository, JwtService jwtService){
        this.userRepository = userRepository;
        this.jwtService = jwtService;
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

        String token = jwtService.generateToken(user.getId(), user.getUsername());
        UserDto response = new UserDto();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setToken(token);
        return response;
    }
    
}
