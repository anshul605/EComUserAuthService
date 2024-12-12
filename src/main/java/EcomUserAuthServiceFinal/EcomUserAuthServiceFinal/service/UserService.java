package EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.service;

import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.dto.LoginRequestDTO;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.dto.SignUpRequestDTO;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.dto.UserResponseDTO;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.entity.Role;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.entity.User;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.exception.RoleNotFoundExceptions;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.exception.UserNotFoundException;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.repository.RoleRepository;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface UserService {

    UserResponseDTO signup(SignUpRequestDTO signupRequestDTO); //throws RoleNotFoundException;
    UserResponseDTO login(LoginRequestDTO loginRequestDTO);
    boolean validateToken(String token);
    boolean logout(String token);
}
