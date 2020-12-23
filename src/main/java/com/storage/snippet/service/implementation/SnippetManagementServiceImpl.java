package com.storage.snippet.service.implementation;

import com.storage.snippet.apimodel.SnippetCreationRequest;
import com.storage.snippet.apimodel.SnippetCreationResponse;
import com.storage.snippet.model.Snippet;
import com.storage.snippet.service.SnippetManagementService;
import com.storage.snippet.util.Util;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
public class SnippetManagementServiceImpl implements SnippetManagementService {

    private Map<String, Snippet> snippetMap = new HashMap();
    private final String snippetRetrievalUrl = "/api/get-snippet";
    private static final int EXPIRY_TIME_EXTENSION = 30;

    @Override
    public SnippetCreationResponse createSnippet(SnippetCreationRequest snippetCreationRequest){

        validateSnippetCreationRequest(snippetCreationRequest);
        String name = snippetCreationRequest.getName();

        Snippet snippet = new Snippet(name, Util.generateDate(snippetCreationRequest.getExpires_in()),
                snippetCreationRequest.getSnippet());
        snippetMap.put(name, snippet);

        return buildResponse(snippet);
    }

    @Override
    public SnippetCreationResponse getSnippet(String name){
        if(!snippetMap.containsKey(name)){
            // TODO: 12/22/20 set errpr message
        }

        Snippet snippet = snippetMap.get(name);

        if(!hasSnippetExpired(snippet)){
            updateExpiryTime(name, EXPIRY_TIME_EXTENSION);
            return buildResponse(snippetMap.get(name));
        }else{
            snippetMap.remove(name);
            // TODO: 12/22/20 set error message and delete this line
            return buildResponse(snippetMap.get(name));
        }

    }

    private void validateSnippetCreationRequest(SnippetCreationRequest snippetCreationRequest){

        if((snippetCreationRequest.getName().isEmpty())){
            return;
        }

        if((snippetCreationRequest.getExpires_in() < 0)){
            return;
        }

        if((snippetCreationRequest.getSnippet().isEmpty())){
            return;
        }

        if(snippetMap.containsKey(snippetCreationRequest.getName())){
            // TODO: 12/22/20 set error message
            return;
        }

    }

    private SnippetCreationResponse buildResponse(Snippet snippet){

        String url = snippetRetrievalUrl.concat("/").concat(snippet.getName());
        SnippetCreationResponse snippetCreationResponse = new SnippetCreationResponse(url, snippet.getName(),
                snippet.getExpires_at(), snippet.getSnippet());

        return snippetCreationResponse;
    }

    private void updateExpiryTime(String snippetName, int seconds){

        Snippet snippet = snippetMap.get(snippetName);
        snippet.setExpires_at(Util.generateDate(seconds));
        snippetMap.put(snippetName,snippet);

    }

    private boolean hasSnippetExpired(Snippet snippet){

        Date snippetDate;
        try{
            snippetDate = Util.convertStringToDate(snippet.getExpires_at());
        }catch (ParseException e){
            System.out.println("error parsing string");
            return false;
        }

        Date currentDate = Util.getCurrentDate();

        if(currentDate.compareTo(snippetDate) > 0) {
            return true;
        } else {
            return false;
        }

    }

}
