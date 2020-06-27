package com.ariodev.instagram.requests;

import com.ariodev.instagram.requests.payload.StatusResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.HttpUrl;

public class InstagramBlockRequest extends InstagramPostRequest<StatusResult>
{
    private long userId;

    @Override
    public String getUrl() {
        return "friendships/block/" + userId + "/";
    }


    @Override
    public String getPayload() {


        try
        {
            HttpUrl httpUrl = HttpUrl.parse(getUrl());
            Map<String, Object> likeMap = new LinkedHashMap<>();
            likeMap.put("_uuid", api.getUuid());
            likeMap.put("_uid", api.getUserId());
            likeMap.put("user_id", userId);
            likeMap.put("_csrftoken", api.getOrFetchCsrf(httpUrl));
            ObjectMapper mapper = new ObjectMapper();
            String payloadJson = mapper.writeValueAsString(likeMap);
            return payloadJson;
        } catch (IOException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }


    }

    @Override
    public StatusResult parseResult(int resultCode, String content) {
        return parseJson(resultCode, content, StatusResult.class);
    }

    InstagramBlockRequest(long userId)
    {
        this.userId = userId;
    }
}
