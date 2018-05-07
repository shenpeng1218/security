package com.moss.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private CustomerSuccessHandler customerSuccessHandler;

    @Autowired
    private CustomerFailureHandler customerFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*http.formLogin().loginPage("/login.html").loginProcessingUrl("/login")
//                .successHandler(customerSuccessHandler)
                .and()
                .authorizeRequests().antMatchers("/login.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();*/
        http.formLogin().loginPage("/authtication/require").loginProcessingUrl("/login")
                .successHandler(customerSuccessHandler)
                .failureHandler(customerFailureHandler)
                .and()
                .authorizeRequests().antMatchers("/authtication/require", "/login.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}
