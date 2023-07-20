package Idea.Archive.IdeaArchive.global.security;

import Idea.Archive.IdeaArchive.global.filter.ExceptionHandlerFilter;
import Idea.Archive.IdeaArchive.global.filter.JwtRequestFilter;
import Idea.Archive.IdeaArchive.global.security.handler.CustomAccessDeniedHandler;
import Idea.Archive.IdeaArchive.global.security.handler.CustomAuthenticationEntryPointHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
    private final ExceptionHandlerFilter exceptionHandlerFilter;

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
                .csrf().disable()
                .cors();
        http
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()

                // health
                .antMatchers(HttpMethod.GET, "/health").permitAll()

                // email
                .antMatchers(HttpMethod.POST, "/email/send").permitAll()
                .antMatchers(HttpMethod.HEAD, "/email").permitAll()

                // auth
                .antMatchers(HttpMethod.POST,"/auth/signup").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .antMatchers(HttpMethod.PATCH, "/auth").permitAll()
                .antMatchers(HttpMethod.DELETE, "/auth").authenticated()
                .antMatchers(HttpMethod.GET, "/google/login").permitAll()
                .antMatchers(HttpMethod.GET, "/github/login").permitAll()
                .antMatchers(HttpMethod.GET, "/kakao/login").permitAll()

                // post
                .antMatchers(HttpMethod.POST, "/post/write").authenticated()
                .antMatchers(HttpMethod.GET, "/post").permitAll()
                .antMatchers(HttpMethod.GET, "/post/{post_id}").permitAll()
                .antMatchers(HttpMethod.PATCH, "/post/{post_id}").authenticated()
                .antMatchers(HttpMethod.DELETE, "/post/{post_id}").authenticated()
                .antMatchers(HttpMethod.POST, "/post/search").permitAll()
                .antMatchers(HttpMethod.POST, "/post/category").permitAll()
                .antMatchers(HttpMethod.POST, "/post/{post_id}/heart").authenticated()
                .antMatchers(HttpMethod.GET, "/post/popular").permitAll()

                // comment
                .antMatchers(HttpMethod.POST, "/post/comment/{post_id}").authenticated()
                .antMatchers(HttpMethod.PATCH, "/post/comment/{post_id}").authenticated()
                .antMatchers(HttpMethod.DELETE, "/post/comment/{post_id}").authenticated()

                // member
                .antMatchers(HttpMethod.GET, "/member").authenticated()
                .antMatchers(HttpMethod.DELETE, "/member").authenticated()
                .antMatchers(HttpMethod.DELETE, "/member/oauth").authenticated()
                .antMatchers(HttpMethod.PATCH, "/member/find-password").permitAll()
                .antMatchers(HttpMethod.PATCH, "/member/name").authenticated()
                .antMatchers(HttpMethod.GET, "/member/posts").authenticated()
                .antMatchers(HttpMethod.GET, "/member/hearts-posts").authenticated()

                // image
                .antMatchers(HttpMethod.POST, "/img").authenticated()
                .antMatchers(HttpMethod.PATCH, "/img").authenticated()
                .antMatchers(HttpMethod.DELETE, "/img").authenticated()

                // notice
                .antMatchers(HttpMethod.GET, "/admin/notice").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/admin/notice/{notice_id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/notice/write").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/admin/notice/{notice_id}").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.GET, "member/notice").hasAuthority("MEMBER")
                .antMatchers(HttpMethod.GET, "member/notice/{notice_id}").hasAuthority("MEMBER")

                .anyRequest().authenticated();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .authenticationEntryPoint(new CustomAuthenticationEntryPointHandler());
        http
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, JwtRequestFilter.class);

        return http.build();
    }
}