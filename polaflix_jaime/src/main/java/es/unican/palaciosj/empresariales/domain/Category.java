package es.unican.palaciosj.empresariales.domain;

import jakarta.persistence.Embeddable;

/**
 * Category class
 */
@Embeddable
public abstract class Category {

    // Atributes
    private String name;
    private double price;

    // Constructor
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