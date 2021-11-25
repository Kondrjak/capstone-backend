package neuefische.capstone.backend.security.service;

import neuefische.capstone.backend.security.model.Credential;
import neuefische.capstone.backend.security.repo.CredentialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CredentialService implements UserDetailsService {

    private final CredentialRepo credRepo;

    @Autowired
    public CredentialService(CredentialRepo credRepo) {
        this.credRepo = credRepo;
    }

    public Credential addNewUser(Credential credential){
        return credRepo.save(credential);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return credRepo.findById(userName)
                .map(credential -> User
                        .withUsername(credential.getUsername())
                        .password(credential.getPassword())
                        .authorities("user")
                        .build())
                .orElseThrow(()-> new UsernameNotFoundException("Username does not exist: "+ userName));

    }

    public String getUserByUserName(String username) {
        Optional<Credential> optionalAppUserData = credRepo.findById(username);
        if (optionalAppUserData.isPresent()){

            return "Hallo " + optionalAppUserData.get().getUsername();

        } else {
            throw new UsernameNotFoundException("No User found by username: " + username);
        }
    }
}

