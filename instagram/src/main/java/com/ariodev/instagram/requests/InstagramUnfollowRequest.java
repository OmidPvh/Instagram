package com.ariodev.instagram.requests;

import com.ariodev.instagram.Instagram;
import com.ariodev.instagram.requests.payload.StatusResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;


public class InstagramUnfollowRequest extends InstagramPostRequest<StatusResult>
{

    private long userId;

    public String getUrl()
    {
        return "friendships/destroy/" + this.userId + "/";
    }

    @Override
    public Instagram getApi()
    {
        return super.getApi();
    }

    public String getPayload()
    {

        try
        {
            Map<String, Object> likeMap = new LinkedHashMap();
            likeMap.put("_uuid", this.api.getUuid());
            likeMap.put("_uid", this.api.getUserId());
            likeMap.put("user_id", this.userId);
            likeMap.put("_csrftoken", this.api.getOrFetchCsrf(null));
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(likeMap);
        } catch (Exception e)
        {
            e.printStackTrace();
            return e.getMessage();
        }


    }

    public StatusResult parseResult(int statusCode, String content)
    {
        try
        {
            return this.parseJson(statusCode, content, StatusResult.class);
        } catch (Throwable var4)
        {
            throw var4;
        }
    }

    public InstagramUnfollowRequest(long userId)
    {
        this.userId = userId;
    }
}
