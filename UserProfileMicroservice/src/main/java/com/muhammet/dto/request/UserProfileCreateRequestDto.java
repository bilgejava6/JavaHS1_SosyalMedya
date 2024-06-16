package com.muhammet.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileCreateRequestDto {
    @NotNull
    @Min(value = 1)
    Long authId;
    @NotNull
    @NotEmpty
    @Size(min = 3,max = 64)
    String userName;
}
