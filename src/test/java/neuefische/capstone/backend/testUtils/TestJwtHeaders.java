package neuefische.capstone.backend.testUtils;

import neuefische.capstone.backend.security.storage.CredentialRepo;
import neuefische.capstone.backend.security.userCredentialModel.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Component
public class TestJwtHeaders {
    private final String username = "someUser";
    private final String password = "somePassword";

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private CredentialRepo credentialRepo;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * In order to go through the jwt token authentication process,
     * all test http mappings need an authenticated users header
     *
     * @return authenticated users http headers for controller tests
     */
    public HttpHeaders get(){
        Optional<Credential> maybeCredential = credentialRepo.findByUsername(username);
        return maybeCredential.map(this::headersFromExistentUser).orElse( createSomeUserAndReturnHeaders());
    }

    private HttpHeaders createSomeUserAndReturnHeaders() {
        String hashedPw = encoder.encode(password);
        Credential hashedCredential = Credential.builder().username(username).password(hashedPw).build();
        credentialRepo.save(hashedCredential);
        return headersFromExistentUser(Credential.builder().username(username).password(password).build());
    }

    private HttpHeaders headersFromExistentUser(Credential loginCredential){
        ResponseEntity<String> postResponse = restTemplate.postForEntity("/auth/login", loginCredential, String.class);
        HttpHeaders headers = new HttpHeaders();
        assertThat(postResponse.getBody(), is(notNullValue()));
        headers.setBearerAuth(postResponse.getBody());
        return headers;
    }

}
