package com.geckolabs.notes;

public class PicNoteModel {
    private int id;
    private String title;
    private String description;
    private String type;
    private int noteId;
    private String mediaPath;

    public PicNoteModel(int id, String title, String description, String type, int noteId, String mediaPath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.noteId = noteId;
        this.mediaPath = mediaPath;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }
}
