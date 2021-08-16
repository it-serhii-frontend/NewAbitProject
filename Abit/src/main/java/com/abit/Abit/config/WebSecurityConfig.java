package com.abit.Abit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin1").roles("ADMIN").and().withUser("user").password("user1").roles("USER");

    }
    @Override
    protected void configure(HttpSecurity http) throws  Exception{
        http.authorizeRequests()
                .antMatchers("/**").hasRole("ADMIN")
                .antMatchers("/all").hasRole("USER")
                .antMatchers("/main").hasRole("USER")
                .antMatchers("/{id}/edit").hasRole("ADMIN")
                .antMatchers("/student").hasRole("ADMIN")
                .antMatchers("/student/{id}").hasRole("ADMIN")
                .and().formLogin();
    }

    @Bean
    public PasswordEncoder encoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
