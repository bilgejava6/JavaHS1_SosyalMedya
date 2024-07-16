package com.muhammet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackGatewayController {
    @GetMapping("/auth")
    public String authFallBack(){
        return "Auth Servis şuan yanıt veremiyor lütfen tekrar deneyiniz";
    }

    @GetMapping("/user")
    public String userFallBack(){
        return "User Servis şuan yanıt veremiyor lütfen tekrar deneyiniz";
    }
}
