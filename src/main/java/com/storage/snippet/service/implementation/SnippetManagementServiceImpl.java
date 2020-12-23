package com.storage.snippet.service.implementation;

import com.storage.snippet.apimodel.SnippetCreationRequest;
import com.storage.snippet.apimodel.SnippetCreationResponse;
import com.storage.snippet.exceptions.RequestProcessingException;
import com.storage.snippet.exceptions.ResourceNotFoundException;
import com.storage.snippet.exceptions.ValidationException;
import com.storage.snippet.model.Snippet;
import com.storage.snippet.service.SnippetManagementService;
import com.storage.snippet.util.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
public class SnippetManagementServiceImpl implements SnippetManagementService {


    @Value("${baseUrl}")
    private String hostBaseUrl;
    private Map<String, Snippet> snippetMap = new HashMap<>();
    private static final String SNIPPET_RETRIEVAL_URL = "/api/get-snippet";
    private static final int EXPIRY_TIME_EXTENSION = 30;

    @Override
    public SnippetCreationResponse createSnippet(SnippetCreationRequest snippetCreationRequest) throws ValidationException {

        validateSnippetCreationRequest(snippetCreationRequest);
        String name = snippetCreationRequest.getName();

        Snippet snippet = new Snippet(name, Util.generateDate(snippetCreationRequest.getExpires_in()),
                snippetCreationRequest.getSnippet());
        snippetMap.put(name, snippet);

        return buildResponse(snippet);
    }

    @Override
    public SnippetCreationResponse getSnippet(String name) throws ResourceNotFoundException, RequestProcessingException {

        checkThatSnippetExists(name);
        Snippet snippet = snippetMap.get(name);

        if(!hasSnippetExpired(snippet)){
            updateExpiryTime(name, EXPIRY_TIME_EXTENSION);
            return buildResponse(snippetMap.get(name));
        }else{
            snippetMap.remove(name);
            throw new ResourceNotFoundException("Snippet with name "+ name + " has expired");
        }

    }

    @Override
    public SnippetCreationResponse likeSnippet(String name) throws ResourceNotFoundException {
        checkThatSnippetExists(name);
        Snippet snippet = snippetMap.get(name);
        snippet.incrementLike();
        snippetMap.put(name,snippet);
        updateExpiryTime(name, EXPIRY_TIME_EXTENSION);
        return buildResponse(snippetMap.get(name));
    }

    private void validateSnippetCreationRequest(SnippetCreationRequest snippetCreationRequest) throws ValidationException {

        if((snippetCreationRequest.getName().isEmpty())){
            throw new ValidationException("Snippet name cannot be empty");
        }

        if((snippetCreationRequest.getExpires_in() < 0)){
            throw new ValidationException("Snippet expiry time should be greater than 0");
        }

        if((snippetCreationRequest.getSnippet().isEmpty())){
            throw new ValidationException("Snippet text should not be empty");
        }

    }

    private SnippetCreationResponse buildResponse(Snippet snippet){

        String url = hostBaseUrl.concat(SNIPPET_RETRIEVAL_URL).concat("/").concat(snippet.getName());
        SnippetCreationResponse snippetCreationResponse = new SnippetCreationResponse(url, snippet.getName(),
                snippet.getExpires_at(), snippet.getSnippet());

        snippetCreationResponse.setLikes(snippet.getLikes());

        return snippetCreationResponse;
    }

    private void updateExpiryTime(String snippetName, int seconds){

        Snippet snippet = snippetMap.get(snippetName);
        snippet.setExpires_at(Util.generateDate(seconds));
        snippetMap.put(snippetName,snippet);

    }

    private boolean hasSnippetExpired(Snippet snippet) throws RequestProcessingException {

        Date snippetDate;
        try{
            snippetDate = Util.convertStringToDate(snippet.getExpires_at());
        }catch (ParseException e){
            System.out.println("error parsing date string "+ snippet.getExpires_at());
            throw new RequestProcessingException("Error processing request");
        }

        Date currentDate = Util.getCurrentDate();

        return currentDate.compareTo(snippetDate) > 0;

    }

    private void checkThatSnippetExists(String name) throws ResourceNotFoundException {

        if(!snippetMap.containsKey(name)){
            throw new ResourceNotFoundException("Snippet with name "+ name + " not found");
        }

    }
}
