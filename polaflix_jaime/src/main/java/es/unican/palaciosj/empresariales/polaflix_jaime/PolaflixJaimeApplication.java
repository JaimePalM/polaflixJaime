package es.unican.palaciosj.empresariales.polaflix_jaime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"es.unican.palaciosj.empresariales.domain"})
@EnableJpaRepositories(basePackages = {"es.unican.palaciosj.empresariales.repositories"})
public class PolaflixJaimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolaflixJaimeApplication.class, args);
	}

}
