package ru.novikov.rescueSite.models;

import jakarta.validation.constraints.*;

public class User {

    private int id;

    private String userStatus;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 20, message = "Only latin characters from 2 to 20")
    @Pattern(regexp = "[A-Z][a-z]+", message = "First letter should be capital")
    private String name;

    @NotEmpty(message = "Surname shouldn't be empty")
    @Size(min = 2, max = 20, message = "Only latin characters from 2 to 20")
    @Pattern(regexp = "[A-Z][a-z]+", message = "First letter should be capital")
    private String surname;

    @NotEmpty(message = "Patronymic shouldn't be empty")
    @Size(min = 2, max = 20, message = "Only latin characters from 2 to 20")
    @Pattern(regexp = "[A-Z][a-z]+", message = "First letter should be capital")
    private String patronymic;

    @NotEmpty(message = "Hometown shouldn't be empty")
    @Size(min = 2, max = 40, message = "Hometown should be less than 40 symbols and more than two")
    private String hometown;

    @NotEmpty(message = "Age shouldn't be empty")
    @Min(value = 14, message = "You have to be older than 14")
    @Max(value = 130, message ="Age error")
    @Pattern(regexp = "\\d{1,3}", message = "Wrong age format")
    private String age;

    public User() {}

    public User(int id, String userStatus, String name, String surname, String patronymic, String hometown, String age) {
        this.id = id;
        this.userStatus = userStatus;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.hometown = hometown;
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getHometown() {
        return hometown;
    }

    public String getAge() {
        return age;
    }

}
