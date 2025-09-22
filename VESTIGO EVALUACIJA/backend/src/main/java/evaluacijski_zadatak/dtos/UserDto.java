package evaluacijski_zadatak.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String token;

    public UserDto(Long id, String username){
        this.id = id;
        this.username = username;
    }
    
    public UserDto(Long id, String username, String token){
        this.id = id;
        this.username = username;
        this.token = token;
    }
}
