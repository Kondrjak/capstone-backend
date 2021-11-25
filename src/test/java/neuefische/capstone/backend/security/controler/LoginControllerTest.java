package neuefische.capstone.backend.security.controler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import neuefische.capstone.backend.security.storage.CredentialRepo;
import neuefische.capstone.backend.security.userCredentialModel.Credential;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CredentialRepo credentialsRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.service.secret.string}")
    private String JWT_SECRET;

    @Test
    void post_withValidCredentials_ShouldReturnValidJwt_withHttpStatusOk() {

        // GIVEN
        String hashedPw = passwordEncoder.encode("some-password");
        Credential credentialToSave = Credential.builder().username("user").password(hashedPw).build();
        credentialsRepo.save(credentialToSave);

        // WHEN
        Credential credentialToPost = Credential.builder().username("user").password("some-password").build();
        ResponseEntity<String> response = restTemplate
                .postForEntity(
                        "/auth/login",
                        credentialToPost,
                        String.class);

        // THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        // extract Claims from Token
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(response.getBody())
                .getBody();
        assertThat(claims.getSubject(), is("user"));
    }

    @Test
    void login_WithInvalidCredentials_ShouldReturnError() {

        // GIVEN
        Credential unauthorizedUser = new Credential(1,"user", "WRONG_PASSWORD");

        // WHEN
        ResponseEntity<String> response = restTemplate.postForEntity("/auth/login",
                                                                    unauthorizedUser,
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
}