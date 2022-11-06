package com.geckolabs.notes;

public class NoteModel {
    public int id;
    public String text;

    //constructors
    public NoteModel(int id, String text) {
        this.id = id;
        this.text = text;
    }


    //toSting is necessary for printing the contents of a class object
    @Override
    public String toString() {
        return "NoteModel{" +
                "id=" + id +
//                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    //setters and getters
    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {

        this.text = text;
    }
}
