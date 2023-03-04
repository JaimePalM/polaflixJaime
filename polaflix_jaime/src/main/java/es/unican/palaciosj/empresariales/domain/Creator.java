package es.unican.palaciosj.empresariales.domain;

public class Creator {
    
    // Atributes
    private String name;

    // Constructor
    public Creator(String name) {
        this.name = name;
    }

    // Getters and Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
