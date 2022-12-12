package preproject.kata314.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preproject.kata314.model.User;
import preproject.kata314.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  private final UserRepository userDAO;
  private final PasswordEncoder passwordEncoder;

  public UserServiceImpl(UserRepository userDAO, PasswordEncoder passwordEncoder) {
    this.userDAO = userDAO;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public List<User> getAllUsers() {
    return userDAO.findAll();
  }

  @Override
  public User getUser(long id) {
    return userDAO.findById(id).orElse(null);
  }

  @Override
  public User getUserByUsername(String username) {
    return userDAO.findUserByEmail(username);
  }

  @Override
  @Transactional
  public void saveUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userDAO.save(user);
  }

  @Override
  @Transactional
  public void updateUser(User user) {
    if (!user.getPassword().equals(userDAO.getById(user.getId()).getPassword())) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
    userDAO.save(user);
  }

  @Override
  @Transactional
  public void deleteUser(long id) {
    userDAO.deleteById(id);
  }

}
