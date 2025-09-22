package evaluacijski_zadatak.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class IllegalArgumentConflictException extends IllegalArgumentException{
    public IllegalArgumentConflictException(String message){
        super(message);
    }
}
