package es.unican.palaciosj.empresariales.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.unican.palaciosj.empresariales.domain.User;

/**
 * User repository with CRUD operations
 */
@Repository("userRepo")
public interface UserRepository extends JpaRepository<User, String> {
        
    User findByUsername(String username);
}