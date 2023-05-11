package com.example.ukrposhtatest.config;

import com.example.ukrposhtatest.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ADMIN = Role.RoleName.ADMIN.name();
    private static final String MANAGER = Role.RoleName.MANAGER.name();
    private static final String PROGRAMMER = Role.RoleName.PROGRAMMER.name();
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers(HttpMethod.GET, "/managers/all", "/programmers/all").hasRole(ADMIN)
                .antMatchers(HttpMethod.DELETE, "/managers/**").hasRole(ADMIN)
                .antMatchers(HttpMethod.PUT, "/managers/**").hasRole(ADMIN)
                .antMatchers(HttpMethod.GET, "/managers/{id}", "/managers/all/**").hasAnyRole(ADMIN, MANAGER)
                .antMatchers(HttpMethod.GET, "/programmers/{id}",
                        "programmers/all/**").hasAnyRole(ADMIN, PROGRAMMER)
                .antMatchers(HttpMethod.POST, "/projects/**",
                        "/teams/**", "/sprints/**").hasAnyRole(ADMIN, MANAGER)
                .antMatchers(HttpMethod.PATCH, "/projects/**",
                        "/teams/**", "/sprints/**").hasAnyRole(ADMIN, MANAGER)
                .antMatchers(HttpMethod.DELETE, "/projects/**",
                        "/teams/**", "/sprints/**").hasAnyRole(ADMIN, MANAGER)
                .antMatchers(HttpMethod.GET, "/projects/**",
                        "/teams/**", "/sprints/**").hasAnyRole(ADMIN, MANAGER, PROGRAMMER)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
