package EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.service;

import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.dto.LoginRequestDTO;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.dto.SignUpRequestDTO;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.dto.UserResponseDTO;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.entity.Role;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.entity.User;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.exception.InvalidCredentialException;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.exception.RoleNotFoundExceptions;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.exception.UserNotFoundException;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.repository.RoleRepository;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public UserResponseDTO signup(SignUpRequestDTO signupRequestDTO) throws RoleNotFoundExceptions {
        Role role = roleRepository.findById(signupRequestDTO.getRoleId()).orElseThrow(
                () -> new RoleNotFoundExceptions("Role not found")
        );

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setName(signupRequestDTO.getName());
        user.setEmailId(signupRequestDTO.getEmail());
        user.setPassword(encoder.encode(signupRequestDTO.getPassword()));
        user.setRoles(List.of(role));

        return UserResponseDTO.from(userRepository.save(user));
    }

    @Override
    public UserResponseDTO login(LoginRequestDTO loginRequestDTO) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User savedUser = userRepository.findByEmailId(loginRequestDTO.getEmail()).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
        if(bCryptPasswordEncoder.matches(loginRequestDTO.getPassword(), savedUser.getPassword())){
            // will not write the security stuff write now. will generate the token for user
            String userData = savedUser.getEmailId() + savedUser.getPassword() + LocalDateTime.now();
            String token = bCryptPasswordEncoder.encode(userData);
            savedUser.setToken(token);
        } else {
            throw new InvalidCredentialException();
        }
        //how to return the token in header but we are in service. how we put in header if we have to return user data and send token as well
        //for time being set these parameter in the user respnse dto and saved in db as well
        savedUser = userRepository.save(savedUser);
        return UserResponseDTO.from(savedUser);
    }

    @Override
    public boolean validateToken(String token) {
        User savedUser = userRepository.findByToken(token).orElseThrow(
                () -> new InvalidCredentialException("Token is not valid")
        );
        return true;
    }

    @Override
    public boolean logout(String token) {
        //simply,for the time being if token exist in db and its valid
        //this will string comparison
        User savedUser = userRepository.findByToken(token).orElseThrow(
                () -> new InvalidCredentialException("Token is not valid")
        );
        savedUser.setToken(null);
        userRepository.save(savedUser);
        return true;
    }


}