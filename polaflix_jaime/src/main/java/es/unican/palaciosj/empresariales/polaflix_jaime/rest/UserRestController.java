package es.unican.palaciosj.empresariales.polaflix_jaime.rest;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.palaciosj.empresariales.polaflix_jaime.domain.BankAccount;
import es.unican.palaciosj.empresariales.polaflix_jaime.domain.Bill;
import es.unican.palaciosj.empresariales.polaflix_jaime.domain.Chapter;
import es.unican.palaciosj.empresariales.polaflix_jaime.domain.Serie;
import es.unican.palaciosj.empresariales.polaflix_jaime.domain.User;
import es.unican.palaciosj.empresariales.polaflix_jaime.domain.Views;
import es.unican.palaciosj.empresariales.polaflix_jaime.repositories.SerieRepository;
import es.unican.palaciosj.empresariales.polaflix_jaime.repositories.UserRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "users")
public class UserRestController {
    
    private UserRepository ur;
    private SerieRepository sr;

    @Autowired
    public UserRestController(UserRepository ur, SerieRepository sr) {
        this.ur = ur;
        this.sr = sr;
    }

    // Get all users
    @GetMapping()
    @JsonView(JsonViews.UserView.class)
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok(ur.findAll());
    }

    // Get user by id
    @GetMapping(value = "/{id}")
    @JsonView(JsonViews.UserView.class)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        Optional<User> u = ur.findById(id);
        ResponseEntity<User> result;

        if (u.isPresent()) {
            result = ResponseEntity.ok(u.get());
        } else {
            result = ResponseEntity.notFound().build();
        }

        return result;
    }

    // Search user by email
    @GetMapping(params = "email")
    @JsonView(JsonViews.UserView.class)
    public ResponseEntity<User> getUserEmail(@RequestParam("email") String email) {
        User u = ur.findByEmail(email);
        ResponseEntity<User> result;

        if (u != null) {
            result = ResponseEntity.ok(u);
        } else {
            result = ResponseEntity.notFound().build();
        }

        return result;
    }

    // Add user to database by atributtes
    @PostMapping(params = {"email", "username", "password", "IBAN", "fixedFee"})
    @JsonView(JsonViews.UserView.class)
    public ResponseEntity<User> addUser(@RequestParam("email") String email, @RequestParam("username") String username, 
                                        @RequestParam("password") String password, @RequestParam("IBAN") String IBAN, 
                                        @RequestParam("fixedFee") Boolean fixedFee) {
        ResponseEntity<User> result;    

        if (ur.findByUsername(username) != null) {
            result = ResponseEntity.badRequest().build();
        } else {
            BankAccount bankAccount = new BankAccount(IBAN);
            User u = new User(email, username, password, bankAccount, fixedFee);
            ur.save(u);
            result = ResponseEntity.ok(u);
        }

        return result;
    }

    // Get user pending series
    @GetMapping(value ="/{id}/pending-series")
    @JsonView(JsonViews.SerieListView.class)
    public ResponseEntity<Iterable<Serie>> getPendingSeries(@PathVariable("id") long id) {
        ResponseEntity<Iterable<Serie>> result;
        Optional<User> u = ur.findById(id);

        if (u.isPresent()) {
            result = ResponseEntity.ok(u.get().getPendingSeries());
        } else {
            result = ResponseEntity.notFound().build();
        }

        return result;

    }

    // Add serie to user pending list
    @PostMapping(value = "/{id}/pending-series", params = {"idSerie"})
    @JsonView(JsonViews.UserView.class)
    public ResponseEntity<User> addPendingSerie(@PathVariable("id") long id, @RequestParam("idSerie") long idSerie) {
        ResponseEntity<User> result;
        Optional<User> u = ur.findById(id);
        Optional<Serie> s = sr.findById(idSerie);

        if (u.isPresent() && s.isPresent()) {
            u.get().addSerieToPending(s.orElseThrow());
            ur.save(u.get());
            result = ResponseEntity.ok(u.get());
        } else {
            result = ResponseEntity.notFound().build();
        }

        return result;
    }

    // Get user started series
    @GetMapping(value ="/{id}/started-series")
    @JsonView(JsonViews.SerieListView.class)
    public ResponseEntity<Iterable<Serie>> getStartedSeries(@PathVariable("id") long id) {
        ResponseEntity<Iterable<Serie>> result;
        Optional<User> u = ur.findById(id);

        if (u.isPresent()) {
            result = ResponseEntity.ok(u.get().getStartedSeries());
        } else {
            result = ResponseEntity.notFound().build();
        }

        return result;

    }
    
    // Get user finished series
    @GetMapping(value ="/{id}/finished-series")
    @JsonView(JsonViews.SerieListView.class)
    public ResponseEntity<Iterable<Serie>> getFinishedSeries(@PathVariable("id") long id) {
        ResponseEntity<Iterable<Serie>> result;
        Optional<User> u = ur.findById(id);

        if (u.isPresent()) {
            result = ResponseEntity.ok(u.get().getFinishedSeries());
        } else {
            result = ResponseEntity.notFound().build();
        }

        return result;

    }

    // Mark chapter as viewed
    @PostMapping(value = "/{id}/mark-chapter-viewed/{idSerie}/{numSeason}/{numChapter}")
    @JsonView(JsonViews.SerieViewsView.class)
    @Transactional
    public ResponseEntity<Views> markChapterViewed(@PathVariable("id") long id, @PathVariable("idSerie") long idSerie, 
                                                  @PathVariable("numSeason") int numSeason, @PathVariable("numChapter") int numChapter) {
        ResponseEntity<Views> result;
        Optional<User> u = ur.findById(id);
        Optional<Serie> s = sr.findById(idSerie);

        if (u.isPresent() && s.isPresent()) {
            Chapter c = s.orElseThrow().getSeason(numSeason).getChapter(numChapter);
            u.get().markChapterViewed(s.orElseThrow(), c);
            ur.save(u.get());
            result = ResponseEntity.ok(u.get().getSerieViews(s.orElseThrow()));
        } else {
            result = ResponseEntity.notFound().build();
        }

        return result;
    }

    // Get last chapter viewed for a given serie
    @GetMapping(value = "/{id}/last-chapter-viewed/{idSerie}")
    @JsonView(JsonViews.ChapterView.class)
    public ResponseEntity<Chapter> getLastChapterViewed(@PathVariable("id") long id, @PathVariable("idSerie") long idSerie) {
        ResponseEntity<Chapter> result;
        Optional<User> u = ur.findById(id);
        Optional<Serie> s = sr.findById(idSerie);

        if (u.isPresent() && s.isPresent()) {
            result = ResponseEntity.ok(u.get().getLastViewedChapter(s.orElseThrow()));
        } else {
            result = ResponseEntity.notFound().build();
        }

        return result;
    }

    // Get all chapters viewed for a given serie
    @GetMapping(value = "/{id}/views/{idSerie}")
    @JsonView(JsonViews.SerieViewsView.class)
    public ResponseEntity<Views> getViews(@PathVariable("id") long id, @PathVariable("idSerie") long idSerie) {
        ResponseEntity<Views> result;
        Optional<User> u = ur.findById(id);
        Optional<Serie> s = sr.findById(idSerie);

        if (u.isPresent() && s.isPresent()) {
            result = ResponseEntity.ok(u.get().getSerieViews(s.orElseThrow()));
        } else {
            result = ResponseEntity.notFound().build();
        }

        return result;
    }

    // Get bills
    @GetMapping(value = "/{id}/bills")
    @JsonView(JsonViews.BillView.class)
    public ResponseEntity<Iterable<Bill>> getBills(@PathVariable("id") long id) {
        ResponseEntity<Iterable<Bill>> result;
        Optional<User> u = ur.findById(id);

        if (u.isPresent()) {
            result = ResponseEntity.ok(u.get().getBills());
        } else {
            result = ResponseEntity.notFound().build();
        }

        return result;
    }

    // Get bill by date (precondition: date is in format dd/MM/yy)
    @GetMapping(value = "/{id}/bills", params = "date")
    @JsonView(JsonViews.BillView.class)
    public ResponseEntity<Bill> getBillByDate(@PathVariable("id") long id, @RequestParam("date") String date) {
        ResponseEntity<Bill> result;
        Optional<User> u = ur.findById(id);

        if (u.isPresent()) {
            // Convert date to Date
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
            Date dateConverted;
            try {
                dateConverted = new Date(formatter.parse(date).getTime());
                result = ResponseEntity.ok(u.get().getBillByDate(dateConverted));
            } catch (ParseException e) {
                result = ResponseEntity.badRequest().build();
            }
        } else {
            result = ResponseEntity.notFound().build();
        }

        return result;
    }
}
