package preproject.kata314.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import preproject.kata314.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findUserByEmail(String email);
}
