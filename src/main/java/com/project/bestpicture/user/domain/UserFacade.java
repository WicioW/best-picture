package com.project.bestpicture.user.domain;

import com.project.bestpicture.exception.NotFoundException;
import com.project.bestpicture.exception.UsernameAlreadyExistsException;
import com.project.bestpicture.user.api.CreateUserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserFacade {
  private final System.Logger LOGGER = System.getLogger(this.getClass().getName());

  private final UserRepository userRepository;

  public UserFacade(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public User createUser(CreateUserDto createUserDto) {
    var newUser = new User(createUserDto.username, createUserDto.password);
    Optional<User> optionalUser = userRepository.findByUsername(createUserDto.username);
    if (optionalUser.isPresent()) throw new UsernameAlreadyExistsException();
    User user = userRepository.saveAndFlush(newUser);
    LOGGER.log(System.Logger.Level.INFO, "Created user:" + user.toLogString());
    return user;
  }

  public User getLoggedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return userRepository
        .findByUsername(authentication.getPrincipal().toString())
        .orElseThrow(() -> new NotFoundException("User not logged"));
  }
}
