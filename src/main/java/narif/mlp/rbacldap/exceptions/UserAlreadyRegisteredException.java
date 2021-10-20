package narif.mlp.rbacldap.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserAlreadyRegisteredException extends RuntimeException{

    public final static String genericMessage = "User Already Registered with the given Email ID.";

    public UserAlreadyRegisteredException() {
        this(genericMessage);
    }

    public UserAlreadyRegisteredException(String message) {
        super(message);
    }

}
