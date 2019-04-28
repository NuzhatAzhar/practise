package com.example.administrator.login_password;

public class Student_list {
    String user;
    String password;
    String email;
    String uid;


    public Student_list() {
    }

    public Student_list(String user, String password, String email, String uid) {
        this.user = user;
        this.password = password;
        this.email = email;
        this.uid = uid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
