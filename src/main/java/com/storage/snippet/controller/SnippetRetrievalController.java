package com.storage.snippet.controller;

import com.storage.snippet.apimodel.SnippetCreationResponse;
import com.storage.snippet.service.SnippetManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/get-snippet")
public class SnippetRetrievalController {
    @Autowired
    private SnippetManagementService snippetManagementService;

    @GetMapping("/{name}")
    public SnippetCreationResponse createSnippet(@PathVariable("name") String name){
        return snippetManagementService.getSnippet(name);
    }
}
