package com.example.alirzaycefaydal.ticaret.Models;

public class User {

    private String name;
    private String password;
    private String mail;
    private String image;

    public User(){}

    public User(String name, String password, String mail, String image) {
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
