package com.ariodev.instagram;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.ariodev.instagram.requests.InstagramAutoCompleteUserListRequest;
import com.ariodev.instagram.requests.InstagramFbLoginRequest;
import com.ariodev.instagram.requests.InstagramGetInboxRequest;
import com.ariodev.instagram.requests.InstagramGetRecentActivityRequest;
import com.ariodev.instagram.requests.InstagramLoginRequest;
import com.ariodev.instagram.requests.InstagramRequest;
import com.ariodev.instagram.requests.InstagramSyncFeaturesRequest;
import com.ariodev.instagram.requests.internal.InstagramFetchHeadersRequest;
import com.ariodev.instagram.requests.payload.InstagramFbLoginPayload;
import com.ariodev.instagram.requests.payload.InstagramLoginPayload;
import com.ariodev.instagram.requests.payload.InstagramLoginResult;
import com.ariodev.instagram.requests.payload.InstagramSyncFeaturesPayload;
import com.ariodev.instagram.util.InstagramGenericUtil;
import com.ariodev.instagram.util.InstagramHashUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by root on 08/06/17.
 */

public class Instagram
{


    public static Context context;
    public static String randomKey;

    @Getter
    protected String deviceId;

    @Getter
    @Setter
    public static String username;

    @Getter
    @Setter
    public static String password;

    @Getter
    @Setter
    private String accessToken;

    @Getter
    @Setter
    protected boolean isLoggedIn;

    @Getter
    @Setter
    private String uuid;

    @Getter
    @Setter
    protected String rankToken;

    @Getter
    @Setter
    private long userId;

    @Getter
    @Setter
    protected Response lastResponse;

    @Getter
    @Setter
    protected OkHttpClient client;

    private final HashMap<String, Cookie> cookieStore = new HashMap<>();

    /**
     * Input this parameter carefully
     *
     * @param username -> instagram username
     * @param password -> instagram password
     * @param context  -> context you want to login.
     */

    @Builder
    public Instagram(String username, String password, String randomKey, Context context)
    {
        super();
        this.username = username;
        this.password = password;
        this.context = context;
        this.randomKey = randomKey;
    }


