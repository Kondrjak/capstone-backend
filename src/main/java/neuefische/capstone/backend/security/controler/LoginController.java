package neuefische.capstone.backend.security.controler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neuefische.capstone.backend.security.model.Credential;
import neuefische.capstone.backend.security.service.JwtService;
import neuefische.capstone.backend.security.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/auth/login")
public class LoginController {

    @Value("http://localhost:3000/")
    String redirectUrl;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final OAuthService oAuthService;

    public LoginController(AuthenticationManager authenticationManager, JwtService jwtService, OAuthService oAuthService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.oAuthService = oAuthService;
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


    @GetMapping("/github")
    public RedirectView loginWithGithub(
            RedirectAttributes attributes, @Param("code") String code) {
        String username = oAuthService.getUsername(code);
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("jwtToken", jwtService.createToken(new HashMap<>(), username));
        return new RedirectView(redirectUrl);
    }

}
