package es.unican.palaciosj.empresariales.polaflix_jaime.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.palaciosj.empresariales.polaflix_jaime.domain.Category;
import es.unican.palaciosj.empresariales.polaflix_jaime.domain.Creator;
import es.unican.palaciosj.empresariales.polaflix_jaime.domain.Serie;
import es.unican.palaciosj.empresariales.polaflix_jaime.repositories.CategoryRepository;
import es.unican.palaciosj.empresariales.polaflix_jaime.repositories.SerieRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
    @JsonView(JsonViews.SerieView.class)
    public Iterable<Serie> getAllSeries() {
        return sr.findAll();
    }

    // Get series by initial letter
    @GetMapping(params = "initial")
    @JsonView(JsonViews.SerieView.class)
    public Iterable<Serie> getSeriesByInitialLetter(@RequestParam("initial") char initialLetter) {
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
    @PostMapping(params = {"title", "description", "category", "creatorName", "creatorSurname"})
    @JsonView(JsonViews.SerieView.class)
    @Transactional
    public ResponseEntity<?> addSerie(@RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("category") String category, 
                                        @RequestParam("creatorName") String creatorName, @RequestParam("creatorSurname") String creatorSurname) {
        ResponseEntity<?> result;

        if (!sr.findByTitle(title).isEmpty()) {
            result = ResponseEntity.badRequest().body("Serie already exists");
        } else {
            Category c = cr.findByName(category);
            if (c == null) {
                result = ResponseEntity.badRequest().body("Category does not exist");
            }
            Serie serie = new Serie(title, description, c);
            Creator cr = new Creator(creatorName, creatorSurname);
            serie.addCreator(cr);
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
