package com.example.doctor;

public class model2 {
    String name;
    String email;
    String number;
    String cat;

    public model2() {

    }

    public model2(String name, String email, String number, String cat) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
}

