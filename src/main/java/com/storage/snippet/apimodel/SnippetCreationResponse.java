package com.storage.snippet.apimodel;

import java.util.Date;

public class SnippetCreationResponse {
    private String url;
    private String name;
    private String expires_at;
    private String snippet;

    public SnippetCreationResponse(String url, String name, String expires_at, String snippet) {
        this.url = url;
        this.name = name;
        this.expires_at = expires_at;
        this.snippet = snippet;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
