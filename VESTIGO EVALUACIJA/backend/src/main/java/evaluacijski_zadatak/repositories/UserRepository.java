package evaluacijski_zadatak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import evaluacijski_zadatak.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Boolean existsByUsername(String username);
    User findByUsername(String username);
}
