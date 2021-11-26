package neuefische.capstone.backend.security.controler;

import neuefische.capstone.backend.security.storage.CredentialRepo;
import neuefische.capstone.backend.testUtils.RequestBuilder;
import neuefische.capstone.backend.testUtils.TestJwtHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CredentialControllerTest {

    @Autowired
    TestJwtHeaders jwtHeaders;

    @Autowired
    CredentialRepo credentialRepo;

    @LocalServerPort
    private int port;

    @Test
    void getMe_shouldReturnUsersCredentials_withHttpStatusOK_ifUserIsAuthenticated() {
        // GIVEN
        HttpHeaders testUserJwtHeaders = jwtHeaders.get();
        RequestBuilder<String> request = new RequestBuilder<>(String.class, testUserJwtHeaders);

        // WHEN
        ResponseEntity<String> getResponse = request.getForEntity(port + "/api/user/me");

        HttpStatus actualStatusCode = getResponse.getStatusCode();
        String actualBody = getResponse.getBody();

        // THEN
        assertThat(actualStatusCode, is(HttpStatus.OK));
        assertThat(actualBody, endsWith("\"username\":\"someUser\"," +
                        "\"authorities\":[{\"authority\":\"user\"}]," +
                        "\"accountNonExpired\":true," +
                        "\"accountNonLocked\":true," +
                        "\"credentialsNonExpired\":true," +
                        "\"enabled\":true}"
                )
        );
    }
}