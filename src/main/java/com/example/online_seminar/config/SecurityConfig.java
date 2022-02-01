package com.example.online_seminar.config;

import com.example.online_seminar.entity.util.Role;
import lombok.RequiredArgsConstructor;
import com.example.online_seminar.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.example.online_seminar.entity.util.Role.*;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 「/js/**」を追加
        web.ignoring().antMatchers(
                "/js/**", "/css/**", "/img/**", "/webjars/**","/layout/**","/fragments/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 「/register」を追加
                .antMatchers("/login").permitAll()
                .antMatchers("/student/*").hasRole("0")
                .antMatchers("/users/teacher/**").hasAnyRole("1","3")
                .antMatchers("/groups/teacher/**").hasAnyRole("1","3")
                .antMatchers("/employee/*").hasRole("2")
                .antMatchers("/admin/*").hasRole("3")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .rememberMe();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        // userDetailsServiceを使用して、DBからユーザを参照できるようにします
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
