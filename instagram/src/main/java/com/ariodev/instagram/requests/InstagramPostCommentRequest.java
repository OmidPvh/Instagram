package com.ariodev.instagram.requests;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;

public class InstagramPostCommentRequest extends InstagramPostRequest<InstagramPostCommentResult>
{
    private long mediaId;
    private String commentText;

    public String getUrl()
    {
        return "media/" + this.mediaId + "/comment/";
    }

    public String getPayload()
    {
        try
        {
            Map<String, Object> likeMap = new LinkedHashMap();
            likeMap.put("_uuid", this.api.getUuid());
            likeMap.put("_uid", this.api.getUserID());
            likeMap.put("_csrftoken", this.api.getOrFetchCsrf(null));
            likeMap.put("comment_text", this.commentText);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(likeMap);
        } catch (Exception e)
        {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    public InstagramPostCommentResult parseResult(int statusCode, String content)
    {
        try
        {
            return this.parseJson(statusCode, content, InstagramPostCommentResult.class);
        } catch (Throwable var4)
        {
            throw var4;
        }
    }

    public InstagramPostCommentRequest(long mediaId, String commentText)
    {
        this.mediaId = mediaId;
        this.commentText = commentText;
    }
}
