package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        DataSource dataSource;

        // private ImplementsUserDetailsService userDetailsService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http
                                .authorizeRequests()
                                .antMatchers("/css/**", "/img/**", "/register/**", "/jogo/**",
                                                "/admin/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                                .and()
                                .formLogin(form -> form
                                                .loginPage("/auth")
                                                .defaultSuccessUrl("/home", true)
                                                .permitAll())
                                .logout(logout -> logout.logoutUrl("/auth/logout"));
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                UserDetails userAdmin = User.builder()
                                .username("admin")
                                .password(encoder.encode("admin123"))
                                .roles("ADMIN")
                                .build();

                UserDetails userCris = User.builder()
                                .username("cris")
                                .password(encoder.encode("12345"))
                                .roles("USER")
                                .build();

                auth.jdbcAuthentication()
                                .dataSource(dataSource)
                                .passwordEncoder(encoder)
                // .withUser(userAdmin)
                // .withUser(userCris)
                ;
                // auth.userDetailsService(userDetailsService)
                // .passwordEncoder(encoder);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

}
