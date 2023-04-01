package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import java.sql.Date;

import jakarta.persistence.Entity;

@Entity
public class OnDemandBill extends Bill {
    
    // Atributes

    // Constructor
    public OnDemandBill() { }
    public OnDemandBill(User user, Date month) {
        super(user, month);
    }

    // Auxiliar methods
}

