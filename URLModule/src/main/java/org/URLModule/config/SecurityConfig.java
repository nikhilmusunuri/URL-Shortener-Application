package org.URLModule.config;


import org.URLModule.service.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	UserAuth userAuth;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/url-shortener/shortenurl").hasAuthority("user")
                .antMatchers("/US/getmyUrlClicks/**").hasAuthority("user")
                .antMatchers("/US/short/**").hasAuthority("user")
                .and()
                .httpBasic();
    }
}
