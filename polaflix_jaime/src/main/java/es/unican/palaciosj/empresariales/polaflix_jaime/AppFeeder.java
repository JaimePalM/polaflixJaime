package es.unican.palaciosj.empresariales.polaflix_jaime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import es.unican.palaciosj.empresariales.domain.BankAccount;
import es.unican.palaciosj.empresariales.domain.Category;
import es.unican.palaciosj.empresariales.domain.Chapter;
import es.unican.palaciosj.empresariales.domain.Season;
import es.unican.palaciosj.empresariales.domain.Serie;
import es.unican.palaciosj.empresariales.domain.SerieGold;
import es.unican.palaciosj.empresariales.domain.SerieStandard;
import es.unican.palaciosj.empresariales.domain.User;
import es.unican.palaciosj.empresariales.repositories.SerieRepository;
import es.unican.palaciosj.empresariales.repositories.UserRepository;

@Component
public class AppFeeder implements CommandLineRunner {
	
	@Autowired
	protected UserRepository ur;
	@Autowired
	protected SerieRepository sr;
	
	@Override
	public void run(String... args) throws Exception {
		feedUsers();
		feedSeries();
		
		//testViajeRepository();
		
		System.out.println("Application feeded");
	}

	private void feedUsers() {
        BankAccount b1 = new BankAccount("ES12 3456 7890 1234 5678 9012");
        BankAccount b2 = new BankAccount("ES12 3456 7890 1234 5678 9013");
	    User u1 = new User("Paco","paco123", b1);
		User u2 = new User("Lola","lola123", b2);
		ur.save(u1);
		ur.save(u2);
	}
	
	private void feedSeries() {
        // Create categories and series
        Category c1 = new SerieGold("Gold");
        Category c2 = new SerieStandard("Standard");
		Serie s1 = new Serie("The Big Bang Theory", "Comedy", c2);
        Serie s2 = new Serie("Dark", "Time travel", c1);

        // Add seasons to series
        Season s1s1 = new Season(1, s1);
        Season s2s1 = new Season(1, s2);
        s1.addSeason(s1s1);
        s2.addSeason(s2s1);

        // Add chapters to seasons
        Chapter s1s1c1 = new Chapter(1, "Pilot", "First chapter", "http://www.polaflix.com/watch?v=1");
        Chapter s1s1c2 = new Chapter(2, "The Big Bran Hypothesis", "Second chapter", "http://www.polaflix.com/watch?v=2");
        Chapter s2s1c1 = new Chapter(1, "Secrets", "First chapter", "http://www.polaflix.com/watch?v=3");
        Chapter s2s1c2 = new Chapter(2, "Lies", "Second chapter", "http://www.polaflix.com/watch?v=4");
        s1s1.addChapter(s1s1c1);
        s1s1.addChapter(s1s1c2);
        s2s1.addChapter(s2s1c1);
        s2s1.addChapter(s2s1c2);

		sr.save(s1);
        sr.save(s2);
	}

	private void testViajeRepository() {
		/*
		SimpleDateFormat dateParser = new SimpleDateFormat("dd-MM-yyyy");
		Date sample = null;
		try {
			sample = dateParser.parse("01-01-2020");
		} catch (ParseException e) {
			System.out.println("Crujo parseando fecha");
			e.printStackTrace();
		}
		
		// Set<Viaje> viajes = vr.findByOrigenCiudadAndDestinoCiudad("Santander","Cadiz");
		Set<Viaje> viajes = vr.findByOrigenAndDestino("Santander","Cadiz");
		
		System.out.println("Viajes recuperados = " + viajes.size());
	
		for(Viaje v : viajes) {
			System.out.println("Viaje in " + v.getFecha());
		}
		
		viajes = vr.findByOrigen_CiudadAndFechaBeforeOrderByPrecio("Santander", sample);

		System.out.println("================================");
		
		System.out.println("Viajes recuperados = " + viajes.size());
		
		
//		Usuario paco = ur.findByEmail("paco@carSharing.es"); 
//		
//		System.out.println("Paco = " + paco.getNombre() + ":" + paco.getEmail());
//		
//		Set<Usuario> usuarios = ur.findByFechaAltaAfter(sample);
//		for(Usuario u : usuarios) {
//			System.out.println("Usuario " + u.getNombre() + ":" + u.getEmail());
//		}
*/
		
	}

}
