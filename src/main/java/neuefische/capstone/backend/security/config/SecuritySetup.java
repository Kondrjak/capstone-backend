package neuefische.capstone.backend.security.config;

import lombok.RequiredArgsConstructor;
import neuefische.capstone.backend.security.service.PrivilegeService;
import neuefische.capstone.backend.security.service.RoleService;
import neuefische.capstone.backend.security.storage.CredentialRepo;
import neuefische.capstone.backend.security.model.Credential;
import neuefische.capstone.backend.security.model.Role;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Loads initial users, roles and privileges on startup
 * compare: https://www.baeldung.com/role-and-privilege-for-spring-security-registration
 */
@Component
@RequiredArgsConstructor
public class SecuritySetup implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    private final CredentialRepo credentialRepo;
    private final RoleService roleService;
    private final PrivilegeService privilegeService;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        Role adminRole = roleService.createAdminRole();
        Role userRole = roleService.createUserRole();

        Credential aliceCredentials = Credential.builder()
                .username("alice")
                .password(passwordEncoder.encode("alice"))
                .roles(List.of(adminRole))
                .build();
        credentialRepo.save(aliceCredentials);

        Credential bobCredentials = Credential.builder()
                .username("bob")
                .password(passwordEncoder.encode("bob"))
                .roles(List.of(userRole))
                .build();
        credentialRepo.save(bobCredentials);

        alreadySetup = true;
    }
}