package es.unican.palaciosj.empresariales.domain;

public class Chapter {

    // Atributes
    private int number;
    private String title;
    private String description;
    private String url;



    // Constructor
    public Chapter(int number, String title, String description, String url) {
        this.number = number;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    // Auxiliar methods
    public void play() {
        System.out.println("Playing chapter " + this.number + "...");
    }

    // Getters and Setters
    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
