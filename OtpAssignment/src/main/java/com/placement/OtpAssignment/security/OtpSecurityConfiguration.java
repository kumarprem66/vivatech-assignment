package com.placement.OtpAssignment.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class OtpSecurityConfiguration {

    @Bean
    public SecurityFilterChain optSecurityConfig(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.sessionManagement(
                        sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .cors(cors -> {

                    cors.configurationSource(new CorsConfigurationSource() {

                        @Override
                        public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                            CorsConfiguration cfg = new CorsConfiguration();

                            cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
                            cfg.setAllowedMethods(Collections.singletonList("*"));
                            cfg.setAllowCredentials(true);
                            cfg.setAllowedHeaders(Collections.singletonList("*"));
                            cfg.setExposedHeaders(Arrays.asList("Authorization"));
                            return cfg;

                        }
                    });

                }).authorizeHttpRequests(auth ->
                auth.requestMatchers(HttpMethod.POST,"/otp/register").permitAll()
//                        .requestMatchers("/otp/signIn").permitAll()
                        .requestMatchers("/swagger-ui*/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                ).csrf(csrf -> csrf.disable())
                .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

}
