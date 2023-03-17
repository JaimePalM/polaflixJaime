package es.unican.palaciosj.empresariales.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.unican.palaciosj.empresariales.domain.Serie;

/**
 * Serie repository with CRUD operations
 */
@Repository("serieRepo")
public interface SerieRepository extends JpaRepository<Serie, Long>{

    Serie findById(long id);
    
    List<Serie> findByInitial(String initial);
}
