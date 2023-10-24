package ru.novikov.rescueSite.models;

import jakarta.validation.constraints.*;

public class Post {

    private int id;

    private boolean postStatus;

    private Integer searchingPeopleCurrentQuantity;

    @NotEmpty(message = "name is significant")
    @Size(min = 2, max = 20, message = "can't be greater than 20 characters")
    @Pattern(regexp = "[A-Z][a-z]+", message = "only latin characters")
    private String lostPersonName;

    @NotEmpty(message = "Surname can't be empty")
    @Size(min = 2, max = 20, message = "can't be greater than 20 characters")
    @Pattern(regexp = "[A-Z][a-z]+", message = "only latin characters")
    private String lostPersonSurname;

    @NotEmpty(message = "age shouldn't be empty")
    @Min(value = 0, message = "Only older 14")
    @Max(value = 130, message ="Age error")
    @Pattern(regexp = "\\d{1,3}", message = "Age must contain only numbers")
    private String lostPersonAge;

    @NotEmpty(message = "provide some info about lost person")
    @Size(min = 2, max = 600, message = "less than 600 symbols")
    private String lostPersonSpecialSigns;

    @NotEmpty(message = "please enter location")
    @Size(min = 2, max = 250, message = "less than 250 symbols")
    private String whereGotLost;

    @NotEmpty(message = "enter date")
    @Pattern(regexp = "\\d{2}.\\d{2}.\\d{4}", message = "year should be in format 01.01.2017")
    private String whenGotLost;

    @NotEmpty(message = "enter person clothing")
    @Size(min = 2, max = 600, message = "less than 600 symbols")
    private String lostPersonClothing;

    public Post() {}

    public Post(int id, boolean status, Integer searchingPeopleCurrentQuantity, String lostPersonName, String lostPersonSurname,
                String lostPersonAge, String lostPersonSpecialSigns, String whereGotLost, String whenGotLost, String lostPersonClothing) {
        this.id = id;
        this.postStatus = status;
        this.searchingPeopleCurrentQuantity = searchingPeopleCurrentQuantity;
        this.lostPersonName = lostPersonName;
        this.lostPersonSurname = lostPersonSurname;
        this.lostPersonAge = lostPersonAge;
        this.lostPersonSpecialSigns = lostPersonSpecialSigns;
        this.whereGotLost = whereGotLost;
        this.whenGotLost = whenGotLost;
        this.lostPersonClothing = lostPersonClothing;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPostStatus(boolean postStatus) {
        this.postStatus = postStatus;
    }

    public void setSearchingPeopleCurrentQuantity(Integer searchingPeopleCurrentQuantity) {
        this.searchingPeopleCurrentQuantity = searchingPeopleCurrentQuantity;
    }

    public void setLostPersonName(String lostPersonName) {
        this.lostPersonName = lostPersonName;
    }

    public void setLostPersonSurname(String lostPersonSurname) {
        this.lostPersonSurname = lostPersonSurname;
    }

    public void setLostPersonAge(String lostPersonAge) {
        this.lostPersonAge = lostPersonAge;
    }

    public void setLostPersonSpecialSigns(String lostPersonSpecialSigns) {
        this.lostPersonSpecialSigns = lostPersonSpecialSigns;
    }

    public void setWhereGotLost(String whereGotLost) {
        this.whereGotLost = whereGotLost;
    }

    public void setWhenGotLost(String whenGotLost) {
        this.whenGotLost = whenGotLost;
    }

    public void setLostPersonClothing(String lostPersonClothing) {
        this.lostPersonClothing = lostPersonClothing;
    }

    public int getId() {
        return id;
    }

    public boolean isPostStatus() {
        return postStatus;
    }

    public Integer getSearchingPeopleCurrentQuantity() {
        return searchingPeopleCurrentQuantity;
    }

    public String getLostPersonName() {
        return lostPersonName;
    }

    public String getLostPersonSurname() {
        return lostPersonSurname;
    }

    public String getLostPersonAge() {
        return lostPersonAge;
    }

    public String getLostPersonSpecialSigns() {
        return lostPersonSpecialSigns;
    }

    public String getWhereGotLost() {
        return whereGotLost;
    }

    public String getWhenGotLost() {
        return whenGotLost;
    }

    public String getLostPersonClothing() {
        return lostPersonClothing;
    }

}
