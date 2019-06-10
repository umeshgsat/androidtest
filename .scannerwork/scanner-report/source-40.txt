package com.example.sqlitedatabase;

public class Note {
    private int mId;
    private String mNote;
    private String mTimestamp;

    public Note() {

    }

    public Note(int id, String note, String timestamp) {
        this.mId = id;
        this.mNote = note;
        this.mTimestamp = timestamp;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getNote() {
        return mNote;
    }

    public void setNote(String mNote) {
        this.mNote = mNote;
    }

    public String getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(String mTimestamp) {
        this.mTimestamp = mTimestamp;
    }
}
