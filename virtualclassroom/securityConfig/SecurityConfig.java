package ksolve.virtualclassroom.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ksolve.virtualclassroom.jwt.JwtAuthenticationEntryPoint;
import ksolve.virtualclassroom.jwt.JwtTokenFilter;
import ksolve.virtualclassroom.serviceImplement.CustomUserDetailsService;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;
    
    private JwtAuthenticationEntryPoint authenticationEntryPoint; 	

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            // Allow open access to login and registration endpoints
            .requestMatchers("/ksolve/api/login").permitAll()
            .requestMatchers("/userLogin/ksolve/api/registerUser").permitAll()

            // Role-based access control
            .requestMatchers("/admin/**").hasRole("ADMIN")         // Only ADMIN can access /admin/*
            .requestMatchers("/student/**").hasRole("STUDENT")     // Only STUDENT can access /student/*
            .requestMatchers("/instructor/**").hasRole("INSTRUCTOR") // Only INSTRUCTOR can access /instructor/*

            // Any other requests must be authenticated
            .anyRequest().authenticated();

        // Prevent XSS attacks for unauthorized users
        http.headers()
            .xssProtection().and()
            .contentSecurityPolicy("script-src 'self'");

        // Add JWT filter before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry corsRegistry) {
                corsRegistry.addMapping("/**")
                    .allowedMethods("*")
                    .allowedOrigins("*")
                    .allowedHeaders("*");
            }
        };
    }
}