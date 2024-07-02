package com.muhammet.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final  String headerAuth = request.getHeader("Authorization");
        log.info("gelen auth token...: "+ headerAuth);
        /**
         * Login page olmadığı ortamlar da,
         * RestAPI kullanılan, microservice yapısıs olan mimarilerde
         * TOKEN kullanılır.
         * Token ya da JwtToken kullanım şekli şöyle işlemektedir.
         * - öncelikle kullanıcı Auth servisinden login işlemini yaparak token alır.
         * - sonraki tüm işlemlerinde token bilgisini request lerinin tümünde
         * sunucuya iletecek. bu işlem için Header üzerinde Authentication anahtar kelimesini
         * kullanarak Bearer [TOKEN] şeklinde bir value iletir.
         * - iletilen bilgi sunucu tarafından işlenerek işleme onay verilir.
         */
        if(Objects.nonNull(headerAuth) && headerAuth.startsWith("Bearer ")){

        }
    }
}
