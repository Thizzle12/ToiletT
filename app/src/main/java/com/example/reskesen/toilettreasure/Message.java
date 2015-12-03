package com.example.reskesen.toilettreasure;

/**
 * Created by Theis on 03-12-2015.
 */
public class Message {

   private String message, location, username;
   private int spiritanimal, likes;

    public Message(int spiritanimal, String message, String username, String location, int likes) {
        this.spiritanimal = spiritanimal;
        this.message = message;
        this.username = username;
        this.location = location;
        this.likes = likes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSpiritanimal() {
        return spiritanimal;
    }

    public void setSpiritanimal(int spiritanimal) {
        this.spiritanimal = spiritanimal;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
