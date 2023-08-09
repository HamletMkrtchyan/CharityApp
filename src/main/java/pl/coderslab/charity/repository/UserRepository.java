package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);
    boolean existsByEmail(String email);

    List<User> findAllByRoles(Role role);
}
