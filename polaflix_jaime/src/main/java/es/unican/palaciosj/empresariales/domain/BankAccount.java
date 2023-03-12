package es.unican.palaciosj.empresariales.domain;

import jakarta.persistence.Embeddable;

/**
 * Bank account class
 */
@Embeddable
public class BankAccount {

    // Atributes
    private String IBAN;

    // Getters and Setters
    public String getIBAN() {
        return this.IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }
}
