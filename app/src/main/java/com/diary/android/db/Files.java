package com.diary.android.db;

import com.diary.android.util.File;

import java.util.List;

public class Files {

    private int id;

    private String userID;

    private List<File> folder;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setFolder(List<File> folder) {
        this.folder = folder;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<File> getFolder() {
        return folder;
    }
}
