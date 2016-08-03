package org.personal.mason.study.configuration;

import static org.springframework.security.config.http.SessionCreationPolicy.IF_REQUIRED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by meidongxu on 6/22/15.
 */
@Configuration
@EnableWebSecurity
@EnableRedisHttpSession
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.csrf().disable()
                .formLogin().loginPage("/index.html").defaultSuccessUrl("/chat.html").permitAll()
                .and().logout().logoutSuccessUrl("/index.html").permitAll()
                .and().authorizeRequests()
                        .antMatchers("/js/**", "/dep/**", "/images/**", "/css/**", "/index.html", "/").permitAll()
                        .antMatchers("/stats").hasRole("ADMIN")
                        .anyRequest().authenticated()
                .and().sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true)
                        .expiredUrl("/index.html?expired")
                .and().sessionCreationPolicy(IF_REQUIRED).sessionFixation().newSession();
        // @formatter:on
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("baby")
                .password("baby")
                .roles("USER")
                .and()
                .withUser("mason")
                .password("awesome")
                .roles("USER", "ADMIN");
    }
}
