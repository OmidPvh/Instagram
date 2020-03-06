package com.ariodev.instagram.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ariodev.instagram.requests.payload.InstagramCurrentUserResult;
import com.ariodev.instagram.util.InstagramGenericUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.SneakyThrows;

/**
 * Created by Charlie on 15/06/17.
 */

public class InstagramCurrentUserRequest extends InstagramPostRequest<InstagramCurrentUserResult>{

    @Override
    public String getUrl() {
        Map<String, String> params = new HashMap<>();
        params.put("edit", "true");

        return "accounts/current_user" + InstagramGenericUtil.generateQueryParams(params);
    }

    @Override
    @SneakyThrows
    public String getPayload() {

        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("_uuid", api.getUuid());
        likeMap.put("_uid", api.getUserID());
        likeMap.put("_csrftoken", api.getOrFetchCsrf(null));

        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(likeMap);

        return payloadJson;
    }

    @Override
    public InstagramCurrentUserResult parseResult(int resultCode, String content) {
        return parseJson(resultCode, content, InstagramCurrentUserResult.class);
    }
}
