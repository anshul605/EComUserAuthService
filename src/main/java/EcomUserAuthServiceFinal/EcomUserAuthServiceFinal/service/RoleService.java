package EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.service;

import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.dto.RoleRequestDTO;
import EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.dto.RoleResponseDTO;

public interface RoleService {

    RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO);
}
