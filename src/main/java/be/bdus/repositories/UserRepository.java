package be.bdus.repositories;

import be.bdus.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional <User> findByUsername( String username);

}