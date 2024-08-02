package com.example.emsbackend.dto.auths;


import com.example.emsbackend.dto.users.UserEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    // Token that should be sent back the user the first time they logged in
    private String token;

    UserEntityDTO userInfo;

}
