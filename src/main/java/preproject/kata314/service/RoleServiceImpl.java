package preproject.kata314.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preproject.kata314.model.Role;
import preproject.kata314.repository.RoleRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService{

  private final RoleRepository roleDAO;

  public RoleServiceImpl(RoleRepository roleDAO) {
    this.roleDAO = roleDAO;
  }

  @Override
  public Role getRole(Long id) {
    return roleDAO.getById(id);
  }

  @Override
  @Transactional
  public void addRole(Role role) {
    roleDAO.save(role);
  }

  @Override
  public List<Role> getRoles() {
    return roleDAO.findAll();
  }
}
