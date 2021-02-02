package com.diary.android.util;

import java.util.Date;

public class File {

    private int id;

    private String Folder;

    private String theme;

    private Date lastModify;

    private String content;

    private int textSize;

    private String textFont;

    private String textColor;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTheme() {
        return theme;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }

    public Date getLastModify() {
        return lastModify;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setFolder(String folder) {
        Folder = folder;
    }

    public String getFolder() {
        return Folder;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextFont(String textFont) {
        this.textFont = textFont;
    }

    public String getTextFont() {
        return textFont;
    }
}
