package Idea.Archieve.IdeaArchieve.global.security;

import Idea.Archieve.IdeaArchieve.domain.auth.service.GoogleLoginService;
import Idea.Archieve.IdeaArchieve.global.config.oauth2.PrincipalOauth2UserService;
import Idea.Archieve.IdeaArchieve.global.filter.JwtRequestFilter;
import Idea.Archieve.IdeaArchieve.global.security.handler.CustomAccessDeniedHandler;
import Idea.Archieve.IdeaArchieve.global.security.handler.CustomAuthenticationEntryPointHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
    private final PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/email/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/member/**").authenticated()
                .antMatchers("/post/**").authenticated()
                .antMatchers("/post/comment/**").authenticated()
                .anyRequest().authenticated();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .authenticationEntryPoint(new CustomAuthenticationEntryPointHandler());
        http
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http
                .oauth2Login()
                .userInfoEndpoint()
                .userService(principalOauth2UserService);

        return http.build();
    }
}
