package es.unican.palaciosj.empresariales.polaflix_jaime;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import es.unican.palaciosj.empresariales.polaflix_jaime.domain.Actor;
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
	@Transactional
	public void run(String... args) throws Exception {
		feedUsers();
		//feedBills();
		feedCategories();
		feedSeries();

		// Test add serie to pending list
		User paco = ur.findByUsername("Paco");
		List<Serie> dList = sr.findByInitial('D');
		Serie dark = dList.get(0);	
		paco.addSerieToPending(dark);
		ur.save(paco);

		 
		// Test mark chapter as viewed
		User lola = ur.findByUsername("Lola");
		List<Serie> bList = sr.findByInitial('B');
		Serie breakingBad = bList.get(0); 
		lola.markChapterViewed(breakingBad, breakingBad.getSeason(1).getChapter(1));
		ur.save(lola);

		paco = ur.findByUsername("Paco");
		paco.markChapterViewed(dark, dark.getSeason(1).getChapter(2));
		ur.save(paco);
		 

		System.out.println("Application feeded");
	}
	
	private void feedUsers() {
        BankAccount b1 = new BankAccount("ES12 3456 7890 1234 5678 9012");
        BankAccount b2 = new BankAccount("ES12 3456 7890 1234 5678 9013");
	    User u1 = new User("paco23@polaflix.com","Paco","paco123", b1, false);
		User u2 = new User("lola43@polaflix.com","Lola","lola123", b2, true);
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
        Chapter s1s1c1 = new Chapter(1, "Pilot", "First chapter", "http://www.polaflix.com/breaking-bad/season-1/chapter-1", s1s1);
        Chapter s1s1c2 = new Chapter(2, "Cat's in the bag", "Second chapter", "http://www.polaflix.com/breaking-bad/season-1/chapter-2", s1s1);
        Chapter s2s1c1 = new Chapter(1, "Secrets", "First chapter", "http://www.polaflix.com/dark/season-1/chapter-1", s2s1);
        Chapter s2s1c2 = new Chapter(2, "Lies", "Second chapter", "http://www.polaflix.com/dark/season-1/chapter-2", s2s1);
        s1s1.addChapter(s1s1c1);
        s1s1.addChapter(s1s1c2);
        s2s1.addChapter(s2s1c1);
        s2s1.addChapter(s2s1c2);		

		// Add creators to series
		Creator c1s1 = new Creator("Vince", "Gilligan");
		Creator c1s2 = new Creator("Baran", "Bo Odar");
		s1.addCreator(c1s1);
		s2.addCreator(c1s2);

		// Add actors to series
		Actor a1s1 = new Actor("Bryan", "Cranston");
		Actor a2s1 = new Actor("Aaron", "Paul");
		Actor a1s2 = new Actor("Louis", "Hofmann");
		Actor a2s2 = new Actor("Lisa", "Vicari");
		s1.addActor(a1s1);
		s1.addActor(a2s1);
		s2.addActor(a1s2);
		s2.addActor(a2s2);

		sr.save(s1);
		sr.save(s2);

		// User lola = ur.findByUsername("Lola");
		// lola.markChapterViewed(s1, s1.getSeason(1).getChapter(1));
		// ur.save(lola);
		// User paco = ur.findByUsername("Paco");
		// paco.markChapterViewed(s2, s2.getSeason(1).getChapter(2));
		// ur.save(paco);
		// sr.save(s1);
	}

}
