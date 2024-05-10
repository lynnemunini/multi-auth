package com.example.multiauth;

import com.example.multiauth.model.UserResource;
import com.example.multiauth.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AuthorizationServerAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        AuthenticationService authenticationService = new AuthenticationService();

        Optional<UserResource> optionalUser = authenticationService.authenticateUser(username, password);
        if (optionalUser.isEmpty()) {
            throw new BadCredentialsException("Invalid username or password");
        }

        List<GrantedAuthority> authorities = Collections.emptyList();
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
