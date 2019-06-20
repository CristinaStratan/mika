package com.drools.droolstry.model;

import java.util.ArrayList;
import java.util.List;

public class Letter {

    List<String> letters = new ArrayList<>();

    String letter;

    public Letter() {
    }

    public Letter(String letter) {
        this.letter = letter;
    }
    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Letter(List<String> letters, String letter) {
        this.letters = letters;
        this.letter = letter;
    }

    public List<String> getLetters() {
        return letters;
    }

    public void setLetters(List<String> letters) {
        this.letters = letters;
    }

    public void addLetters(String letter) {
        this.letters.add(letter);
    }
}
