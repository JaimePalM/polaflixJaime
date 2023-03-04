package es.unican.palaciosj.empresariales.domain;

import java.sql.Date;

public class Bill {
    
    // Atributes
    private float totalAmount;
    private Date month;

    // Constructor
    public Bill(float totalAmount, Date month) {
        this.totalAmount = totalAmount;
        this.month = month;
    }

    // Getters and Setters
    public float getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getMonth() {
        return this.month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }
}
