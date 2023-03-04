package es.unican.palaciosj.empresariales.domain;

public class SerieSilver extends Serie {
    
    // Atributes
    private final double PRICE = 0.75;

    // Constructor
    public SerieSilver(String title, String description) {
        super(title, description);
    }

    // Getters and Setters
    @Override
    public double getPrice() {
        return this.PRICE;
    }
}
