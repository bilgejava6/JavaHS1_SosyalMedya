package com.muhammet.controller;

import com.muhammet.document.Authority;
import com.muhammet.dto.request.AuthoritySaveDto;
import com.muhammet.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authority")
public class AuthorityController {
    private final AuthorityService authorityService;

    @PostMapping("/save")
    public ResponseEntity<Authority> save(@RequestBody AuthoritySaveDto dto){
      Authority authority =  authorityService.save(Authority.builder()
                      .authId(dto.getAuthId())
                      .username(dto.getUserName())
                      .authorities(dto.getAuthorities())
              .build());
      return ResponseEntity.ok(authority);
    }

}
