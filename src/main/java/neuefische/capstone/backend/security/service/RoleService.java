package neuefische.capstone.backend.security.service;

import neuefische.capstone.backend.security.storage.RoleRepo;
import neuefische.capstone.backend.security.model.Privilege;
import neuefische.capstone.backend.security.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleService {
    private final PrivilegeService privilegeService;
    private final RoleRepo roleRepo;

    @Autowired
    public RoleService(PrivilegeService privilegeService, RoleRepo roleRepo) {
        this.privilegeService = privilegeService;
        this.roleRepo = roleRepo;
    }

    public Role createRoleIfNotFound(
            String name, List<Privilege> privileges) {

        Role role = roleRepo.findByName(name);
        if (role == null) {
            role = Role.builder()
                    .name(name)
                    .privileges(privileges)
                    .build();
            roleRepo.save(role);
        }
        return role;
    }

    @Transactional
    public Role createAdminRole() {
        Privilege readPrivilege
                = privilegeService.createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = privilegeService.createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        List<Privilege> adminPrivileges = List.of(readPrivilege, writePrivilege);
        return createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
    }

    public Role createUserRole() {
        Privilege readPrivilege
                = privilegeService.createPrivilegeIfNotFound("READ_PRIVILEGE");
        return createRoleIfNotFound("ROLE_USER", List.of(readPrivilege));
    }
}
