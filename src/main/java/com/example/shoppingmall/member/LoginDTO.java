package com.example.shoppingmall.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    // MemberRequestDTO, MemberResponseDTO, SignUpReqDTO, SignUpResDto, LoginReqDTO, LoginResDTO
    private int id;

    @JsonProperty("user_id")
    @NotBlank(message = "User ID cannot be blank")
    private String userId;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String pw;

    public String getStringId(){
        return String.valueOf(id);
    }
}
