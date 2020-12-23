package com.storage.snippet.service;

import com.storage.snippet.apimodel.SnippetCreationRequest;
import com.storage.snippet.apimodel.SnippetCreationResponse;

public interface SnippetManagementService {

    SnippetCreationResponse createSnippet(SnippetCreationRequest snippetCreationRequest);
    SnippetCreationResponse getSnippet(String name);
}
