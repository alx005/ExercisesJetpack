package com.google.signinsample.data;

public class User {
    public String id;
    public String email;

    public User(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public User (){

    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
