package com.devahoy.sample.ahoysqlite.model;

public class Friend {

    public static final String KEY_ID = "id";
    public static final String KEY_FIRST_NAME = "firstName";
    public static final String KEY_LAST_NAME = "lastName";
    public static final String KEY_TEL = "tel";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_DESCRIPTION = "description";

    private int id;
    private String firstName;
    private String lastName;
    private String tel;
    private String email;
    private String description;

    //Default Constructor
    public Friend() {

    }
    //Constructor
    public Friend(int id, String firstName, String lastName, String tel,
                  String email, String description) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tel = tel;
        this.email = email;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
