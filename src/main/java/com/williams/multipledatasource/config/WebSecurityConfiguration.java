package com.williams.multipledatasource.config;

import com.williams.multipledatasource.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Autowired
    UserDetailsService userDetailsService;

    private final UserRepository userRepository;

    @Autowired
    public WebSecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/api/user/save_user", "/public/**").permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/api/save_user", "POST")).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        List<UserDetails> users = new ArrayList<>();
        userRepository.findAll().forEach(savedUser -> {
            UserDetails user = User.builder()
                    .username(savedUser.getUsername())
                    .password(passwordEncoder().encode(savedUser.getPassword()))
                    .roles(savedUser.getRole())
                    .build();
            users.add(user);
        });
        return new InMemoryUserDetailsManager(users);
    }


    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
