package com.geckolabs.dao.model;

public class Note {
    private int id;
    private String title;
    private String note_group;
    private int color;

    public Note(int id, String title, String note_group, int color) {
        this.id = id;
        this.title = title;
        this.note_group = note_group;
        this.color = color;
    }

    public Note(String title, String note_group, int color) {
        this.title = title;
        this.note_group = note_group;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote_group() {
        return note_group;
    }

    public void setNote_group(String note_group) {
        this.note_group = note_group;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
