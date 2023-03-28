package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Category class
 */
@Entity
public class Category {

    // Atributes
    @Id
    private String name;
    private double price;

    // Constructor
    public Category() { }
    public Category(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters and Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}