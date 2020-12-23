package com.storage.snippet.apimodel;

public class SnippetCreationRequest {

    private String name;
    private int expires_in;
    private String snippet;

    public SnippetCreationRequest(String name, int expires_in, String snippet) {
        this.name = name;
        this.expires_in = expires_in;
        this.snippet = snippet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }
}
