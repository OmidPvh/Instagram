package com.ariodev.instagram.requests.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ariodev.instagram.InstagramConstants;
import com.ariodev.instagram.requests.InstagramPostRequest;
import com.ariodev.instagram.requests.payload.StatusResult;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.SneakyThrows;

/**
 * Created by root on 09/06/17.
 */

@AllArgsConstructor
@Builder
public class InstagramConfigureVideoRequest extends InstagramPostRequest<StatusResult> {

    private String uploadId;
    private String caption;

    private long duration;
    private int width;
    private int height;

    @Override
    public String getUrl() {
        return "media/configure/?video=1";
    }

    @Override
    @SneakyThrows
    public String getPayload() {

        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("upload_id", uploadId);
        likeMap.put("source_type", 3);
        likeMap.put("poster_frame_index", 0);
        likeMap.put("length", 0.0);
        likeMap.put("audio_muted",false);
        likeMap.put("filter_type", 0);
        likeMap.put("video_result", "deprecated");

        Map<String, Object> clips = new LinkedHashMap<>();
        clips.put("length", duration);
        clips.put("source_type", 3);
        clips.put("camera_position", "back");
        likeMap.put("clips", "clips");

        Map<String, Object> extraMap = new LinkedHashMap<>();
        extraMap.put("source_width", width);
        extraMap.put("source_height", height);
        likeMap.put("extra", extraMap);

        Map<String, Object> deviceMap = new LinkedHashMap<>();
        deviceMap.put("manufacturer", InstagramConstants.getDeviceManufacturer());
        deviceMap.put("model", InstagramConstants.getDeviceModel());
        deviceMap.put("android_version", InstagramConstants.getDeviceAndroidVersion());
        deviceMap.put("android_release", InstagramConstants.getDeviceAndroidRelease());
        likeMap.put("device", deviceMap);


        likeMap.put("_csrftoken", api.getOrFetchCsrf(null));
        likeMap.put("_uuid", api.getUuid());
        likeMap.put("_uid", api.getUserID());
        likeMap.put("caption", caption);




        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(likeMap);

        return payloadJson;
    }

    @Override
    @SneakyThrows
    public StatusResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, StatusResult.class);
    }

}
