package preproject.kata314.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import preproject.kata314.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findRoleByName(String name);
}
