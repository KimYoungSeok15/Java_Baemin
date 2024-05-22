package com.example.shoppingmall.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    // MemberRequestDTO, MemberResponseDTO, SignUpReqDTO, SignUpResDto, LoginReqDTO, LoginResDTO
    private int id;

    @JsonProperty("user_id")
    @NotBlank(message = "User ID cannot be blank")
    private String userId;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String pw;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
    private String contact;

    public Member convertToEntity() {
        return new Member(userId, pw, name, email, contact);
    }
}
