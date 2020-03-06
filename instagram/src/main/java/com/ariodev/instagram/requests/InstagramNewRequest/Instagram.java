package com.ariodev.instagram.requests.InstagramNewRequest;

import com.ariodev.instagram.requests.payload.InstagramLoginResult;
import com.ariodev.instagram.util.InstagramGenericUtil;
import com.ariodev.instagram.util.InstagramHashUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

@Getter
public class Instagram
{

    protected String uuid;

    protected String username;


    protected long userID;
    protected String cookie;
    protected String web_agent;
    protected String web_agent_mobile;
    protected String Csrf;
    protected String deviceId;
    protected OkHttpClient client;
    protected String mid;
    protected String sesid;

    private final HashMap<String, Cookie> cookieStore = new HashMap<>();

    public Instagram()
    {

    }

    public void setup(String username, String password, long userID, String cookie, String web_agent, String user_agent_mobile)
    {
        this.uuid = InstagramGenericUtil.generateUuid(true);
        this.username = username;
        this.userID = userID;
        this.cookie = extractCookie(cookie);
        this.web_agent = web_agent;
        this.web_agent_mobile = user_agent_mobile;

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
        }).build();

    }

    private String extractCookie(String str)
    {
        this.Csrf = str.substring(str.indexOf("csrftoken=") + 10);
        if (this.Csrf.contains(";"))
        {
            String str2 = this.Csrf;
            this.Csrf = str2.substring(0, str2.indexOf(";"));
        }
        this.mid = str.substring(str.indexOf("mid=") + 4);
        if (this.mid.contains(";"))
        {
            String str3 = this.mid;
            this.mid = str3.substring(0, str3.indexOf(";"));
        }
        this.sesid = str.substring(str.indexOf("sessionid=") + 10);
        if (this.sesid.contains(";"))
        {
            String str4 = this.sesid;
            this.sesid = str4.substring(0, str4.indexOf(";"));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("csrftoken=");
        sb.append(this.Csrf);
        sb.append(";mid=");
        sb.append(this.mid);
        sb.append(";sessionid=");
        sb.append(this.sesid);
        sb.append(";");
        return sb.toString();
    }

    public InstagramLoginResult login() throws IOException
    {
        InstagramLoginResult loginResult = new InstagramLoginResult();
        loginResult.setMessage("");
        loginResult.setStatus("");
//        loginResult.setLogged_in_user();
//        loginResult.setLock();
//        loginResult.setSpam();
        return loginResult;
    }
}