    public void setup()
    {

        /*
        if (this.username.length() < 1) {
            throw new IllegalArgumentException("Username is mandatory.");
        }

        if (this.password.length() < 1) {
            throw new IllegalArgumentException("Password is mandatory.");
        }*/

        this.deviceId = InstagramHashUtil.generateDeviceId(username, password);
        this.uuid = InstagramGenericUtil.generateUuid(true);

        client = new OkHttpClient.Builder().cookieJar(new CookieJar()
        {

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies)
            {
                if (cookies != null)
                {
                    for (Cookie cookie : cookies)
                    {
                        cookieStore.put(cookie.name(), cookie);
                    }
                }
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url)
            {
                List<Cookie> validCookies = new ArrayList<>();
                for (Map.Entry<String, Cookie> entry : cookieStore.entrySet())
                {
                    Cookie cookie = entry.getValue();
                    if (cookie.expiresAt() >= System.currentTimeMillis())
                    {
                        validCookies.add(cookie);
                    }
                }
                return validCookies;
            }
        })
                                           .build();

    }

    public InstagramLoginResult loginFb() throws IOException
    {

        InstagramFbLoginPayload loginRequest = InstagramFbLoginPayload.builder()
                                                                      .dryrun(true)
                                                                      /*.username(username)*/
                                                                      .adid(InstagramGenericUtil.generateUuid(false))
                                                                      .device_id(deviceId)
                                                                      .fb_access_token(password)
                                                                      .phone_id(InstagramGenericUtil.generateUuid(false))
                                                                      .waterfall_id(InstagramGenericUtil.generateUuid(false))
                                                                      /*.allow_contacts_sync(true)
                                                                      .big_blue_token(password)*/
                                                                      .build();

        InstagramLoginResult loginResult = this.sendRequest(new InstagramFbLoginRequest(loginRequest));
        if (loginResult.getStatus()
                       .equalsIgnoreCase("ok"))
        {
            System.out.println(cookieStore.toString());
            this.userId = loginResult.getLogged_in_user()
                                     .getPk();
            this.rankToken = this.userId + "_" + this.uuid;
            this.isLoggedIn = true;

            InstagramSyncFeaturesPayload syncFeatures = InstagramSyncFeaturesPayload.builder()
                                                                                    ._uuid(uuid)
                                                                                    ._csrftoken(getOrFetchCsrf(null))
                                                                                    ._uid(userId)
                                                                                    .id(userId)
                                                                                    .experiments(InstagramConstants.DEVICE_EXPERIMENTS)
                                                                                    .build();

            this.sendRequest(new InstagramSyncFeaturesRequest(syncFeatures));
            this.sendRequest(new InstagramAutoCompleteUserListRequest());
            //this.sendRequest(new InstagramTimelineFeedRequest());
            this.sendRequest(new InstagramGetInboxRequest());
            this.sendRequest(new InstagramGetRecentActivityRequest());
        }

        System.out.println("Hello! --> " + loginResult.toString());

        return loginResult;

    }

    public InstagramLoginResult login() throws IOException
    {

        //Log.d("LOGIN", "Logging with user " + username + " and password " + password.replaceAll("[a-zA-Z0-9]", "*"));

        InstagramLoginPayload loginRequest = InstagramLoginPayload.builder()
                                                                  .username(username)
                                                                  .password(password)
                                                                  .guid(uuid)
                                                                  .device_id(deviceId)
                                                                  .phone_id(InstagramGenericUtil.generateUuid(true))
                                                                  .login_attempt_account(0)
                                                                  ._csrftoken(getOrFetchCsrf(null))
                                                                  .build();

        InstagramLoginResult loginResult = this.sendRequest(new InstagramLoginRequest(loginRequest));
        if (loginResult.getStatus()
                       .equalsIgnoreCase("ok"))
        {
            this.userId = loginResult.getLogged_in_user()
                                     .getPk();
            this.rankToken = this.userId + "_" + this.uuid;
            this.isLoggedIn = true;

            InstagramSyncFeaturesPayload syncFeatures = InstagramSyncFeaturesPayload.builder()
                                                                                    ._uuid(uuid)
                                                                                    ._csrftoken(getOrFetchCsrf(null))
                                                                                    ._uid(userId)
                                                                                    .id(userId)
                                                                                    .experiments(com.ariodev.instagram.InstagramConstants.DEVICE_EXPERIMENTS)
                                                                                    .build();

            this.sendRequest(new InstagramSyncFeaturesRequest(syncFeatures));
            this.sendRequest(new InstagramAutoCompleteUserListRequest());
            //this.sendRequest(new InstagramTimelineFeedRequest());
            this.sendRequest(new InstagramGetInboxRequest());
            this.sendRequest(new InstagramGetRecentActivityRequest());
        }


        return loginResult;
    }


    public String getOrFetchCsrf(HttpUrl url) throws IOException
    {

        Cookie cookie = getCsrfCookie(url);
        if (cookie == null)
        {
            sendRequest(new InstagramFetchHeadersRequest());
            cookie = getCsrfCookie(url);
        }

        return cookie.value();

    }

    public Cookie getCsrfCookie(HttpUrl url)
    {

        for (Cookie cookie : client.cookieJar()
                                   .loadForRequest(url))
        {


            if (cookie.name()
                      .equalsIgnoreCase("csrftoken"))
            {
                return cookie;
            }

        }

        return null;

    }

    public <T> T sendRequest(InstagramRequest<T> request) throws IOException
    {

        if (!this.isLoggedIn && request.requiresLogin())
        {
            throw new IllegalStateException("Need to login first!");
        }

        request.setApi(this);
        T response = request.execute();

        return response;
    }

    public void logout(Context context)
    {
        CookieSyncManager.createInstance(context);
        CookieManager instance = CookieManager.getInstance();
        instance.removeAllCookie();
        instance.setAcceptCookie(true);
    }

}
