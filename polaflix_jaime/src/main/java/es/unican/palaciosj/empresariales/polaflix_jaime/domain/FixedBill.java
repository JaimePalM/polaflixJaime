package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import java.sql.Date;

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
