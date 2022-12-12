package preproject.kata314.init;

import org.springframework.stereotype.Component;
import preproject.kata314.model.Role;
import preproject.kata314.model.User;
import preproject.kata314.service.RoleService;
import preproject.kata314.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class InitUsers {

  private final RoleService roleService;
  private final UserService userService;


  public InitUsers(RoleService roleService, UserService userService) {
    this.roleService = roleService;
    this.userService = userService;
  }

  @PostConstruct
  public void init() {
    Role roleAdmin = new Role("ROLE_ADMIN");
    roleService.addRole(roleAdmin);
    Role roleUser = new Role("ROLE_USER");
    roleService.addRole(roleUser);
    User user = new User("user@mail.ru", "user", 30, "user", "user");
    user.getRoles().add(roleService.getRole(2L));
    User admin = new User("admin@mail.ru", "admin", 35, "admin", "admin");
    admin.getRoles().add(roleService.getRole(1L));
    admin.getRoles().add(roleService.getRole(2L));
    userService.saveUser(admin);
    userService.saveUser(user);
  }
}
