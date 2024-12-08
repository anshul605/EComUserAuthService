package EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequestDTO {

    //destroy the token, clear the cookies, session
    //remove it, it will go in header
    private String token;
}
