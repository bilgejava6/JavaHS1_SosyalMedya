package com.muhammet.config;

import com.muhammet.document.Authority;
import com.muhammet.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUserDetail implements UserDetailsService {
    private final AuthorityService authorityService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails getUserDetailFromAuthId(Long authId){
        Optional<Authority> authority = authorityService.findByAuthId(authId);
        if(authority.isEmpty())  return  null;
        List<GrantedAuthority> yetkiListesi = new ArrayList<>();
        authority.get().getAuthorities().forEach(yetki->{
            yetkiListesi.add(new SimpleGrantedAuthority(yetki));
        });
        return User.builder()
                .username(authority.get().getUsername())
                .password("")
                .accountExpired(false)
                .accountLocked(false)
                .authorities(yetkiListesi)
                .build();

    }
}
