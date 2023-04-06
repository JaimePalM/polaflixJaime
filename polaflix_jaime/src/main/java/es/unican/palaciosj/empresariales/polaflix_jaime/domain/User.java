package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * User class
 */
@Entity
@Table(name = "users")
public class User {
    
    // Atributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String email;
    private String username;
    private String password;
    @Embedded
    private BankAccount bankAccount;
    private boolean fixedFee;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Serie> pendingSeries = new TreeSet<Serie>();
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Serie> startedSeries = new TreeSet<Serie>();
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Serie> finishedSeries = new TreeSet<Serie>();
    @ManyToMany(fetch = FetchType.EAGER)
    private Map<Serie, Chapter> lastChapterView = new HashMap<Serie, Chapter>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Map<Serie, Views> serieViews = new HashMap<Serie, Views>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Bill> bills = new TreeSet<Bill>();

    // Constructor
    public User() { }
    public User(String email, String username, String password, BankAccount bankAccount, boolean fixedFee) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.bankAccount = bankAccount;
        this.fixedFee = fixedFee;
    }

    // Auxiliar methods
    // Add serie to pending list
    public void addSerieToPending(Serie serie) {
        // Check if serie is already in any list
        if (this.pendingSeries.contains(serie) || this.startedSeries.contains(serie) || this.finishedSeries.contains(serie)) {
            return;
        }
        this.pendingSeries.add(serie);
    }

    // Mark chapter as viewed for a serie
    public void markChapterViewed(Serie serie, Chapter chapter) {
        // Save last chapter viewed for serie
        this.lastChapterView.put(serie, chapter);
        // Mark chapter as viewed
        Views view = this.serieViews.get(serie);
        if (view == null) {
            view = new Views(serie);
            this.serieViews.put(serie, view);
        }
        view.markChapterViewed(chapter);
        // If serie is not in any list, add it to started list
        if (!this.pendingSeries.contains(serie) && !this.finishedSeries.contains(serie)) {
            this.startedSeries.add(serie);
        }
        // If serie is in pending list, move it to started list
        if (this.pendingSeries.contains(serie)) {
            this.pendingSeries.remove(serie);
            this.startedSeries.add(serie);
        }
        // Bill the chapter
        // Get last bill
        Bill lastBill = null;
        for (Bill bill : this.bills) {
            lastBill = bill;
        }
        LocalDate date = LocalDate.now();
        Date today = Date.valueOf(date);
        ChapterView chapterView = new ChapterView(chapter, today, serie.getCategory().getPrice());
        lastBill.addChapterView(chapterView);
    }

    // Add bill to user
    public void addBill(Bill bill) {
        this.bills.add(bill);
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return this.email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return this.email.hashCode();
    }

    // Getters and Setters    
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFixedFee() {
        return this.fixedFee;
    }

    public void setFixedFee(boolean fixedFee) {
        this.fixedFee = fixedFee;
    }

    public BankAccount getBankAccount() {
        return this.bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Set<Serie> getFinishedSeries() {
        return this.finishedSeries;
    }

    public void setFinishedSeries(Set<Serie> finishedSeries) {
        this.finishedSeries = finishedSeries;
    }

    public Set<Serie> getStartedSeries() {
        return this.startedSeries;
    }

    public void setStartedSeries(Set<Serie> startedSeries) {
        this.startedSeries = startedSeries;
    }

    public Set<Serie> getPendingSeries() {
        return this.pendingSeries;
    }

    public void setPendingSeries(Set<Serie> pendingSeries) {
        this.pendingSeries = pendingSeries;
    }

    public Chapter getLastViewedChapter(Serie serie) {
        return this.lastChapterView.get(serie);
    }

    public void setLastViewedChapter(Serie serie, Chapter chapter) {
        this.lastChapterView.put(serie, chapter);
    }

    public Map<Serie, Chapter> getLastChapterView() {
        return this.lastChapterView;
    }

    public void setLastChapterView(Map<Serie, Chapter> lastChapterView) {
        this.lastChapterView = lastChapterView;
    }

    public Set<Bill> getBills() {
        return this.bills;
    }

    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }

}
