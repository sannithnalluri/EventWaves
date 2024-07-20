package com.EventWaveServer.EventWave.Security;


import com.EventWaveServer.EventWave.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Configuration
@EnableWebSecurity
public class Config {
    @Autowired
    private UserServiceImpl userServieImpl;

    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring security filter chain");
        http
                .authorizeHttpRequests(auths -> auths
                        .requestMatchers("/auth/register").permitAll()
                        .requestMatchers("/auth/test").permitAll()
                        .anyRequest().authenticated() // Allow all requests
                )
                .csrf(AbstractHttpConfigurer::disable)
                .userDetailsService(userServieImpl)
                .sessionManagement(session->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        logger.info("Creating password encoder");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public  AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws  Exception{
        logger.info("Creating authentication manager");
        return  configuration.getAuthenticationManager();
    }

}
