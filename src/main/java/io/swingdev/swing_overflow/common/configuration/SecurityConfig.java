package io.swingdev.swing_overflow.common.configuration;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private String audience = "swingoverflow.io";
    private String issuer = "https://dawiddominiak.eu.auth0.com/";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtWebSecurityConfigurer
            .forRS256(audience, issuer)
            .configure(http)
            .authorizeRequests()
            .antMatchers("/resources/**").authenticated()
            .antMatchers("/tags/**").authenticated()
            .antMatchers("/users/**").authenticated();
    }
}
