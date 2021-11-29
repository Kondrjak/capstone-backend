package neuefische.capstone.backend.security.service;

import neuefische.capstone.backend.security.storage.PrivilegeRepo;
import neuefische.capstone.backend.security.model.Privilege;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PrivilegeService {

    private final PrivilegeRepo privilegeRepo;

    public PrivilegeService(PrivilegeRepo privilegeRepo) {
        this.privilegeRepo = privilegeRepo;
    }

    @Transactional
    public Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepo.findByName(name);
        if (privilege == null) {
            privilege = Privilege.builder().name(name).build();
            privilegeRepo.save(privilege);
        }
        return privilege;
    }
}
