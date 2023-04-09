package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import java.sql.Date;

import jakarta.persistence.Entity;

@Entity
public class FixedBill extends Bill {
    
    // Atributes
    private final double FIXED_PRICE = 20;

    // Constructor
    public FixedBill() { }
    public FixedBill(User user, Date month) {
        super(user, month);
        setTotalAmount(FIXED_PRICE);
    }

}
