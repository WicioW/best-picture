package com.project.bestpicture.user.domain;

import com.project.bestpicture.configuration.BaseEntity;
import com.project.bestpicture.movierating.domain.MovieRating;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "users")
public class User extends BaseEntity {

  @Column(nullable = false,unique = true, updatable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @OneToMany(mappedBy = "user")
  Set<MovieRating> movieRatings;

  public User() {}

  public User(String username, String password) {
    this.username = username;
    this.password = BCrypt.hashpw(password, BCrypt.gensalt(13));
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public boolean hasMatchingPassword(String incomingPassword) {
    return BCrypt.checkpw(incomingPassword, this.password);
  }

  public String toLogString() {
    return "User{" +
            "username='" + username + '\'' +
            '}';
  }
}
