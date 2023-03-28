package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import java.sql.Date;

import jakarta.persistence.Entity;

@Entity
public class FixedBill extends Bill {
    
    // Atributes
    private static double totalAmount = 20;

    // Constructor
    public FixedBill(Date month) {
        super(month);
    }

    // Getters and Setters
    public double getTotalAmount() {
        return FixedBill.totalAmount;
    }
}
