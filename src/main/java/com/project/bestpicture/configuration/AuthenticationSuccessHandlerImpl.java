package com.project.bestpicture.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

  private final CustomAuthenticationProvider customAuthenticationProvider;

  public AuthenticationSuccessHandlerImpl(
      CustomAuthenticationProvider customAuthenticationProvider) {
    this.customAuthenticationProvider = customAuthenticationProvider;
  }

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    response
        .getWriter()
        .write(
            new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(customAuthenticationProvider.getLoggedUser()));
  }
}
