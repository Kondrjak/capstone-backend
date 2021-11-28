package neuefische.capstone.backend.security.controler;

import neuefische.capstone.backend.security.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class CredentialController {
    CredentialService service;

    @Autowired
    public CredentialController(CredentialService service) {
        this.service = service;
    }

    @GetMapping("me")
    public UserDetails getLoggedInUser(Principal principal) {
        String username = principal.getName();
        return service.loadUserByUsername(username);
    }
}
