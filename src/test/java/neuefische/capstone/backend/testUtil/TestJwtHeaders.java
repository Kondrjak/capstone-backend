package neuefische.capstone.backend.testUtil;

import lombok.RequiredArgsConstructor;
import neuefische.capstone.backend.security.service.PrivilegeService;
import neuefische.capstone.backend.security.service.RoleService;
import neuefische.capstone.backend.security.storage.CredentialRepo;
import neuefische.capstone.backend.security.model.Credential;
import neuefische.capstone.backend.security.model.Role;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Component
@RequiredArgsConstructor
public class TestJwtHeaders {
    private final String username = "someUser";
    private final String password = "somePassword";
    private final PasswordEncoder encoder;
    private final CredentialRepo credentialRepo;
    private final RoleService roleService;
    private final PrivilegeService privilegeService;

    /**
     * In order to go through the jwt token authentication process,
     * all test http mappings need an authenticated users header
     *
     * @return authenticated users http headers for controller tests
     */
    public HttpHeaders createAdminAndReturnHeaders(int port){
        String hashedPw = encoder.encode(password);
        Role admin = roleService.createAdminRole();
        Credential hashedCredential = Credential.builder()
                .username(username)
                .password(hashedPw)
                .roles(List.of(admin))
                .build();
        credentialRepo.save(hashedCredential);
        return headersFromExistentUser(port, Credential.builder().username(username).password(password).build());
    }

    private HttpHeaders headersFromExistentUser(int port, Credential loginCredential){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> postResponse = restTemplate.postForEntity("http://localhost:"+port+"/auth/login", loginCredential, String.class);
        HttpHeaders headers = new HttpHeaders();
        assertThat(postResponse.getBody(), is(notNullValue()));
        headers.setBearerAuth(postResponse.getBody());
        return headers;
    }

}
