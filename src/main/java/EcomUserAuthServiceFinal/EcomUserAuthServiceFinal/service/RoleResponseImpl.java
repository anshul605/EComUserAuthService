package EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.service;

import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.dto.RoleRequestDTO;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.dto.RoleResponseDTO;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.entity.Role;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleResponseImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO) {
        Role role = new Role();
        role.setRoleName(roleRequestDTO.getRoleName());
        role.setDescription(roleRequestDTO.getDescription());
        return RoleResponseDTO.from(roleRepository.save(role));
    }
}
