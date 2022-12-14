package app.security;

import app.consts.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig
{
    @Autowired
    private TokenFilter tokenFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/login/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/internshipSubject/**","/appointment/**","/announcement/**").authenticated()
                        .requestMatchers(HttpMethod.PUT,"/appointment/**").authenticated()
                        .requestMatchers(HttpMethod.POST,"/internship/**").authenticated()
                        .requestMatchers(HttpMethod.GET,"/internship/student/**").authenticated()
                        .requestMatchers("/teacher/**","/student/**","/appointment/**","/internship/**","/internshipSubject/**","/announcement/**").hasRole(Role.TEACHER)
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(tokenFilter,UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }
}