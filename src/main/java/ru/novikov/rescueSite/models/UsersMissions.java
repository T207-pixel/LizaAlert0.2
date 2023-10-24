package ru.novikov.rescueSite.models;

public class UsersMissions {

    private int userId;
    private int postId;

    public UsersMissions() {}

    public UsersMissions(int userId, int postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
