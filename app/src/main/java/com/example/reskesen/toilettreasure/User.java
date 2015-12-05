package com.example.reskesen.toilettreasure;

/**
 * Created by Reskesen on 12-11-2015.
 */
public class User {


    private String password;
    private String fullName;
    private int spiritanimal;



    public User(String fullName, String password, int spiritanimal) {
        this.fullName = fullName;
        this.password = password;

        this.spiritanimal = spiritanimal;

    }

    public String getPassword()

    {
        return password;
    }



    public String getFullName() {
        return fullName;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getSpiritanimal() {
        return spiritanimal;
    }

    public void setSpiritanimal(int spititanimal) {
        this.spiritanimal = spititanimal;
    }

}

