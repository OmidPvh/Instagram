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
import com.ariodev.instagram.requests.internal.InstagramFetchHeadersRequest;
import com.ariodev.instagram.requests.internal.InstagramSyncFeaturesRequest;
import com.ariodev.instagram.requests.payload.InstagramFbLoginPayload;
import com.ariodev.instagram.requests.payload.InstagramLoginPayload;
import com.ariodev.instagram.requests.payload.InstagramLoginResult;
import com.ariodev.instagram.requests.payload.InstagramLoginTwoFactorPayload;
import com.ariodev.instagram.requests.payload.InstagramLoginTwoFactorRequest;
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
    @Getter
    @Setter
    protected String identifier;

    @Getter
    @Setter
    protected String verificationCode;

    @Getter
    @Setter
    protected String challengeUrl;


    @Getter
    @Setter
    static Context context;

    @Getter
    @Setter
    protected String Csrf;


    @Getter
    @Setter
    protected String cookie;

    @Getter
    @Setter
    protected String deviceId;


    @Getter
    @Setter
    public static String username;

    @Getter
    @Setter
    public static String password;


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
    private long userID;

    @Getter
    @Setter
    protected Response lastResponse;

    @Getter
    @Setter
    protected OkHttpClient client;

    @Getter
    @Setter
    protected HashMap<String, Cookie> cookieStore = new HashMap<>();


    /**
     * Input this parameter carefully
     *
     * @param username -> instagram username
     * @param password -> instagram password
     * @param context  -> context you want to login.
     */

    @Builder
    public Instagram(String username, String password, Context context)
    {
        super();
        Instagram.username = username;
        Instagram.password = password;
        Instagram.context = context;
    }

    @Builder
    public Instagram(Context context, String Csrf, String verificationCode, String challenge_url, String cookie, OkHttpClient client, String device_id, String identifier, Response lastResponse, String username, String password, long userId, String uuid, HashMap<String, Cookie> cookieStore, String rankToken)
    {
        super();
        this.challengeUrl = challenge_url;
        this.verificationCode = verificationCode;
        this.Csrf = Csrf;
        this.cookie = cookie;
        this.deviceId = device_id;
        this.identifier = identifier;
        this.lastResponse = lastResponse;
        this.context = context;
        this.client = client;
        this.username = username;
        this.password = password;
        this.rankToken = rankToken;
        this.userID = userId;
        this.uuid = uuid;
        this.cookieStore = cookieStore;
        this.isLoggedIn = true;
    }


    public void setup()
    {

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
                                                                      .phone_id(uuid)
                                                                      .waterfall_id(InstagramGenericUtil.generateUuid(false))
                                                                      /*.allow_contacts_sync(true)
                                                                      .big_blue_token(password)*/
                                                                      .build();

        InstagramLoginResult loginResult = this.sendRequest(new InstagramFbLoginRequest(loginRequest));
        emulateUserLoggedIn(loginResult);


        return loginResult;

    }

    public InstagramLoginResult login() throws IOException
    {


        InstagramLoginPayload loginRequest = InstagramLoginPayload.builder()
                                                                  .username(username)
                                                                  .password(password)
                                                                  .guid(uuid)
                                                                  .device_id(deviceId)
                                                                  .phone_id(deviceId)
                                                                  .login_attempt_account(0)
                                                                  ._csrftoken(getOrFetchCsrf(null))
                                                                  .build();

        InstagramLoginResult loginResult = this.sendRequest(new InstagramLoginRequest(loginRequest));

        emulateUserLoggedIn(loginResult);

        //        if (loginResult.getTwo_factor_info() != null)
        //        {
        //            identifier = loginResult.getTwo_factor_info().getTwo_factor_identifier();
        //        }
        //        else
        //
        if (loginResult.getCheckpoint_url() != null)
        {
            // logic for challenge

            //            Log.i("Instagram", "Challenge required: " + loginResult.getCheckpoint_url());
        }

        return loginResult;
    }

    public InstagramLoginResult login(String verificationCode) throws Exception
    {
        if (identifier == null)
        {
            login();
        }
        InstagramLoginTwoFactorPayload loginRequest = InstagramLoginTwoFactorPayload.builder()
                                                                                    .username(username)

                                                                                    .verification_code(verificationCode)
                                                                                    .two_factor_identifier(identifier)
                                                                                    .password(password)
                                                                                    .guid(uuid)
                                                                                    .device_id(deviceId)
                                                                                    .phone_id(InstagramGenericUtil.generateUuid(true))
                                                                                    .login_attempt_account(0)
                                                                                    ._csrftoken(getOrFetchCsrf(null))
                                                                                    .build();
        InstagramLoginTwoFactorRequest req = new InstagramLoginTwoFactorRequest(loginRequest);
        InstagramLoginResult loginResult = this.sendRequest(req);
        emulateUserLoggedIn(loginResult);
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

        //        Log.i("Instagram", "getOrFetchCsrf: " + cookie.value());
        //        return "mFK3r3uPeitQsxjCHJvAXBQ9ErZ8zvWB";
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

    private void emulateUserLoggedIn(InstagramLoginResult loginResult) throws IOException
    {
        if (loginResult.getStatus()
                       .equalsIgnoreCase("ok"))
        {
            this.userID = loginResult.getLogged_in_user()
                                     .getPk();
            this.rankToken = this.userID + "_" + this.uuid;
            this.isLoggedIn = true;

            this.sendRequest(new InstagramSyncFeaturesRequest(false));
            this.sendRequest(new InstagramAutoCompleteUserListRequest());
            //            this.sendRequest(new InstagramTimelineFeedRequest());
            this.sendRequest(new InstagramGetInboxRequest());
            this.sendRequest(new InstagramGetRecentActivityRequest());
        }


    }

    // 1.2.9 Return to default InstagramLoginResult


}
