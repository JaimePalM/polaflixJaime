package es.unican.palaciosj.empresariales.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

/**
 * User class
 */
@Entity
public class Usuario {
    
    // Atributes
    @Id
    private String username;
    private String password;
    @OneToOne
    private BankAccount bankAccount;
    private boolean fixedFee;
    @OneToMany
    private Set<Serie> pendingSeries = new TreeSet<Serie>();
    @OneToMany
    private Set<Serie> startedSeries = new TreeSet<Serie>();
    @OneToMany
    private Set<Serie> finishedSeries = new TreeSet<Serie>();
    private Map<Serie, Chapter> lastChapterView = new HashMap<Serie, Chapter>();

    // Constructor
    public Usuario(String username, String password, BankAccount bankAccount) {
        this.username = username;
        this.password = password;
        this.bankAccount = bankAccount;
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

    // Mark last chapter viewed for a serie
    public void markLastChapterViewed(Serie serie, Chapter chapter) {
        this.lastChapterView.put(serie, chapter);
    }

    // Getters and Setters
    
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

    public void getLastViewedChapter(Serie serie) {
        this.lastChapterView.get(serie);
    }

    public void setLastViewedChapter(Serie serie, Chapter chapter) {
        this.lastChapterView.put(serie, chapter);
    }

}
