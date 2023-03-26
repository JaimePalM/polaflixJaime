package es.unican.palaciosj.empresariales.polaflix_jaime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.unican.palaciosj.empresariales.polaflix_jaime.domain.Category;

/**
 * Category repository with CRUD operations
 */
@Repository("categoryRepo")
public interface CategoryRepository extends JpaRepository<Category, String> {
    
    Category findByName(String name);
}