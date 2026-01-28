package com.example.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http

        .csrf(csrf -> csrf
            .ignoringRequestMatchers(
                new AntPathRequestMatcher("/users/**"),
                new AntPathRequestMatcher("/h2-console/**")
            )
        )
        .authorizeHttpRequests(auth -> auth
            // Static + login
            .requestMatchers("/login", "/error").permitAll()
            .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()

            // UI pages: read for admin/user
            .requestMatchers(HttpMethod.GET, "/", "/dashboard", "/customers/**").hasAnyRole("ADMIN", "USER")
            // UI write (add interaction) -> admin only
            .requestMatchers(HttpMethod.POST, "/customers/**").hasRole("ADMIN")

            // API: read endpoints -> admin or viewer
            .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN", "USER")

            // API: write endpoints -> admin only
            .requestMatchers("/users/**").hasRole("ADMIN")

            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login")
            .defaultSuccessUrl("/dashboard", true)
            .permitAll()
        )
        .logout(logout -> logout
            .logoutSuccessUrl("/login?logout")
            .permitAll()
        )
        // Keep HTTP Basic for API testing (curl/Postman)
        .httpBasic(Customizer.withDefaults());

    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService(PasswordEncoder encoder) {
    UserDetails admin = User.builder()
        .username("admin")
        .password(encoder.encode("admin123"))
        .roles("ADMIN")
        .build();

    UserDetails viewer = User.builder()
        .username("viewer")
        .password(encoder.encode("viewer123"))
        .roles("USER")
        .build();

    return new InMemoryUserDetailsManager(admin, viewer);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
