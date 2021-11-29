package neuefische.capstone.backend.security.storage;

import neuefische.capstone.backend.security.model.Privilege;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepo extends PagingAndSortingRepository<Privilege, String> {
    Privilege findByName(String name);
}


