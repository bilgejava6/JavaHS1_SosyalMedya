package com.muhammet.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRegisterReqestDto {
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 64)
    String userName;
    @NotNull
    @NotEmpty
    @Size(min = 8,max = 32)
    String password;
    @NotNull
    @NotEmpty
    @Size(min = 8,max = 32)
    String rePassword;
    @Email
    @NotNull
    String email;
}
