package com.storage.snippet.service;

import com.storage.snippet.apimodel.SnippetCreationRequest;
import com.storage.snippet.apimodel.SnippetCreationResponse;
import com.storage.snippet.exceptions.RequestProcessingException;
import com.storage.snippet.exceptions.ResourceNotFoundException;
import com.storage.snippet.exceptions.ValidationException;

public interface SnippetManagementService {

    SnippetCreationResponse createSnippet(SnippetCreationRequest snippetCreationRequest) throws ValidationException;
    SnippetCreationResponse getSnippet(String name) throws ResourceNotFoundException, RequestProcessingException;
    SnippetCreationResponse likeSnippet(String name) throws ResourceNotFoundException;
}
