package com.example.flowershopproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers()
                .frameOptions()
                .disable();
        http.authorizeRequests()
                .antMatchers("/shop/orderList", "/shop/order", "/shop/accountInfo")
                .access("hasAnyRole('ROLE_CLIENT', 'ROLE_ADMIN')");
        http.authorizeRequests()
                .antMatchers("/shop/product")
                .access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
        http.authorizeRequests()
                .and()
                .formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/shop/login")
                .defaultSuccessUrl("/shop/accountInfo")
                .failureUrl("/shop/login?error=true")
                .usernameParameter("userName")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/shop/logout")
                .logoutSuccessUrl("/");
    }
}
