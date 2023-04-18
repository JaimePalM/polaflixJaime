package es.unican.palaciosj.empresariales.polaflix_jaime.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.palaciosj.empresariales.polaflix_jaime.domain.Category;
import es.unican.palaciosj.empresariales.polaflix_jaime.domain.Serie;
import es.unican.palaciosj.empresariales.polaflix_jaime.repositories.CategoryRepository;
import es.unican.palaciosj.empresariales.polaflix_jaime.repositories.SerieRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(value = "series")
public class SerieRestController {

    SerieRepository sr;
    CategoryRepository cr;
    
    @Autowired
    public SerieRestController(SerieRepository sr, CategoryRepository cr) {
        this.sr = sr;
        this.cr = cr;
    }

    // Get all series
    @GetMapping()
    public Iterable<Serie> getSeries() {
        return sr.findAll();
    }

    // Get series by initial letter
    @GetMapping(value = "/search/{initialLetter}")
    public Iterable<Serie> getSeriesByInitialLetter(@PathVariable("initialLetter") char initialLetter) {
        return sr.findByInitial(initialLetter);
    }

    // Get series by id
    @GetMapping(value = "/{id}")
    @JsonView(JsonViews.SerieView.class)
    public ResponseEntity<Serie> getSerie(@PathVariable("id") long id) {
        Optional<Serie> s = sr.findById(id);
        ResponseEntity<Serie> result;

        if (s.isPresent()) {
            result = ResponseEntity.ok(s.get());
        } else {
            result = ResponseEntity.notFound().build();
        }

        return result;
    }

    // Add serie to database by atributtes
    @PostMapping(value = "/new/{title}/{description}/{category}")
    public ResponseEntity<?> addSerie(@PathVariable("title") String title, @PathVariable("description") String description, 
                                        @PathVariable("category") String category) {
        ResponseEntity<?> result;

        if (!sr.findByTitle(title).isEmpty()) {
            result = ResponseEntity.badRequest().body("Serie already exists");
        } else {
            Category c = cr.findByName(category);
            if (c == null) {
                result = ResponseEntity.badRequest().body("Category does not exist");
            }
            Serie serie = new Serie(title, description, c);
            sr.save(serie);
            result = ResponseEntity.ok(serie);
        }

        return result;
    }

    // Change serie category
    @PostMapping(value = "/{id}/changeCategory/{category}")
    public ResponseEntity<Serie> changeCategory(@PathVariable("id") long id, @PathVariable("category") String category) {
        ResponseEntity<Serie> result;

        Optional<Serie> s = sr.findById(id);
        if (s.isPresent()) {
            Category c = cr.findByName(category);
            if (c == null) {
                result = ResponseEntity.notFound().build();
            }
            s.get().setCategory(c);
            sr.save(s.get());
            result = ResponseEntity.ok(s.get());
        } else {
            result = ResponseEntity.notFound().build();
        }

        return result;
    }
     
    
}
