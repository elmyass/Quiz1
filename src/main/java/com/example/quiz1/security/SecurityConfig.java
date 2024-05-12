package com.example.quiz1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
              .formLogin(Customizer.withDefaults())
              .httpBasic(Customizer.withDefaults())
              .authorizeHttpRequests(
                      authCustomizer -> authCustomizer
                              //les droits
                              .requestMatchers("/editUser","/updateUser" ,"deletUser").hasRole("ADMIN")
                              .requestMatchers("/createUser", "/saveUser").hasRole("ADMIN")
                              .requestMatchers("/createTest", "/saveTest", "/editTest").hasRole("PROF")
                              .requestMatchers("/createQuestion", "/saveQuestion" , "/deleteQuestion").hasRole("PROF")
                              .requestMatchers("/usersList").hasAnyRole("ADMIN", "PROF", "ETUDIANT")
                              .anyRequest().authenticated()
              )
      .build();
    }
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin").password(bCryptPasswordEncoder().encode("123")).roles("ADMIN" , "ETUDIANT").build(),
                User.withUsername("prof").password(bCryptPasswordEncoder().encode("123")).roles("PROF").build(),
                User.withUsername("etudiant").password(bCryptPasswordEncoder().encode("123")).roles("ETUDIANT").build()
        );
    }
}
