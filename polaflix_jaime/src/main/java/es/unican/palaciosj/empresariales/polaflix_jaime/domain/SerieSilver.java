package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import jakarta.persistence.Embeddable;

/**
 * Serie silver class
 */
@Embeddable
public class SerieSilver extends Category {
    
    // Atributes
    private static double price = 0.75;

    // Constructor
    public SerieSilver(String name) {
        super(name);
    }

    // Getter
    public double getPrice() {
        return price;
    }
}