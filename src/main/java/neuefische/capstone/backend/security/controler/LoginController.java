package neuefische.capstone.backend.security.controler;

import neuefische.capstone.backend.security.service.JwtService;
import neuefische.capstone.backend.security.userCredentialModel.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping
    public String login(@RequestBody Credential cred) {
        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                cred.getUsername(),
                                cred.getPassword()
                        )
                );
        return jwtService.createToken(new HashMap<>(), cred.getUsername());
    }
}
