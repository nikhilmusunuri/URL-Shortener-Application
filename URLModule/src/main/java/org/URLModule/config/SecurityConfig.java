package org.URLModule.config;

import org.URLModule.service.URLShortenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	URLShortenService userservice;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userservice);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/url-shortener/shortenURL").hasAuthority("user")
                .antMatchers("/testapicall/**").hasAuthority("user")
                .antMatchers(HttpMethod.POST,"/url-shortener/singup/**").hasAuthority("service")
                .and()
                .httpBasic();
    }
}
