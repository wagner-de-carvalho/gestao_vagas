package br.com.acme.gestao_vagas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private SecurityCompanyFilter securityCompanyFilter;

    @Autowired
    private SecurityCandidateFilter securityCandidateFilter;

    private static final String[] PERMIT_ALL_LIST = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/candidate").permitAll()
                            .requestMatchers("/company").permitAll()
                            .requestMatchers("/company/auth").permitAll()
                            .requestMatchers("/candidate/auth").permitAll()
                            .requestMatchers(PERMIT_ALL_LIST).permitAll();
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(securityCandidateFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(securityCompanyFilter, BasicAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
