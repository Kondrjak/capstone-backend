package neuefische.capstone.backend.security.service;

import neuefische.capstone.backend.security.storage.CredentialRepo;
import neuefische.capstone.backend.security.userCredentialModel.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class CredentialService implements UserDetailsService {
    private final CredentialRepo repo;

    @Autowired
    public CredentialService(CredentialRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<Credential> maybeCredential = repo.findById(username);
        return maybeCredential
                .map(credential -> User
                        .withUsername(credential.getUsername())
                        .password(credential.getPassword())
                        .authorities("user")
                        .build()
                )
                .orElseThrow(
                        () -> new UsernameNotFoundException("username " + username + " not found.")
                );
    }
}
