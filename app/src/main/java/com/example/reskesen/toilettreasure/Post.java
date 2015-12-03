package com.example.reskesen.toilettreasure;

/**
 * Created by Reskesen on 19-11-2015.
 */
public class Post {


    private String post;
    private String location;
    private String user;
    private int likes;
    private int spiritanimal;



    public void setPost(String post) {
        this.post = post;
    }

    public Post(String post, String location, String user, int likes, int spiritanimal) {
        this.post = post;
        this.location = location;
        this.user = user;
        this.likes = likes;
        this.spiritanimal = spiritanimal;
    }

    public String getLocation() {
        return location;
    }

    public int getSpiritanimal() {
        return spiritanimal;
    }

    public void setSpiritanimal(int spiritAnimal) {
        this.spiritanimal = spiritAnimal;
    }

    public String getPost()

    {
        return post;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
