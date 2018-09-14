package com.fishing.fishboard.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    FishUsersService fishUsersService;

    @Autowired
    DataSource dataSource;

    private PersistentTokenRepository getJDBCRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers(new AntPathRequestMatcher("/**.html"))
                .requestMatchers(new AntPathRequestMatcher("/static/**"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/boards/list").permitAll()
                    .antMatchers("/boards/register").hasAnyRole("USER","ADMIN")
                    .antMatchers("/johang/list").permitAll()
                    .antMatchers("/johang/register").hasAnyRole("USER","ADMIN")
                    .antMatchers("/member/join").permitAll();

        http
                .formLogin().loginPage("/login").successHandler(new LoginSuccessHandler());
        http.exceptionHandling().accessDeniedPage("/accessDenied");
        http.logout().logoutUrl("/logout").invalidateHttpSession(true);
        http.rememberMe().key("fish")
                .userDetailsService(fishUsersService)
                .tokenRepository(getJDBCRepository())
                .tokenValiditySeconds(60*60*24);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(fishUsersService).passwordEncoder(passwordEncoder());
    }
}
