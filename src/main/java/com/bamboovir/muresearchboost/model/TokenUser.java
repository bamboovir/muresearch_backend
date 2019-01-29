package com.bamboovir.muresearchboost.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tokenuser")
public class TokenUser {
    @Id
    private String id;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private String authorities;
    private Long lastPasswordChange;
    private Boolean enable;
}
