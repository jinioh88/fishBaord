package com.fishing.fishboard.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    FishUsersService fishUsersService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/guest/**").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN");
        http
                .formLogin().loginPage("/login");
        http.exceptionHandling().accessDeniedPage("/accessDenied");
        http.logout().logoutUrl("/logout").invalidateHttpSession(true);
        http.userDetailsService(fishUsersService);
    }


}
