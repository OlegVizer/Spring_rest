package preproject.kata314.service;

import preproject.kata314.model.Role;

import java.util.List;

public interface RoleService {

  Role getRole(Long id);

  void addRole(Role role);

  List<Role> getRoles();
}
