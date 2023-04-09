package es.unican.palaciosj.empresariales.polaflix_jaime.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.unican.palaciosj.empresariales.polaflix_jaime.domain.BankAccount;
import es.unican.palaciosj.empresariales.polaflix_jaime.domain.Serie;
import es.unican.palaciosj.empresariales.polaflix_jaime.domain.User;
import es.unican.palaciosj.empresariales.polaflix_jaime.repositories.SerieRepository;
import es.unican.palaciosj.empresariales.polaflix_jaime.repositories.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class UserRestController {
    
    private UserRepository ur;
    private SerieRepository sr;

    @Autowired
    public UserRestController(UserRepository ur, SerieRepository sr) {
        this.ur = ur;
        this.sr = sr;
    }

    // Get all users
    @GetMapping(value = "users")
    public Iterable<User> getUsers() {
        return ur.findAll();
    }

    // Get user by id
    @GetMapping(value = "users/{id}")
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

    // Add user to database by atributtes
    @PostMapping(value = "users/new/{email}/{username}/{password}/{IBAN}/{fixedFee}")
    public ResponseEntity<User> addUser(@PathVariable("email") String email, @PathVariable("username") String username, 
                                        @PathVariable("password") String password, @PathVariable("IBAN") String IBAN, 
                                        @PathVariable("fixedFee") boolean fixedFee) {
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

    // Add serie to user pending list
    @PostMapping(value = "users/{id}/add-serie-pending/{idSerie}")
    public ResponseEntity<User> addPendingSerie(@PathVariable("id") long id, @PathVariable("idSerie") long idSerie) {
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
    
}
