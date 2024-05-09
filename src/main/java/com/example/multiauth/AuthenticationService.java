package com.example.multiauth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * A service class responsible for API Key authentication.
 */
public class AuthenticationService {

    /**
     * The name of the HTTP header containing the API Key.
     */
    public static final String AUTH_TOKEN_HEADER_NAME = "API-KEY";

    /**
     * Attempts to retrieve and validate the API Key from the request header.
     * If valid, it creates an Authentication object representing the API Key.
     *
     * @param request the HttpServletRequest object
     * @return an Authentication object containing the validated API Key, or throws an exception if invalid
     * @throws BadCredentialsException if the API Key is missing or invalid
     */
    public static Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if (apiKey == null || !apiKey.equals("12345abc")) {
            throw new BadCredentialsException("Invalid API Key");
        }

        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}