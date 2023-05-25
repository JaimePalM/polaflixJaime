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
		feedBills();
		feedCategories();
		feedSeries();

		User paco = ur.findByEmail("paco23@polaflix.com");
		User lola = ur.findByEmail("lola43@polaflix.com");
		List<Serie> dList = sr.findByInitial('D');
		Serie dark = dList.get(0);
		List<Serie> bList = sr.findByInitial('B');
		Serie breakingBad = bList.get(0);
		List<Serie> gList = sr.findByInitial('G');
		Serie got = gList.get(0);
		List<Serie> bcList = sr.findByInitial('B');
		Serie betterCallSaul = bcList.get(1);
		
		// Test add serie to pending list
		paco.addSerieToPending(dark);
		paco.addSerieToPending(breakingBad);
		ur.save(paco);
		lola.addSerieToPending(got);
		ur.save(lola);

		// Test mark chapter as viewed
		lola.markChapterViewed(breakingBad, breakingBad.getSeason(1).getChapter(1));
		ur.save(lola);
		paco.markChapterViewed(dark, dark.getSeason(1).getChapter(2));
		ur.save(paco);

		// Populate started and finished series
		paco.addSerieToStarted(got);
		paco.addSerieToFinished(betterCallSaul);
		lola.addSerieToStarted(breakingBad);
		lola.addSerieToFinished(dark);
		ur.save(paco);
		ur.save(lola);

		System.out.println("Application feeded");
	}

	private void feedUsers() {
		BankAccount b1 = new BankAccount("ES12 3456 7890 1234 5678 9012");
		BankAccount b2 = new BankAccount("ES12 3456 7890 1234 5678 9013");
		User u1 = new User("paco23@polaflix.com", "Paco", "paco123", b1, false);
		User u2 = new User("lola43@polaflix.com", "Lola", "lola123", b2, true);
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
		Category silver = cr.findByName("Silver");
		Category standard = cr.findByName("Standard");

		// Create series
		Serie s1 = new Serie("Breaking Bad", "Drug dealer", standard);
		Serie s2 = new Serie("Dark", "Time travel", gold);
		Serie s3 = new Serie("Game of Thrones", "Kings, Queens and Dragons", gold);
		Serie s4 = new Serie("Better Call Saul", "Lawyer", gold);
		// Create some series that will we empty
		Serie s5 = new Serie("The Walking Dead", "Zombies", silver);
		Serie s6 = new Serie("MindHunter", "Serial killers", silver);
		Serie s7 = new Serie("Prison Break", "Prison", silver);

		// Add seasons to series
		Season s1s1 = new Season(1, s1);
		Season s2s1 = new Season(1, s2);
		Season s2s2 = new Season(2, s2);
		Season s3s1 = new Season(1, s3);
		Season s3s2 = new Season(2, s3);
		Season s3s3 = new Season(3, s3);
		Season s4s1 = new Season(1, s4);
		Season s7s1 = new Season(1, s7);
		s1.addSeason(s1s1);
		s2.addSeason(s2s1); s2.addSeason(s2s2); 
		s3.addSeason(s3s1); s3.addSeason(s3s2); s3.addSeason(s3s3);
		s4.addSeason(s4s1);
		s7.addSeason(s7s1);

		// Add chapters to seasons
		Chapter s1s1c1 = new Chapter(1, "Pilot", "First chapter",
				"http://www.polaflix.com/breaking-bad/season-1/chapter-1", s1s1);
		Chapter s1s1c2 = new Chapter(2, "Cat's in the bag", "Second chapter",
				"http://www.polaflix.com/breaking-bad/season-1/chapter-2", s1s1);
		Chapter s2s1c1 = new Chapter(1, "Secrets",
				"In 2019, a local boy's disappearance stokes fear in the residents of Winden, a small German town with a strange and tragic history.",
				"http://www.polaflix.com/dark/season-1/chapter-1", s2s1);
		Chapter s2s1c2 = new Chapter(2, "Lies",
				"When a grim discovery leaves the police baffled, Ulrich seeks a search warrant for the power plant. A mysterious stranger checks into the hotel.",
				"http://www.polaflix.com/dark/season-1/chapter-2", s2s1);
		Chapter s2s2c1 = new Chapter(1, "Beginnings and Endings",
				"Six months after the disappearances, the police form a task force. In 2052, Jonas learns that most of Winden perished in an apocalyptic event.",
				"http://www.polaflix.com/dark/season-2/chapter-1", s2s2);
		Chapter s3s1c1 = new Chapter(1, "Winter Is Coming",
				"Eddard Stark is torn between his family and an old friend when asked to serve at the side of King Robert Baratheon; Viserys plans to wed his sister to a nomadic warlord in exchange for an army",
				"http://www.polaflix.com/game-of-thrones/season-1/chapter-1", s3s1);
		Chapter s3s1c2 = new Chapter(2, "The Kingsroad",
				"Ned departs for King's Landing, after accepting the offer as Hand of the King, accompanied by his daughters Sansa and Arya. He is also accompanied by Jon Snow, his bastard son, who heads to the Wall to join the Night's Watch.",
				"http://www.polaflix.com/game-of-thrones/season-1/chapter-2", s3s1);
		Chapter s3s2c1 = new Chapter(1, "The North Remembers",
				"In the seven kingdoms a furious civil war rages, Stannis Baratheon and Renly do not recognize Jeoffrey as king and both aspire to the Iron Throne. Meanwhile, the North has declared independence and Tyrion is named Hand of the King.",
				"http://www.polaflix.com/game-of-thrones/season-2/chapter-1", s3s2);
		Chapter s3s2c2 = new Chapter(2, "The Night Lands",
				"Theon returns to Pyke, his native kingdom, to ask for his father's alliance in Robb Stark's cause. Meanwhile, Tyrion accuses Cersei of earning the antipathy of her subjects and banishes Janos Slynt, the commander of the Citadel Guard, to the Wall.",
				"http://www.polaflix.com/game-of-thrones/season-2/chapter-2", s3s2);
		Chapter s3s3c1 = new Chapter(1, "Valar Dohaeris",
				"Samwell Tarly and a group of the Night's Watch manage to save themselves from a wraith attack. At the same time, Jon arrives at the wildlings' camp and meets their leader Mance Rayder.",
				"http://www.polaflix.com/game-of-thrones/season-3/chapter-1", s3s3);
		Chapter s3s3c2 = new Chapter(2, "Dark Wings, Dark Words",
				"Jaime and Brienne continue their march to King's Landing, but are discovered by soldiers from the North. At the same time in the north, Bran finds two new companions on his way to the Wall.",
				"http://www.polaflix.com/game-of-thrones/season-3/chapter-2", s3s3);
		Chapter s4s1c1 = new Chapter(1, "Uno",
				"Jimmy uses magic in the courtroom; then, after being unexpectedly inspired, Jimmy tries an unconventional method to find potential clients.",
				"http://www.polaflix.com/better-call-saul/season-1/chapter-1", s4s1);
		Chapter s7s1c1 = new Chapter(1, "Pilot",
				"Michael Scofield is imprisoned in Fox River State Penitentiary. He finds his brother, Lincoln Burrows, who is a death row prisoner, and tells him that he is going to break them both out of the prison.",
				"http://www.polaflix.com/prison-break/season-1/chapter-1", s7s1);
		s1s1.addChapter(s1s1c1); s1s1.addChapter(s1s1c2);
		s2s1.addChapter(s2s1c1); s2s1.addChapter(s2s1c2); s2s2.addChapter(s2s2c1);
		s3s1.addChapter(s3s1c1); s3s1.addChapter(s3s1c2); s3s2.addChapter(s3s2c1); s3s2.addChapter(s3s2c2); s3s3.addChapter(s3s3c1); s3s3.addChapter(s3s3c2);
		s4s1.addChapter(s4s1c1);
		s7s1.addChapter(s7s1c1);

		// Add creators to series
		Creator c1s1 = new Creator("Vince", "Gilligan");
		Creator c1s2 = new Creator("Baran", "Bo Odar");
		Creator c1s3 = new Creator("David", "Benioff");
		Creator c2s3 = new Creator("D.B.", "Weiss");
		Creator c1s7 = new Creator("Paul", "Scheuring");
		s1.addCreator(c1s1); 
		s2.addCreator(c1s2); 
		s3.addCreator(c1s3); s3.addCreator(c2s3);
		s4.addCreator(c1s1);
		s7.addCreator(c1s7);

		// Add actors to series
		Actor a1s1 = new Actor("Bryan", "Cranston");
		Actor a2s1 = new Actor("Aaron", "Paul");
		Actor a1s2 = new Actor("Louis", "Hofmann");
		Actor a2s2 = new Actor("Lisa", "Vicari");
		Actor a1s3 = new Actor("Peter", "Dinklage");
		Actor a2s3 = new Actor("Lena", "Headey");
		Actor a3s3 = new Actor("Emilia", "Clarke");
		Actor a4s3 = new Actor("Kit", "Harington");
		Actor a1s4 = new Actor("Bob", "Odenkirk");
		Actor a2s4 = new Actor("Rhea", "Seehorn");
		Actor a1s7 = new Actor("Wentworth", "Miller");
		Actor a2s7 = new Actor("Dominic", "Purcell");
		s1.addActor(a1s1); s1.addActor(a2s1);
		s2.addActor(a1s2); s2.addActor(a2s2);
		s3.addActor(a1s3); s3.addActor(a2s3); s3.addActor(a3s3); s3.addActor(a4s3);
		s4.addActor(a1s4); s4.addActor(a2s4);
		s7.addActor(a1s7); s7.addActor(a2s7);

		sr.save(s1);
		sr.save(s2);
		sr.save(s3);
		sr.save(s4);
		sr.save(s5);
		sr.save(s6);
		sr.save(s7);
	}

	public void feedBills() {

	}

}
