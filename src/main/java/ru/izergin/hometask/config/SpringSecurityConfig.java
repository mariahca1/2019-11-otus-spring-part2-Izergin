package ru.izergin.hometask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/create").hasRole("ADMIN")
                .antMatchers("/update").hasRole("ADMIN")
                .antMatchers("/book/*").hasRole("ADMIN")
                .antMatchers("/deleteComment").hasRole("ADMIN")
                .antMatchers("/addComment").hasRole("ADMIN")
                .antMatchers("/deleteAuthor").hasRole("ADMIN")
                .antMatchers("/addAuthor").hasRole("ADMIN")

                .antMatchers("/login*").permitAll()
                .antMatchers("/main").permitAll() //главную форму сделал доступной всем, пусть будет
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and().anonymous().authorities( "ROLE_ANONYMOUS" ).principal( new AnonymousUD() )

                .and()
                .formLogin()
                .failureForwardUrl("/error")
                .and().rememberMe()
                .and()
                .logout().logoutSuccessUrl("/main")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("adm").password(passwordEncoder().encode("pass")).roles("ADMIN")
                .and()
                .withUser("user").password(passwordEncoder().encode("pass")).roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
