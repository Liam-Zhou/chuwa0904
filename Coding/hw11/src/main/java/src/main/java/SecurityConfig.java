package src.main.java;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/secure").permitAll()  // Use requestMatchers instead of antMatchers
                        .anyRequest().authenticated()
                )
                .requiresChannel(channel -> channel
                        .anyRequest().requiresSecure()
                )
                .httpBasic(withDefaults());  // Default configuration for basic auth

        return http.build();
    }
}