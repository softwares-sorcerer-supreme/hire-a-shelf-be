//package com.example.shelve.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf.disable())
//                .authorizeRequests(auth -> {
//                    auth
////                            .antMatchers("/oauth2/**").permitAll()
//                            .antMatchers("/v2/api-docs",
//                                    "/configuration/ui",
//                                    "/swagger-resources/**",
//                                    "/configuration/security",
//                                    "/swagger-ui.html",
//                                    "/swagger-ui/**",
//                                    "/webjars/**").permitAll()
//                            .anyRequest().authenticated();
//        })
//                .formLogin(form -> form.permitAll())
//                .logout(logout -> logout.permitAll())
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().build();
//    }
//
//}
