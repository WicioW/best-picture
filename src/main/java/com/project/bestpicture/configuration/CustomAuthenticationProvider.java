package com.project.bestpicture.configuration;

import com.project.bestpicture.user.api.UserDto;
import com.project.bestpicture.user.domain.User;
import com.project.bestpicture.user.domain.UserMapper;
import com.project.bestpicture.user.domain.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final UserRepository userRepository;
  private final HttpServletRequest request;

  public CustomAuthenticationProvider(UserRepository userRepository, HttpServletRequest request) {
    this.userRepository = userRepository;
    this.request = request;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String login = authentication.getName();
    String password = authentication.getCredentials().toString();

    Optional<User> optionalUser = userRepository.findByUsername(login);
    if (optionalUser.isPresent() && optionalUser.get().hasMatchingPassword(password)) {
      return new UsernamePasswordAuthenticationToken(login, password, Collections.EMPTY_LIST);
    } else {
      throw new BadCredentialsException("Wrong Credentials");
    }
  }

  public UserDto getLoggedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    String login = authentication.getName();

    Optional<User> op = userRepository.findByUsername(login);
    if (op.isPresent()) return UserMapper.single.apply(op.get());
    return null; // we do not want to return anything if somehow we cannot find user and he is
    // logged, may be a bug
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
