package com.ariodev.instagram.requests.internal;

import com.ariodev.instagram.InstagramConstants;
import com.ariodev.instagram.requests.InstagramPostRequest;
import com.ariodev.instagram.requests.payload.InstagramSyncFeaturesResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;


/**
 * Sync Features Request
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
@AllArgsConstructor

public class InstagramSyncFeaturesRequest extends InstagramPostRequest<InstagramSyncFeaturesResult>
{

    private boolean preLogin;
    
    @Override
    public String getUrl() {
        return "qe/sync/";
    }

    @Override
    @SneakyThrows
    public String getPayload() {
        
        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("id", api.getUuid());
        likeMap.put("experiments", InstagramConstants.DEVICE_EXPERIMENTS);
        
        if (!preLogin) {
            likeMap.put("_uuid", api.getUuid());
            likeMap.put("_uid", api.getUserID());
            likeMap.put("_csrftoken", api.getOrFetchCsrf(null));
            
        }
        
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(likeMap);
    }

    @Override
    @SneakyThrows
    public InstagramSyncFeaturesResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramSyncFeaturesResult.class);
    }

    /**
     * @return if request must be logged in
     */
    @Override
    public boolean requiresLogin() {
        return false;
    }

}
