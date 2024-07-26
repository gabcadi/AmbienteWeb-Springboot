package com.dcplinaWeb;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final DataSource dataSource;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(DataSource dataSource, UserDetailsService userDetailsService) {
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/registro").permitAll()
                .requestMatchers("/", "/index", "/css/**", "/webjars/**", "/js/**", "/static/**", "/img/**", "/contactar", "/faq", "/testimonios").permitAll()
                .requestMatchers("/login", "/video/**", "/css/**", "/js/**", "/webjars/**", "/static/**", "/img/**").permitAll()
                .requestMatchers("/entrenador/**").hasRole("ENTRENADOR")
                .requestMatchers("/usuario/**").hasAnyRole("USER", "ENTRENADOR")
                .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                .loginPage("/login")
                .defaultSuccessUrl("/default", true)
                .permitAll()
                )
                .logout(logout -> logout.permitAll())
                .rememberMe(rememberMe -> rememberMe
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(86400) // 1 día
                .key("mySecretKey")
                .userDetailsService(userDetailsService)
                );

        return http.build();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
