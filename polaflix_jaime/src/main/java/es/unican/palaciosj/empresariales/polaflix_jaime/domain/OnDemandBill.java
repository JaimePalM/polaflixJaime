package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import java.sql.Date;

import jakarta.persistence.Entity;

@Entity
public class OnDemandBill extends Bill {
    
    // Atributes

    // Constructor
    public OnDemandBill(Date month) {
        super(month);
    }

    // Auxiliar methods
}

