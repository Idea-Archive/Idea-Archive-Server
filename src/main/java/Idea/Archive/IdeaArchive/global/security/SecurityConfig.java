package Idea.Archive.IdeaArchive.global.security;

import Idea.Archive.IdeaArchive.global.filter.JwtRequestFilter;
import Idea.Archive.IdeaArchive.global.security.handler.CustomAccessDeniedHandler;
import Idea.Archive.IdeaArchive.global.security.handler.CustomAuthenticationEntryPointHandler;
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
import org.springframework.web.cors.CorsUtils;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

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
                .csrf().and()
                .cors().disable();
        http
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/email/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/google/**").permitAll()
                .antMatchers("/github/**").permitAll()
                .antMatchers("/kakao/**").permitAll()

                .antMatchers("/member/**").authenticated()
                .antMatchers("/post/**").authenticated()
                .antMatchers("/post/comment/**").authenticated()
                .antMatchers("/img/**").authenticated()

                .antMatchers("/member/notice/**").hasAuthority("MEMBER")
                .antMatchers("/admin/notice/**").hasAuthority("ADMIN")
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
        return http.build();
    }
}