package EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.dto;

import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.entity.Role;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class UserResponseDTO {
    private String name;
    private String email;
    private List<RoleResponseDTO> roles;
    //what about token token will not be pass in the dto--token will go as part of header

    public static UserResponseDTO from(User user){
        if(user == null)
            return null;
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        //here we will not set as we are in the same entity
        userResponseDTO.name = user.getName();
        userResponseDTO.email = user.getEmailId();
        //userResponseDTO.token = user.getToken();
        userResponseDTO.roles = new ArrayList<>();
        //convert it to lambda stream
        for(Role role : user.getRoles()){
            RoleResponseDTO responseDTO = new RoleResponseDTO();
            responseDTO.setDesc(role.getDescription());
            responseDTO.setRole(role.getRoleName());
            //good way to add it
            userResponseDTO.roles.add(responseDTO);
        }
        return userResponseDTO;

    }
    //demo example
    public static User from(UserResponseDTO userResponseDTO){
        return null;
    }
}