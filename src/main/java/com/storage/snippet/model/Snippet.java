package com.storage.snippet.model;

public class Snippet {
    private String name;
    private String expires_at;
    private String snippet;
    private int likes;

    public Snippet(String name, String expires_at, String snippet) {
        this.name = name;
        this.expires_at = expires_at;
        this.snippet = snippet;
        this.likes = 0;
    }

    public String getName() {
        return name;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public int getLikes() {
        return likes;
    }

    private void setLikes(int likes) {
        this.likes = likes;
    }

    public void incrementLike(){
        this.setLikes(this.getLikes()+1);
    }
}
