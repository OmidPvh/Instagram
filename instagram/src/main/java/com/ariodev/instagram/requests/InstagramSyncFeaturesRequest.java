package com.ariodev.instagram.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ariodev.instagram.requests.payload.InstagramSyncFeaturesPayload;
import com.ariodev.instagram.requests.payload.InstagramSyncFeaturesResult;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;

/**
 * Created by root on 09/06/17.
 */

@AllArgsConstructor
public class InstagramSyncFeaturesRequest extends InstagramPostRequest<InstagramSyncFeaturesResult> {

    @NonNull
    private InstagramSyncFeaturesPayload payload;

    @Override
    public String getUrl() {
        return "qe/sync/";
    }

    @Override
    @SneakyThrows
    public String getPayload() {
        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(payload);

        return payloadJson;
    }

    @Override
    @SneakyThrows
    public InstagramSyncFeaturesResult parseResult(int statusCode, String content) {
        return new InstagramSyncFeaturesResult();
    }


}
