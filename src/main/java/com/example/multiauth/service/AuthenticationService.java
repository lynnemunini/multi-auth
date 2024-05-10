package com.example.multiauth.service;

import com.example.multiauth.ApiKeyAuthentication;
import com.example.multiauth.AuthenticationApi;
import com.example.multiauth.model.UserResource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.Optional;

@Service
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

    public AuthenticationApi createRetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("YOUR REST ENDPOINT URL GOES HERE")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit.create(AuthenticationApi.class);
    }

    public Optional<UserResource> authenticateUser(String email, String password) {
        var authenticationApi = createRetrofitService();
        var request = authenticationApi.authenticateUser(email, password);
        try {
            var response = request.execute();
            if (response.isSuccessful() && response.body() != null) {
                return Optional.of(response.body().getData());
            } else {
                return Optional.empty();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}