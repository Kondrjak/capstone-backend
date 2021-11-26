package neuefische.capstone.backend.security.controler;

import neuefische.capstone.backend.security.service.JwtService;
import neuefische.capstone.backend.security.storage.CredentialRepo;
import neuefische.capstone.backend.security.userCredentialModel.Credential;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginControllerThrowsTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CredentialRepo credentialRepo;

    /**
     * test throws cause of unknown injection problems when in same test class with
     * getMe_shouldReturnUsersCredentials_withHttpStatusOK_ifUserIsAuthenticated
     */
    @Test
    void login_WithInvalidCredentials_ShouldReturnError() {
        // GIVEN
        Credential invalidCredentials = Credential.builder().username("user").password("WRONG_PASSWORD").build();
        // WHEN
        ResponseEntity<String> response = restTemplate.postForEntity("/auth/login",
                                                                    invalidCredentials,
                                                                    String.class
        );
        // THEN
        assertThat(response.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
        assertThat(
                response.getBody(),
                startsWith("{\"text\":\"Api Error: Username and/or password are not valid!\"," +
                        "\"exceptionMessage\":\"Ungültige Anmeldedaten")
        );
    }


    @Test
    void login_WithValidCredentials_ShouldReturnValidJwt(){
        // GIVEN
        String username = "user";
        String password = "correct";
        String hashedPw = new BCryptPasswordEncoder().encode(password);
        Credential credentialToSave = Credential.builder().username(username).password(hashedPw).build();
        credentialRepo.save(credentialToSave);
        Credential nowValidCredential = Credential.builder().username(username).password(password).build();
        // WHEN
        ResponseEntity<String> response = restTemplate.postForEntity("/auth/login",
                nowValidCredential,
                String.class
        );
        String actualExtractedUsername = jwtService.parseUsername(response.getBody());
        // THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(actualExtractedUsername, is(username));
    }
}