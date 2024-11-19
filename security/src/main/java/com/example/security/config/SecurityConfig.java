package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // TODO: 웹에 적용할 시큐리티 클래스 포함
@Configuration // TODO: 환경설정 파일
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize
            // TODO: "/" 경로에 대해 permission All 로 바꿈
            .requestMatchers("/", "/sample/guest").permitAll()
            .requestMatchers("/sample/member").hasRole("USER")
            .requestMatchers("/sample/admin").hasRole("ADMIN"))
        .formLogin(Customizer.withDefaults());

    return http.build();
  }

  @Bean // TODO: 스프링 컨테이너가 관리하는 객체(다른 곳에서 passwordEncoder객체가 필요할 때 자동으로 주입해줌)
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  UserDetailsService users() {
    UserDetails user = User.builder()
        .username("user1")
        .password("{bcrypt}$2a$10$t8eaMZpHy/1KwzyQ3TjGeedRhFkjrws5Cu1/Nl4mlQMaSPLr2tSB.")
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(user);
  }
}
