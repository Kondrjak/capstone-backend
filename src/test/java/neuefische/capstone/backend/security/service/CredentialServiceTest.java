package neuefische.capstone.backend.security.service;

import neuefische.capstone.backend.security.storage.CredentialRepo;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CredentialServiceTest {

    private final CredentialRepo credentialRepo = mock(CredentialRepo.class);
    private final CredentialService credentialService = new CredentialService(credentialRepo);

    @Test
    void loadUserByUsername_shouldThrow_whenUserIsNotInRepo() {
        // GIVEN
        when(credentialRepo.findById("invalid")).thenReturn(Optional.empty());

        // WHEN
        // calling: credentialService.loadUserByUsername("invalid");

        // THEN
        assertThrows(UsernameNotFoundException.class, ()->credentialService.loadUserByUsername("invalid"));
    }
}