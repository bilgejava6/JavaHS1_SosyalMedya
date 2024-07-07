package com.muhammet.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
//@EnableWebSecurity
@EnableMethodSecurity
@Slf4j
public class ElasticsearchSecurityConfig {

    @Bean
    public JwtTokenFilter getJwtFilter() {
        return new JwtTokenFilter();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{
        /**
         * Spring in Security yeteneklerinin yönertileceği kısım. Filter mekanizmalarını ve
         * yetkilendireme işlemlerini buradan yöneteceğiz.
         * DİKKAT!!!!!
         * Spring Boot 3.X öncesi ve sonrası yapılandırma çok farklı.
         * Bİr end-point için 3 farklı erişim var
         * 1. public, herkesin erişimine açık alanlar. -> permitAll()
         * 2. protected, bellir bir kullanıcının erişimine açık alanlar(OTURUM AÇMA ZORUNLULUĞU)
         * 3. private, sadece admin lerin erişimine açık alanlar. -> hasAuthority()
         */
        httpSecurity.authorizeHttpRequests(req->
                req
                        .requestMatchers(
                        "/swagger-ui/**","/v3/api-docs/**",
                                "/authority/save/**"
                        ) // Bu kod sadece gelen isteğin url sinin çözümlemek ve eşleştirmek için yazılır.
                        .permitAll() // herkese izin ver.

                        .requestMatchers(
                                "/elastic-user/save"
                        ).hasAuthority("ADMIN") // bir end-point e erişim için istek atan kullanıcının ADMIN rolü olmalı
                        .anyRequest()  // her hangi diğer tüm istekler
                        .authenticated() // bir kullanıcının oturum açmasını zorunlu kıl.
        );
        /**
         * _csrf nedir?
         * login page -> username, password ile giriş yapacaksınız.
         * soru: bir kullanıcının birden fazla kez şifre denediğini nasıl anlarsınız?
         * Kullanıcı isteklerinin kendi sunucularımızda barındırıdığımız web sitelerinden
         * gelip gelmediğini anlamak için kullanıyoruz.
         * Ancak RestAPI ile bir sistem kodlamış isek bunu yerine TOKEN kullanmak
         * doğru bir yöntem olacaktır.
         */
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        /**
         * Login sayfasını getirmek için kullanılır.
         */
        // httpSecurity.formLogin(AbstractAuthenticationFilterConfigurer::permitAll);


        /**
         * kullanıcıların kendilerini doğrulamaları ve sisteme giriş yapmaları için yöntemlerin
         * eklenmesi gereklidir. Bu yöntemler;
         * Header da TOKEN alanara olabilir,
         * Google, Facebook, Github v.s. 3. taraf uygulama entegrasyonları ile olabilir.
         * LDAP, microsoft oturumları, OS oturumları gibi yapılarla olabilir.
         */
        httpSecurity.addFilterBefore(getJwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
