package neuefische.capstone.backend.security.storage;

import neuefische.capstone.backend.security.userCredentialModel.Credential;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialRepo extends PagingAndSortingRepository<Credential, String> {
    Optional<Credential> findByUsername(String username);
}
