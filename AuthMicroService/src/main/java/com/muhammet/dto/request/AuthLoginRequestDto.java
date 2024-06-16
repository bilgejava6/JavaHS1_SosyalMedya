package com.muhammet.dto.request;

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
public class AuthLoginRequestDto {
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 64)
    String userName;
    @NotNull
    @NotEmpty
    @Size(min = 8, max = 64)
    String password;
}
