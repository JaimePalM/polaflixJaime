package es.unican.palaciosj.empresariales.polaflix_jaime.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.palaciosj.empresariales.polaflix_jaime.domain.Serie;
import es.unican.palaciosj.empresariales.polaflix_jaime.repositories.CategoryRepository;
import es.unican.palaciosj.empresariales.polaflix_jaime.repositories.SerieRepository;
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
    @CrossOrigin(origins = "*")
    public Iterable<Serie> getAllSeries() {
        return sr.findAll();
    }

    // Get series by initial letter
    @GetMapping(params = "initial")
    @JsonView(JsonViews.SerieView.class)
    @CrossOrigin(origins = "*")
    public Iterable<Serie> getSeriesByInitialLetter(@RequestParam("initial") char initialLetter) {
        return sr.findByInitial(initialLetter);
    }

    // Get series by id
    @GetMapping(value = "/{id}")
    @JsonView(JsonViews.SerieView.class)
    @CrossOrigin(origins = "*")
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
    
}
