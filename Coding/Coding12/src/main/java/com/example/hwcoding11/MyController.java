package com.example.hwcoding11;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping("/api/test1")
    public void testEndpoint() {
        // Empty response
    }
}
