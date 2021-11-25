package neuefische.capstone.backend.security.controller;

import neuefische.capstone.backend.security.model.Credential;
import neuefische.capstone.backend.security.service.JWTUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class CredentialController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtilService jwtUtilService;


    @Autowired
    public CredentialController(AuthenticationManager authenticationManager, JWTUtilService jwtUtilService) {

        this.authenticationManager = authenticationManager;
        this.jwtUtilService = jwtUtilService;
    }

    @PostMapping("auth/login")
    public String login(@RequestBody Credential credential){
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credential.getUsername(), credential.getPassword())
        );

        return jwtUtilService.createToken(new HashMap<>(), credential.getUsername());
    }

}

