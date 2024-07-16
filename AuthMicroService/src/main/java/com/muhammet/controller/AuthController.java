package com.muhammet.controller;

import com.muhammet.dto.request.AuthLoginRequestDto;
import com.muhammet.dto.request.AuthRegisterReqestDto;
import com.muhammet.exception.AuthException;
import com.muhammet.exception.ErrorType;
import com.muhammet.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.muhammet.config.RestApis.*;
@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping(REGISTER)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> register(@RequestBody @Valid AuthRegisterReqestDto dto){
        if(!dto.getPassword().equals(dto.getRePassword()))
            throw new AuthException(ErrorType.BAD_REQUEST_REPASSWORD_ERROR);
        authService.save(dto);
        return ResponseEntity.ok(true);
    }
    @PostMapping(LOGIN)
    @CrossOrigin("*")
    public ResponseEntity<String> login(@RequestBody @Valid AuthLoginRequestDto dto){
       return ResponseEntity.ok(authService.login(dto));
    }
}
