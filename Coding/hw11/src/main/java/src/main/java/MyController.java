package src.main.java;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/secure")
    public ResponseEntity<String> getSecure() {
        return ResponseEntity.ok("This is a secure response");
    }
}
