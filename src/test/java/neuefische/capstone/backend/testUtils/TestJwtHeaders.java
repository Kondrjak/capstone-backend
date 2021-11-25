package neuefische.capstone.backend.testUtils;

import neuefische.capstone.backend.security.storage.CredentialRepo;
import neuefische.capstone.backend.security.userCredentialModel.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Component
public class TestJwtHeaders {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private CredentialRepo credentialRepo;

    /**
     * In order to go through the jwt token authentication process,
     * all test http mappings need an authenticated users header
     *
     * @return authenticated users http headers for controller tests
     */
    public HttpHeaders get() {
        String username = "someUser";
        String password = "somePassword";
        String hashedPw = encoder.encode(password);
        credentialRepo.save(Credential.builder().username(username).password(hashedPw).build());
        Credential loginData = Credential.builder().username(username).password(password).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> postResponse = restTemplate.postForEntity("/auth/login", loginData, String.class);
        HttpHeaders headers = new HttpHeaders();
        assertThat(postResponse.getBody(), is(notNullValue()));
        headers.setBearerAuth(postResponse.getBody());
        return headers;
    }

}
