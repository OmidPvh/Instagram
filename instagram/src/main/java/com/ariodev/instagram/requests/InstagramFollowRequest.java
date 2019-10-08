/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ariodev.instagram.requests;

import com.ariodev.instagram.requests.payload.StatusResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Follow Request
 *
 * @author Bruno Candido Volpato da Cunha
 */

public class InstagramFollowRequest extends InstagramPostRequest<StatusResult>
{

    private long userId;

    @Override
    public String getUrl()
    {
        return "friendships/create/" + userId + "/";
    }

    @Override

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
        } catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return e.getMessage();
        } catch (Exception e)
        {
            e.printStackTrace();
            return e.getMessage();
        }


    }

    @Override
    public StatusResult parseResult(int statusCode, String content)
    {
        return parseJson(statusCode, content, StatusResult.class);
    }


    public InstagramFollowRequest(long userId)
    {
        this.userId = userId;

    }


}
