package com.ariodev.instagram.requests;

import com.ariodev.instagram.requests.payload.InstagramReelsTrayFeedResult;

/**
 * Created by root on 24/09/17.
 */

public class InstagramReelsTrayRequest extends InstagramGetRequest<InstagramReelsTrayFeedResult> {

    @Override
    public String getUrl() {
        return "feed/reels_tray/";
    }

    @Override
    public InstagramReelsTrayFeedResult parseResult(int resultCode, String content) {
        return parseJson(resultCode, content, InstagramReelsTrayFeedResult.class);
    }

}
