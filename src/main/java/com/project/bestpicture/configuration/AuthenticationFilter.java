package com.project.bestpicture.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private ObjectMapper objectMapper;
  private AuthenticationManager authManager;

  public AuthenticationFilter(AuthenticationManager authManager) {
    this.objectMapper = new ObjectMapper();
    this.authManager = authManager;
    setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler());
    setRequiresAuthenticationRequestMatcher(
        new AntPathRequestMatcher("/**" + "/user/login", HttpMethod.POST.name()));
    setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler());
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      var reader = request.getReader();
      var json = reader.lines().collect(Collectors.joining("\n"));
      var authRequest = objectMapper.readTree(json);
      var token =
          new UsernamePasswordAuthenticationToken(
              authRequest.get("username").asText().toLowerCase(),
              authRequest.get("password").asText());
      setDetails(request, token);
      return authManager.authenticate(token);
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }
}
