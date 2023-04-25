package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.palaciosj.empresariales.polaflix_jaime.rest.JsonViews;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Category class
 */
@Entity
public class Category {

    // Atributes
    @Id
    @JsonView({JsonViews.SerieView.class})
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