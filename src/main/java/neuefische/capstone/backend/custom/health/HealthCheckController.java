package neuefische.capstone.backend.custom.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/health")
    public String index() {
        return "Hello there! I'm running.";
    }
}

