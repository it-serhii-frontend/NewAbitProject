package com.abit.Abit.config;

import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@EnableAutoConfiguration
@EnableWebSecurity
@Configuration
@ComponentScan
@EnableWebMvc
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
