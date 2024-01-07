package com.ademyschool.ademyschool.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws  Exception {
        MvcRequestMatcher.Builder mvcBuilder = new MvcRequestMatcher.Builder(introspector);
        http.csrf((csrf)->csrf.ignoringRequestMatchers("/saveMsg")
                        .ignoringRequestMatchers(PathRequest.toH2Console())
                        .ignoringRequestMatchers("/public/**"))
                .authorizeHttpRequests((request)->request
                        .requestMatchers(mvcBuilder.pattern("/dashboard")).authenticated()
                        .requestMatchers(mvcBuilder.pattern("/displayMessages")).hasRole("ADMIN")
                        .requestMatchers(mvcBuilder.pattern("/displayProfile")).authenticated()
                        .requestMatchers(mvcBuilder.pattern("/closeMsg/**")).hasRole("ADMIN")
                        .requestMatchers(mvcBuilder.pattern("/login")).permitAll()
                        .requestMatchers(mvcBuilder.pattern("/home")).permitAll()
                        .requestMatchers(mvcBuilder.pattern("")).permitAll()
                        .requestMatchers(mvcBuilder.pattern("/")).permitAll()
                        .requestMatchers(mvcBuilder.pattern("/holidays/**")).permitAll()
                        .requestMatchers(mvcBuilder.pattern("/saveMsg")).permitAll()
                        .requestMatchers(mvcBuilder.pattern("/courses")).permitAll()
                        .requestMatchers(mvcBuilder.pattern("/about")).permitAll()
                        .requestMatchers(mvcBuilder.pattern("/logout")).permitAll()
                        .requestMatchers(mvcBuilder.pattern("/contact")).permitAll()
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .requestMatchers(mvcBuilder.pattern("/assets/**")).permitAll()
                        .requestMatchers(mvcBuilder.pattern("/public/**")).permitAll())
                .formLogin((formLogin)->formLogin.loginPage("/login")
                        .defaultSuccessUrl("/dashboard")
                        .failureUrl("/login?error=true").permitAll())
                .logout((log)->log.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults());

      return  http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
