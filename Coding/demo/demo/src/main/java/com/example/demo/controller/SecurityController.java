package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecurityController {

   @GetMapping("/hello")
   public ResponseEntity<String> hello() {
      return ResponseEntity.ok("Hello, HTTPS!");
   }
}
