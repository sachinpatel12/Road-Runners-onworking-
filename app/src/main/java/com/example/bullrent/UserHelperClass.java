package com.example.bullrent;

public class UserHelperClass {
    String name,username,email,phonno,pass;

    public UserHelperClass() {

    }

    public UserHelperClass(String name, String username, String email, String phonno, String pass) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phonno = phonno;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonno() {
        return phonno;
    }

    public void setPhonno(String phonno) {
        this.phonno = phonno;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
