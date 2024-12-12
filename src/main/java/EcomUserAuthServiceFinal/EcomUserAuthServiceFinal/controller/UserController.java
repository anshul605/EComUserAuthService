package EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.controller;

import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.dto.LoginRequestDTO;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.dto.SignUpRequestDTO;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.dto.UserResponseDTO;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.exception.RoleNotFoundExceptions;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(userService.login(loginRequestDTO));
    }

    @GetMapping("/logout")
    //remove the token
    //this how you request header
    public ResponseEntity logout(@RequestHeader("Authorisation") String authToken){
        return ResponseEntity.ok(userService.logout(authToken));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signup(@RequestBody SignUpRequestDTO signupRequestDTO) throws RoleNotFoundExceptions {
        return ResponseEntity.ok(userService.signup(signupRequestDTO));
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestHeader("Authorisation") String authToken){
        return ResponseEntity.ok(userService.validateToken(authToken));
    }
}
