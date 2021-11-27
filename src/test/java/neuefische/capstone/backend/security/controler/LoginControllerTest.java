package neuefische.capstone.backend.security.controler;

import neuefische.capstone.backend.security.storage.CredentialRepo;
import neuefische.capstone.backend.security.storage.PrivilegeRepo;
import neuefische.capstone.backend.security.storage.RoleRepo;
import neuefische.capstone.backend.security.model.Credential;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.AfterEach;
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


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.service.secret.string}")
    private String JWT_SECRET;

    @Autowired
    private CredentialRepo credentialRepo;

    @Autowired
    PrivilegeRepo privilegeRepo;

    @Autowired
    RoleRepo roleRepo;

    @AfterEach
    public void clearRepos() {
        credentialRepo.deleteAll();
        privilegeRepo.deleteAll();
        roleRepo.deleteAll();
    }

    @Test
    void post_withValidCredentials_ShouldReturnValidJwt_withHttpStatusOk() {

        // GIVEN
        String hashedPw = passwordEncoder.encode("some-password");
        credentialRepo.save(Credential.builder().username("some-user").password(hashedPw).build());

        // WHEN
        Credential appUser = Credential.builder().username("some-user").password("some-password").build();
        ResponseEntity<String> response = restTemplate
                .postForEntity(
                        "/auth/login",
                        appUser,
                        String.class);

        // THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        // extract Claims from Token
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(response.getBody())
                .getBody();
        assertThat(claims.getSubject(), is("some-user"));
    }
}