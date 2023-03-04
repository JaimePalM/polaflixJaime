package es.unican.palaciosj.empresariales.domain;

public class SerieStandard extends Serie {

    // Atributes
    private final double PRICE = 0.50;

    public SerieStandard(String title, String description) {
        super(title, description);
    }

    // Getters and Setters
    @Override
    public double getPrice() {
        return this.PRICE;
    }
}
