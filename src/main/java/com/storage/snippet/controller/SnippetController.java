package com.storage.snippet.controller;

import com.storage.snippet.apimodel.SnippetCreationRequest;
import com.storage.snippet.apimodel.SnippetCreationResponse;
import com.storage.snippet.exceptions.RequestProcessingException;
import com.storage.snippet.exceptions.ResourceNotFoundException;
import com.storage.snippet.exceptions.ValidationException;
import com.storage.snippet.service.SnippetManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/snippets")
public class SnippetController {

    @Autowired
    private SnippetManagementService snippetManagementService;

    @PostMapping
    public SnippetCreationResponse createSnippet(@RequestBody SnippetCreationRequest snippetCreationRequest) throws ValidationException {
        return snippetManagementService.createSnippet(snippetCreationRequest);
    }

    @GetMapping("/{name}")
    public SnippetCreationResponse createSnippet(@PathVariable("name") String name) throws ResourceNotFoundException, RequestProcessingException {
        return snippetManagementService.getSnippet(name);
    }

    @PostMapping("/{name}/like")
    public SnippetCreationResponse likeSnippet(@PathVariable("name") String name) throws ResourceNotFoundException {
        return snippetManagementService.likeSnippet(name);
    }

}
