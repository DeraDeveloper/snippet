package com.storage.snippet.model;

public class Snippet {
    private String name;
    private String expires_at;
    private String snippet;

    public Snippet(String name, String expires_at, String snippet) {
        this.name = name;
        this.expires_at = expires_at;
        this.snippet = snippet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
