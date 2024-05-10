package com.example.multiauth;

import com.example.multiauth.model.AuthenticationResponse;
import com.example.multiauth.model.UserResource;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AuthenticationApi {

    @GET("user/basic/")
    Call<AuthenticationResponse<UserResource>> authenticateUser(@Query("email") String email, @Query("password") String password);
}