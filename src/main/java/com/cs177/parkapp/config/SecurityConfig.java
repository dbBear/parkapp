package com.cs177.parkapp.config;

import com.cs177.parkapp.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@AllArgsConstructor
@Configuration
//todo re-enable when security ready
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private UserService userService;
  private BCryptPasswordEncoder passwordEncoder;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //todo remove h2 access when in MySql
    http
        .authorizeRequests()
          .antMatchers("/","/index",
              "/css/**",
              "/js/**",
              "/img/**",
              "/webjars/**",
              "/registration"
              )
            .permitAll()
          // for h2 console access
          .antMatchers("/h2-console/**").permitAll()

        .and()
          .formLogin()
            .loginPage("/login").permitAll()
        .and()
          .logout()
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/logout-success").permitAll();

    // for h2 console access
    http.csrf().disable()
        .headers().frameOptions().disable();

  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userService);
    provider.setPasswordEncoder(passwordEncoder);
    return provider;
  }
}
