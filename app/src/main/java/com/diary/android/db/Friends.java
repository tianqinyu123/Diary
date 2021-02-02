package com.diary.android.db;

import java.util.List;

public class Friends {

    private int id;

    private String userID;

    private List<UserInfo> friend;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setFriend(List<UserInfo> friend) {
        this.friend = friend;
    }

    public List<UserInfo> getFriend() {
        return friend;
    }
}
