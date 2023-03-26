package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * User class
 */
@Entity
@Table(name = "users")
public class User {
    
    // Atributes
    @Id
    private String email;
    private String username;
    private String password;
    @Embedded
    private BankAccount bankAccount;
    private boolean fixedFee;
    @ManyToMany
    private Set<Serie> pendingSeries = new TreeSet<Serie>();
    @ManyToMany
    private Set<Serie> startedSeries = new TreeSet<Serie>();
    @ManyToMany
    private Set<Serie> finishedSeries = new TreeSet<Serie>();
    @ManyToMany
    private Map<Serie, Chapter> lastChapterView = new HashMap<Serie, Chapter>();
    @OneToMany
    private Map<Serie, Views> serieViews = new HashMap<Serie, Views>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Bill> bills = new TreeSet<Bill>();

    // Constructor
    public User() {
    }

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
        this.lastChapterView.put(serie, chapter);
        this.serieViews.get(serie).markChapterViewed(chapter);
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return this.username.equals(user.getUsername()) && this.password.equals(user.getPassword());
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
