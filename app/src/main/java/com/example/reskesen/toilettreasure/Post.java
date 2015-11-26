package com.example.reskesen.toilettreasure;

/**
 * Created by Reskesen on 19-11-2015.
 */
public class Post {


    private String post;
    private String location;
    private String user;
    private int likes;



    public void setPost(String post) {
        this.post = post;
    }

    public Post(String post, String location, String user, int likes) {
        this.post = post;
        this.location = location;
        this.user = user;
        this.likes = likes;
    }

    public String getLocation() {
        return location;
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
