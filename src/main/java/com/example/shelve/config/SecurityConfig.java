package com.example.shelve.config;

import com.example.shelve.services.impl.UserDetailServiceImpl;
import com.google.api.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String STORE_ROLE = "ROLE_STORE";
    private static final String BRAND_ROLE = "ROLE_BRAND";
    private static final String ADMIN_ROLE = "ROLE_ADMIN";
    @Autowired
    private UserDetailsService userDetailService;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors().and()
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth
//                            .antMatchers("/oauth2/**").permitAll()
                            .antMatchers("/v3/api-docs",
                                    "/configuration/ui",
                                    "/swagger-resources/**",
                                    "/configuration/security",
                                    "/swagger-ui.html",
                                    "/swagger-ui/**",
                                    "/webjars/**",
                                    "/api/auth/**",
                                    "/api/auth/google/**",
                                    "/api/account/password/forget")
                            .permitAll()
                            .antMatchers(HttpMethod.POST, "/api/register").permitAll()
                            .antMatchers(HttpMethod.GET, "/api/campaign").permitAll()
                            //Admin
                            .antMatchers(HttpMethod.GET,"/api/register").hasAuthority(ADMIN_ROLE)
                            .antMatchers(HttpMethod.GET,"/api/category").hasAuthority(ADMIN_ROLE)
                            .antMatchers(HttpMethod.POST,"/api/shelve/type").hasAuthority(ADMIN_ROLE)
                            .antMatchers(HttpMethod.PUT, "/api/campaign/{id}").hasAuthority(ADMIN_ROLE)
                            .antMatchers(HttpMethod.PUT, "/api/register/**").hasAuthority(ADMIN_ROLE)
                            .antMatchers(HttpMethod.PUT, "/api/category/**").hasAuthority(ADMIN_ROLE)
                            .antMatchers(HttpMethod.POST, "/api/category").hasAuthority(ADMIN_ROLE)
                            .antMatchers(HttpMethod.PUT, "/api/shelve/type/**").hasAuthority(ADMIN_ROLE)
                            .antMatchers(HttpMethod.GET, "/api/brand").hasAuthority(ADMIN_ROLE)
                            .antMatchers(HttpMethod.GET, "/api/store").hasAuthority(ADMIN_ROLE)
                            //Store
                            .antMatchers(HttpMethod.GET,"/api/shelve").hasAuthority(STORE_ROLE)
                            .antMatchers(HttpMethod.GET,"/api/campaign/home").hasAuthority(STORE_ROLE)
                            .antMatchers(HttpMethod.GET,"/api/contract/store").hasAuthority(STORE_ROLE)
                            .antMatchers(HttpMethod.POST,"/api/shelve").hasAuthority(STORE_ROLE)
                            .antMatchers(HttpMethod.POST, "/api/store/category").hasAuthority(STORE_ROLE)
                            .antMatchers(HttpMethod.POST, "/api/contract").hasAuthority(STORE_ROLE)

                            //Brand
                            .antMatchers(HttpMethod.POST,"/api/campaign").hasAuthority(BRAND_ROLE)
                            .antMatchers(HttpMethod.GET,"/api/product/brand/**").hasAuthority(BRAND_ROLE)
                            .antMatchers(HttpMethod.GET,"/api/product").hasAuthority(BRAND_ROLE)
                            .antMatchers(HttpMethod.POST,"/api/product").hasAuthority(BRAND_ROLE)
                            .antMatchers(HttpMethod.DELETE, "/api/campaign/**").hasAuthority(BRAND_ROLE)
                            .antMatchers(HttpMethod.PUT, "/api/campaign/u/{id}").hasAuthority(BRAND_ROLE)

                            //Multiple role
                            .antMatchers(HttpMethod.GET,"/api/category/status/**").hasAnyAuthority(STORE_ROLE, BRAND_ROLE)
                            .antMatchers(HttpMethod.GET,"/api/campaign/{id}").hasAnyAuthority(ADMIN_ROLE, BRAND_ROLE)

                            //Authenticated
                            .antMatchers(HttpMethod.GET,"/api/notification/**").authenticated()
                            .antMatchers(HttpMethod.GET, "/api/shelve/types/**").authenticated()
                            .antMatchers(HttpMethod.POST,"/api/account/password/change").authenticated()
                            .antMatchers(HttpMethod.GET, "/api/account/{id}").authenticated()
                            //DenyAll
                            .anyRequest().denyAll();
        })
                .headers(headers ->
                        headers
                                .frameOptions()
                                .sameOrigin())
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
