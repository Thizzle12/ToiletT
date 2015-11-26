package com.example.reskesen.toilettreasure;

/**
 * Created by Reskesen on 12-11-2015.
 */
public class User {


    private String password;
    private String fullName;

    public User(String fullName, String password) {
        this.fullName = fullName;
        this.password = password;

    }

    public String getPassword()

    {
        return password;
    }



    public String getFullName() {
        return fullName;
    }




}

