package com.example.multiauth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResource {

    private String uuid;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private String fcmToken;
    private boolean isGoogleSignin;
}
