package es.unican.palaciosj.empresariales.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;

/**
 * Bank account class
 */
@Embeddable
public class BankAccount {

    // Atributes
    @Id
    private String IBAN;

    // Getters and Setters
    public String getIBAN() {
        return this.IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }
}
