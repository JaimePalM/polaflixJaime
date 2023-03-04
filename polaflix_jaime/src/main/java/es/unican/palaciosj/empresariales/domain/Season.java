package es.unican.palaciosj.empresariales.domain;

import java.util.LinkedList;

public class Season {

    // Atributes
    private int number;
    private LinkedList<Chapter> chapters = new LinkedList<Chapter>();

    // Constructor
    public Season(int number) {
        this.number = number;
    }

    // Auxiliar methods
    

    // Getters and Setters
    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LinkedList<Chapter> getChapters() {
        return this.chapters;
    }

    public void setChapters(LinkedList<Chapter> chapters) {
        this.chapters = chapters;
    }

}
