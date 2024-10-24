package com.example.cyberclub.Security;


import com.example.cyberclub.Data.Users;
import com.example.cyberclub.Repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            Users users = userRepository.findByUsername(username);
            if (users != null) return users;
            throw new UsernameNotFoundException("Пользователь не найден!");
        };
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth->auth
                .requestMatchers("/Status/**", "/Computer/**", "/Guest/**", "/Visit/**").authenticated()
                .requestMatchers("/Login", "/Registration").anonymous()
                        .requestMatchers("/", "/css/**", "/img/**").permitAll())
                        .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage("/Login"))
                .build();
    }
}