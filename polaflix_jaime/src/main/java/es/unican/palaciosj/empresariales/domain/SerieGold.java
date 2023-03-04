package es.unican.palaciosj.empresariales.domain;

public class SerieGold extends Serie {
    
    // Atributes
    private final double PRICE = 1.50;

    // Constructor
    public SerieGold(String title, String description) {
        super(title, description);
    }

    // Getters and Setters
    @Override
    public double getPrice() {
        return this.PRICE;
    }
}
