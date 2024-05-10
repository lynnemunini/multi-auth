package com.example.multiauth.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class AuthenticationResponse<T> {

    private boolean success;
    private String message;
    private T data;
    List<Link> links = new ArrayList<>();
}