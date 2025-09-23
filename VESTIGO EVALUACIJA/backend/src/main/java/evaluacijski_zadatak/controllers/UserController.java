package evaluacijski_zadatak.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import evaluacijski_zadatak.dtos.UserDto;
import evaluacijski_zadatak.services.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register (@RequestBody UserDto userDto){
        UserDto registeredUser = userService.register(userDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.login(userDto));
    }
    
}
