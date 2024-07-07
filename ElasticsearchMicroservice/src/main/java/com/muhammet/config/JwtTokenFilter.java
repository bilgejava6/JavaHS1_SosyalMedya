package com.muhammet.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtManager jwtManager;
    @Autowired
    private JwtUserDetail jwtUserDetail;
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
            String token = headerAuth.substring(7); // header içinde gelen Berar TOKEN bilgisini alır.
            Optional<Long> authId = jwtManager.getAuthId(token); // token kontrolü ve authId okuma işlemi
            if(authId.isPresent()){
                /**
                 * Tüm bilgiler doğru olduğunda burası çalışacak ve kullanıcının oturum açması sağlanacak
                 * ayrıca bu kullanıcının yetkilendirmeleri var ise bu yetkiler filter katmanına iletilecek.
                 * Bu kısımda, Spring in kendi user yönetiminin önüne geçerek filter yapısı için bir nesne
                 * oluşturuyoruz.
                 */
                UserDetails userDetails = jwtUserDetail.getUserDetailFromAuthId(authId.get());
                UsernamePasswordAuthenticationToken userToken  =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(userToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
