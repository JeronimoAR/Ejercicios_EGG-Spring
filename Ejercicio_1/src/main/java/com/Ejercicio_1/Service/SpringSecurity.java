package com.Ejercicio_1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImp).passwordEncoder(getEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/noticia/**").hasRole("ADMIN")
                .antMatchers("/user").hasRole("ADMIN")
                .and().formLogin().loginPage("/user/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/logincheck")
                .defaultSuccessUrl("/user/acces").permitAll()
        ;
    }

    @Bean
    public static BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
