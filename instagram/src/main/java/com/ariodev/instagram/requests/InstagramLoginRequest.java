package com.ariodev.instagram.requests;

import android.util.Log;

import com.ariodev.instagram.requests.payload.InstagramLoginPayload;
import com.ariodev.instagram.requests.payload.InstagramLoginResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * Created by root on 09/06/17.
 */

@AllArgsConstructor
public class InstagramLoginRequest extends InstagramPostRequest<InstagramLoginResult>
{

    private InstagramLoginPayload payload;

    @Override
    public String getUrl()
    {
        return "accounts/login/";
    }

    @Override
    @SneakyThrows
    public String getPayload()
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(payload);
    }

    @Override
    @SneakyThrows
    public InstagramLoginResult parseResult(int statusCode, String content)
    {

        Log.i("InstagramLoginRequest", "parseResult: " + content);

        return parseJson(statusCode, content, InstagramLoginResult.class);
    }

    @Override
    public boolean requiresLogin()
    {
        return false;
    }
}
