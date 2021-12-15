package com.example.online_seminar.config;

<<<<<<< HEAD
import com.example.demo.util.Role;
import lombok.RequiredArgsConstructor;
=======
import com.example.online_seminar.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> e43b291991cbfcdf627d920b2686fd24fc21e08d
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

import static com.example.demo.util.Role.*;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

<<<<<<< HEAD
    private final UserDetailsService userDetailsService;
=======
    public SecurityConfig(
            @Autowired
            UserDetailsServiceImpl userDetailsService
    ){
        this.userDetailsService = userDetailsService;
    }

    private UserDetailsService userDetailsService;
>>>>>>> e43b291991cbfcdf627d920b2686fd24fc21e08d

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 「/js/**」を追加
        web.ignoring().antMatchers(
                "/js/**", "/css/**", "/img/**", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 「/register」を追加
                .antMatchers("/login").permitAll()
//                 「/admin」は、ADMINユーザだけアクセス可能にします
                .antMatchers("/student/**").hasRole(STUDENT.name())
//                .antMatchers("/teacher/**").hasRole(TEACHER.name())
                .antMatchers("/teacher/**").hasAnyRole(TEACHER.name(), ADMIN.name())
                .antMatchers("/employee/**").hasRole(EMPLOYEE.name())
                .antMatchers("/admin/**").hasRole(ADMIN.name())
//                .antMatchers("/teacher/**").hasRole(ADMIN.name())
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
