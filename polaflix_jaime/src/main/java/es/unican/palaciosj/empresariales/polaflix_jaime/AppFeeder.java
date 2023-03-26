package es.unican.palaciosj.empresariales.polaflix_jaime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import es.unican.palaciosj.empresariales.polaflix_jaime.domain.BankAccount;
import es.unican.palaciosj.empresariales.polaflix_jaime.domain.Category;
import es.unican.palaciosj.empresariales.polaflix_jaime.domain.Chapter;
import es.unican.palaciosj.empresariales.polaflix_jaime.domain.Creator;
import es.unican.palaciosj.empresariales.polaflix_jaime.domain.Season;
import es.unican.palaciosj.empresariales.polaflix_jaime.domain.Serie;
import es.unican.palaciosj.empresariales.polaflix_jaime.domain.User;
import es.unican.palaciosj.empresariales.polaflix_jaime.repositories.CategoryRepository;
import es.unican.palaciosj.empresariales.polaflix_jaime.repositories.SerieRepository;
import es.unican.palaciosj.empresariales.polaflix_jaime.repositories.UserRepository;

@Component
public class AppFeeder implements CommandLineRunner {
	
	@Autowired
	protected UserRepository ur;
	@Autowired
	protected SerieRepository sr;
	@Autowired
	protected CategoryRepository cr;
	
	@Override
	public void run(String... args) throws Exception {
		feedUsers();
		feedCategories();
		feedSeries();
		
		// Test mark chapter as viewed
		/*/
		User paco = ur.findByEmail("paco23@polaflix.com");
		List<Serie> dList = sr.findByInitial('D');
		Serie dark = dList.get(0);

		paco.markChapterViewed(dark, dark.getSeason(1).getChapter(1));
		*/

		//testViajeRepository();
		
		System.out.println("Application feeded");
	}

	private void feedUsers() {
        BankAccount b1 = new BankAccount("ES12 3456 7890 1234 5678 9012");
        BankAccount b2 = new BankAccount("ES12 3456 7890 1234 5678 9013");
	    User u1 = new User("paco23@polafix.com","Paco","paco123", b1, false);
		User u2 = new User("lola43@polaflix.com","Lola","lola123", b2, false);
		ur.save(u1);
		ur.save(u2);
	}

	private void feedCategories() {
		Category gold = new Category("Gold", 1.5);
		Category silver = new Category("Silver", 1.0);
		Category standard = new Category("Standard", 0.5);
		cr.save(gold);
		cr.save(silver);
		cr.save(standard);
	}
	
	private void feedSeries() {
        // Get categories
		Category gold = cr.findByName("Gold");
		Category standard = cr.findByName("Standard");

		// Create series
		Serie s1 = new Serie("Breaking Bad", "Drug dealer", standard);
        Serie s2 = new Serie("Dark", "Time travel", gold);

        // Add seasons to series
        Season s1s1 = new Season(1, s1);
        Season s2s1 = new Season(1, s2);
        s1.addSeason(s1s1);
        s2.addSeason(s2s1);
		
        // Add chapters to seasons
        Chapter s1s1c1 = new Chapter(1, "Pilot", "First chapter", "http://www.polaflix.com/watch?v=1", s1s1);
        Chapter s1s1c2 = new Chapter(2, "Cat's in the bag", "Second chapter", "http://www.polaflix.com/watch?v=2", s1s1);	
        Chapter s2s1c1 = new Chapter(1, "Secrets", "First chapter", "http://www.polaflix.com/watch?v=3", s2s1);
        Chapter s2s1c2 = new Chapter(2, "Lies", "Second chapter", "http://www.polaflix.com/watch?v=4", s2s1);
        s1s1.addChapter(s1s1c1);
        s1s1.addChapter(s1s1c2);
        s2s1.addChapter(s2s1c1);
        s2s1.addChapter(s2s1c2);		

		// Add creators to series
		Creator c1s1 = new Creator("Vince", "Gilligan");
		Creator c1s2 = new Creator("Baran", "Bo Odar");
		s1.addCreator(c1s1);
		s2.addCreator(c1s2);

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
