package neuefische.capstone.backend.security.storage;

import neuefische.capstone.backend.security.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends PagingAndSortingRepository<Role, String> {
    Role findByName(String role_admin);
}
