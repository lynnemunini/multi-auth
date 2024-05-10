package com.example.multiauth.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/multi-auth")
public class MultiAuthController {

    @GetMapping("/server/test")
    public String serverTestEndpoint() {
        return "This is a secured endpoint! You have successfully authenticated using an API key.";
    }

    @GetMapping("/client/test")
    public String clientTestEndpoint(Principal principal) {
        return "This is a secured endpoint for clients! You are authenticated as: " + principal.getName();
    }
}
