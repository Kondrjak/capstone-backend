package neuefische.capstone.backend.security.repo;

import neuefische.capstone.backend.security.model.Credential;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepo extends PagingAndSortingRepository<Credential, String> {

}
