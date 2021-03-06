package com.ariodev.instagram.requests;

import com.ariodev.instagram.requests.payload.StatusResult;

import lombok.SneakyThrows;

/**
 * Created by root on 09/06/17.
 */

public class InstagramGetRecentActivityRequest extends InstagramGetRequest<StatusResult> {

    @Override
    public String getUrl() {
        return "news/inbox/?";
    }

    @Override
    @SneakyThrows
    public StatusResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, StatusResult.class);
    }
}
