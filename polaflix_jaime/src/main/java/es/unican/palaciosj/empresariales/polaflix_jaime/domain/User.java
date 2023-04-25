package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import es.unican.palaciosj.empresariales.polaflix_jaime.rest.JsonViews;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * User class. Anotacion @Table debido a que el nombre de tabla "user" esta reservada en Spring
 */
@Entity
@Table(name = "users")
public class User {
    
    // Atributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    @JsonView(JsonViews.UserView.class)
    private String email;
    @JsonView(JsonViews.UserView.class)
    private String username;
    @JsonIgnore
    private String password;
    @Embedded
    private BankAccount bankAccount;
    private boolean fixedFee;
    @ManyToMany
    @JsonView(JsonViews.UserView.class)
    private Set<Serie> pendingSeries = new TreeSet<Serie>();
    @ManyToMany
    @JsonView(JsonViews.UserView.class)
    private Set<Serie> startedSeries = new TreeSet<Serie>();
    @ManyToMany
    @JsonView(JsonViews.UserView.class)
    private Set<Serie> finishedSeries = new TreeSet<Serie>();
    @ManyToMany
    private Map<Serie, Chapter> lastChapterView = new HashMap<Serie, Chapter>();
    @OneToMany(cascade = CascadeType.ALL)
    @JsonView(JsonViews.SerieView.class)
    private Map<Serie, Views> serieViews = new HashMap<Serie, Views>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private SortedSet<Bill> bills = new TreeSet<Bill>();

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

    // Mark chapter as viewed for a serie (precondition: the serie is in any list)
    public void markChapterViewed(Serie serie, Chapter chapter) {
        // Check if the chapter is later than the last chapter viewed
        Chapter lastChapter = this.lastChapterView.get(serie);
        if (lastChapter == null) {
            // Save last chapter viewed for serie if there is no last chapter viewed
            this.lastChapterView.put(serie, chapter);
        } else if (lastChapter.compareTo(chapter) < 0) {
            // Save last chapter viewed for serie if the new chapter is later than the last chapter viewed
            this.lastChapterView.put(serie, chapter);
        }
        // Mark chapter as viewed
        Views view = this.serieViews.get(serie);
        if (view == null) {
            view = new Views(serie);
            this.serieViews.put(serie, view);
        }
        view.markChapterViewed(chapter);
        // Bill the chapter
        // Get last bill
        Bill lastBill = null;
        if (this.bills.isEmpty()) {
            // Get first day of current month
            LocalDate date = LocalDate.now();
            date = date.withDayOfMonth(1);
            Date firstDay = Date.valueOf(date);
            if (this.fixedFee) {
                lastBill = new FixedBill(this, firstDay);
            } else {
                lastBill = new OnDemandBill(this, firstDay);
            }
            this.bills.add(lastBill);
        } else { 
            lastBill = this.bills.last();
        }
        lastBill.addChapterView(chapter);
    }

    // Add bill to user
    public void addBill(Bill bill) {
        this.bills.add(bill);
    }

    // Get views for a serie
    public Views getSerieViews(Serie serie) {
        return this.serieViews.get(serie);
    }

    // Get bill for a date (precondition: the date is the first day of the month)
    public Bill getBillByDate(Date date) {
        for (Bill bill : this.bills) {
            if (bill.getMonthBilled().equals(date)) {
                return bill;
            }
        }
        return null;
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

    public void setBills(SortedSet<Bill> bills) {
        this.bills = bills;
    }

}
