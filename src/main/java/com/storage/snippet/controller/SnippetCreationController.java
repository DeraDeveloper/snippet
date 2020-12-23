package com.storage.snippet.controller;

import com.storage.snippet.apimodel.SnippetCreationRequest;
import com.storage.snippet.apimodel.SnippetCreationResponse;
import com.storage.snippet.service.SnippetManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/create-snippet")
public class SnippetCreationController {

    @Autowired
    private SnippetManagementService snippetManagementService;

    @PostMapping
    public SnippetCreationResponse createSnippet(@RequestBody SnippetCreationRequest snippetCreationRequest){
        return snippetManagementService.createSnippet(snippetCreationRequest);
    }
}
