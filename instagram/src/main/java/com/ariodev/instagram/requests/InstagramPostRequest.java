package com.ariodev.instagram.requests;

import android.util.Log;

import com.ariodev.instagram.InstagramConstants;
import com.ariodev.instagram.util.InstagramHashUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by root on 08/06/17.
 */

public abstract class InstagramPostRequest<T> extends InstagramRequest<T>
{

    @Override
    public String getMethod()
    {
        return "POST";
    }

    @Override
    public T execute() throws IOException
    {

        //        Request request = new Request.Builder().url(InstagramConstants.API_URL + getUrl())
        //                                               .addHeader("Connection", "close")
        //                                               .addHeader("Accept", "*/*")
        //                                               .addHeader("Cookie2", "$Version=1")
        //                                               .addHeader("Accept-Language", "en-US")
        //                                               .addHeader("X-IG-Capabilities", "3boBAA==")
        //                                               .addHeader("X-IG-Connection-Type", "WIFI")
        //                                               .addHeader("X-IG-Connection-Speed", "-1kbps")
        //                                               .addHeader("X-IG-App-ID", "567067343352427")
        //                                               .addHeader("User-Agent", InstagramConstants.getUserAgent())
        //                                               .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), InstagramHashUtil.generateSignature(getPayload())))
        //                                               .build();

        Request request = new Request.Builder().url(InstagramConstants.API_URL + getUrl())
                                               .addHeader("Connection", "close")
                                               .addHeader("Accept", "*/*")
                                               .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                               .addHeader("Cookie2", "$Version=1")
                                               .addHeader("Accept-Language", "en-US")
                                               .addHeader("User-Agent", InstagramConstants.getUserAgent())
                                               .build();

        Response response = api.getClient()
                               .newCall(request)
                               .execute();
        api.setLastResponse(response);

        int resultCode = response.code();
        String content = null;
        if (response.body() != null)
        {
            content = response.body()
                              .string();
        }
        Log.i("InstagramPostRequest", "execute: " + content);
        return parseResult(resultCode, content);
    }
}
