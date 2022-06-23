package com.project.bestpicture.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.LazyCsrfTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomAuthenticationProvider authenticationProvider;
  private final AuthenticationSuccessHandlerImpl authenticationSuccessHandler;
  private static final String[] AUTH_WHITELIST = {
          // -- Swagger UI v2
          "/v2/api-docs",
          "/swagger-resources",
          "/swagger-resources/**",
          "/configuration/ui",
          "/configuration/security",
          "/swagger-ui.html",
          "/webjars/**",
          // -- Swagger UI v3 (OpenAPI)
          "/v3/api-docs/**",
          "/swagger-ui/**",
          "/user/register"
  };

  public SecurityConfig(
          CustomAuthenticationProvider authenticationProvider,
          AuthenticationSuccessHandlerImpl authenticationSuccessHandler) {
    this.authenticationProvider = authenticationProvider;
    this.authenticationSuccessHandler = authenticationSuccessHandler;
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(this.authenticationProvider);
  }

  private AuthenticationFilter filter() throws Exception {
    AuthenticationFilter customAuthenticationFilter =
            new AuthenticationFilter(authenticationManagerBean());
    customAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
    return customAuthenticationFilter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors();
    http.csrf()
            .disable()
            .addFilterBefore(filter(), RequestHeaderAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers(AUTH_WHITELIST)
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(this.authenticationEntryPoint())
            .and()
            .logout()
            .logoutUrl("/user/logout")
            .permitAll()
            .logoutSuccessHandler(
                    (httpServletRequest, httpServletResponse, authentication) -> {
                      httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                    })
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID");
  }

  @Bean
  public CsrfTokenRepository csrfTokenRepository() {
    return new LazyCsrfTokenRepository(new HttpSessionCsrfTokenRepository());
  }

  @Bean
  public AuthenticationEntryPoint authenticationEntryPoint() {
    return new AuthenticationEntryPoint() {

      @Override
      public void commence(
              HttpServletRequest request,
              HttpServletResponse response,
              AuthenticationException authException)
              throws IOException, ServletException {
        var respEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, respEntity);
        out.flush();
      }
    };
  }
}
