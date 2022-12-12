package preproject.kata314.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import preproject.kata314.model.User;

@RestController

public class UserRestController {

  @GetMapping("/user")
  public ModelAndView getUserPage() {
    return new ModelAndView("userPage");
  }

  @GetMapping("/api/user")
  public ResponseEntity<User> getUser(Authentication auth) {
    return new ResponseEntity<>((User) auth.getPrincipal(), HttpStatus.OK);
  }
}
