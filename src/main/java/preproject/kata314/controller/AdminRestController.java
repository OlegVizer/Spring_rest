package preproject.kata314.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import preproject.kata314.model.Role;
import preproject.kata314.model.User;
import preproject.kata314.service.RoleService;
import preproject.kata314.service.UserService;

import java.util.List;

@RestController
public class AdminRestController {

  private final UserService userService;
  private final RoleService roleService;

  @Autowired
  public AdminRestController(UserService userService, RoleService roleService) {
    this.userService = userService;
    this.roleService = roleService;
  }

  @GetMapping("/admin")
  public ModelAndView getAdminPage() {
    return new ModelAndView("adminPage");
  }

  @GetMapping("api/admin")
  public ResponseEntity<List<User>> getAllUsers() {
    final List<User> users = userService.getAllUsers();

    return users != null && !users.isEmpty() ? new ResponseEntity<>(users, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }


  @GetMapping("api/admin/{id}")
  public ResponseEntity<User> getUser(@PathVariable("id") long id) {
    final User user = userService.getUser(id);
    return user != null ? new ResponseEntity<>(user, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping("api/admin")
  public ResponseEntity<User> newUser(@RequestBody User user) {
    userService.saveUser(user);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PatchMapping("api/admin/{id}")
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    userService.updateUser(user);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("api/admin/{id}")
  public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
    userService.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("api/roles")
  public ResponseEntity<List<Role>> getAllRoles() {
    return new ResponseEntity<>(roleService.getRoles(), HttpStatus.OK);
  }
}