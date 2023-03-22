package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import jakarta.persistence.Embeddable;

/**
 * Serie gold class
 */
@Embeddable
public class SerieGold extends Category {
    
    // Atributes
    private static double price = 1.50;

    // Constructor
    public SerieGold(String name) {
        super(name);
    }

    // Getter
    public double getPrice() {
        return price;
    }
}
