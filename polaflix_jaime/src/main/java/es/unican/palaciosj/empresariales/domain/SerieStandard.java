package es.unican.palaciosj.empresariales.domain;

import jakarta.persistence.Embeddable;

/**
 * Serie standard class
 */
@Embeddable
public class SerieStandard extends Category {

    // Atributes
    private static double price = 0.50;

   // Constructor
    public SerieStandard(String name) {
        super(name, price);
    }
}
