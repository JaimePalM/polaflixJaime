package es.unican.palaciosj.empresariales.polaflix_jaime.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.unican.palaciosj.empresariales.polaflix_jaime.domain.User;

/**
 * User repository with CRUD operations
 */
@Repository("userRepo")
public interface UserRepository extends JpaRepository<User, String> {
        
    User findByUsername(String username);
}