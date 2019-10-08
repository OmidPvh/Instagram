package com.ariodev.instagram.requests;

import com.ariodev.instagram.InstagramConstants;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by root on 08/06/17.
 */

public abstract class InstagramGetRequest<T> extends InstagramRequest<T> {

    @Override
    public String getMethod() {
        return "GET";
    }

    @Override
    public T execute() throws IOException {

        Request request = new Request.Builder()
                .url(InstagramConstants.API_URL + getUrl())
                .addHeader("Connection", "close")
                .addHeader("Accept", "*/*")
                .addHeader("Cookie2", "$Version=1")
                .addHeader("Accept-Language", "en-US")
                .addHeader("User-Agent", InstagramConstants.getUserAgent())
                .build();

        Response response = api.getClient().newCall(request).execute();
        api.setLastResponse(response);

        int resultCode = response.code();
        String content = response.body().string();
//        Log.d("GET", "Code: " + resultCode);

        return parseResult(resultCode, content);

    }

}
