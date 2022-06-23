package com.project.bestpicture.user.api;

import com.project.bestpicture.user.domain.UserFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "Users controller")
public class RestUserController {

  private final UserFacade userFacade;

  public RestUserController(UserFacade userFacade) {
    this.userFacade = userFacade;
  }

  @PostMapping("/register")
  @Operation(summary = "Register new user")
  public ResponseEntity<UserDto> registerUser(@RequestBody CreateUserDto registerUser) {
    return ResponseEntity.ok(userFacade.createUser(registerUser));
  }
}
