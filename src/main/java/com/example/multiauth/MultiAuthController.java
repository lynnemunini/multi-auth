package com.example.multiauth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/multi-auth")
public class MultiAuthController {

    @GetMapping("/server/test")
    public String testEndpoint() {
        return "This is a secured endpoint! You have successfully authenticated using an API key.";
    }
}
