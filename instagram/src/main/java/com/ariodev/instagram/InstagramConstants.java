package com.ariodev.instagram;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by root on 08/06/17.
 */

public class InstagramConstants
{

    public static final String API_URL = "https://i.instagram.com/api/v1/";

    public static final String API_KEY = "4f8732eb9ba7d1c8e8897a75d6474d4eb3f5279137431b2aafb71fafe2abe178";
    //public static final String API_KEY = "8b138ac7f686560c64fa4c04b884a0509c4311cccaa209353799c888c6995572";
    public static final String API_KEY_VERSION = "4";

    public static final String INSTAGRAM_API_SETTINGS = "instagram_api_settings";
    public static final String INSTAGRAM_RANDOM_KEY = "instagram_random_key";

    public static final String DEVICE_MANUFACTURER = "samsung";

    public static final String DEVICE_MODEL = "SM-G935F";

    public static final String DEVICE_ANDROID_VERSION = "23";


    public static final String DEVICE_ANDROID_RELEASE = "6.0.1";

    public static final String DEVICE_EXPERIMENTS = "ig_android_progressive_jpeg,ig_creation_growth_holdout,ig_android_report_and_hide,ig_android_new_browser,ig_android_enable_share_to_whatsapp,ig_android_direct_drawing_in_quick_cam_universe,ig_android_huawei_app_badging,ig_android_universe_video_production,ig_android_asus_app_badging,ig_android_direct_plus_button,ig_android_ads_heatmap_overlay_universe,ig_android_http_stack_experiment_2016,ig_android_infinite_scrolling,ig_fbns_blocked,ig_android_white_out_universe,ig_android_full_people_card_in_user_list,ig_android_post_auto_retry_v7_21,ig_fbns_push,ig_android_feed_pill,ig_android_profile_link_iab,ig_explore_v3_us_holdout,ig_android_histogram_reporter,ig_android_anrwatchdog,ig_android_search_client_matching,ig_android_high_res_upload_2,ig_android_new_browser_pre_kitkat,ig_android_2fac,ig_android_grid_video_icon,ig_android_white_camera_universe,ig_android_disable_chroma_subsampling,ig_android_share_spinner,ig_android_explore_people_feed_icon,ig_explore_v3_android_universe,ig_android_media_favorites,ig_android_nux_holdout,ig_android_search_null_state,ig_android_react_native_notification_setting,ig_android_ads_indicator_change_universe,ig_android_video_loading_behavior,ig_android_black_camera_tab,liger_instagram_android_univ,ig_explore_v3_internal,ig_android_direct_emoji_picker,ig_android_prefetch_explore_delay_time,ig_android_business_insights_qe,ig_android_direct_media_size,ig_android_enable_client_share,ig_android_promoted_posts,ig_android_app_badging_holdout,ig_android_ads_cta_universe,ig_android_mini_inbox_2,ig_android_feed_reshare_button_nux,ig_android_boomerang_feed_attribution,ig_android_fbinvite_qe,ig_fbns_shared,ig_android_direct_full_width_media,ig_android_hscroll_profile_chaining,ig_android_feed_unit_footer,ig_android_media_tighten_space,ig_android_private_follow_request,ig_android_inline_gallery_backoff_hours_universe,ig_android_direct_thread_ui_rewrite,ig_android_rendering_controls,ig_android_ads_full_width_cta_universe,ig_video_max_duration_qe_preuniverse,ig_android_prefetch_explore_expire_time,ig_timestamp_public_test,ig_android_profile,ig_android_dv2_consistent_http_realtime_response,ig_android_enable_share_to_messenger,ig_explore_v3,ig_ranking_following,ig_android_pending_request_search_bar,ig_android_feed_ufi_redesign,ig_android_video_pause_logging_fix,ig_android_default_folder_to_camera,ig_android_video_stitching_7_23,ig_android_profanity_filter,ig_android_business_profile_qe,ig_android_search,ig_android_boomerang_entry,ig_android_inline_gallery_universe,ig_android_ads_overlay_design_universe,ig_android_options_app_invite,ig_android_view_count_decouple_likes_universe,ig_android_periodic_analytics_upload_v2,ig_android_feed_unit_hscroll_auto_advance,ig_peek_profile_photo_universe,ig_android_ads_holdout_universe,ig_android_prefetch_explore,ig_android_direct_bubble_icon,ig_video_use_sve_universe,ig_android_inline_gallery_no_backoff_on_launch_universe,ig_android_image_cache_multi_queue,ig_android_camera_nux,ig_android_immersive_viewer,ig_android_dense_feed_unit_cards,ig_android_sqlite_dev,ig_android_exoplayer,ig_android_add_to_last_post,ig_android_direct_public_threads,ig_android_prefetch_venue_in_composer,ig_android_bigger_share_button,ig_android_dv2_realtime_private_share,ig_android_non_square_first,ig_android_video_interleaved_v2,ig_android_follow_search_bar,ig_android_last_edits,ig_android_video_download_logging,ig_android_ads_loop_count_universe,ig_android_swipeable_filters_blacklist,ig_android_boomerang_layout_white_out_universe,ig_android_ads_carousel_multi_row_universe,ig_android_mentions_invite_v2,ig_android_direct_mention_qe,ig_android_following_follower_social_context";

    public static final String USER_AGENT = String.format("Instagram 10.26.0 Android (%s/%s; 640dpi; 1440x2560; %s; %s; hero2lte; samsungexynos8890; en_US)", DEVICE_ANDROID_VERSION, DEVICE_ANDROID_RELEASE, DEVICE_MANUFACTURER, DEVICE_MODEL);

    public static String getUserAgent()
    {

        Display defaultDisplay = ((WindowManager) Instagram.context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);

        int densityDpi = Instagram.context.getResources()
                                          .getDisplayMetrics().densityDpi;

        int heightPixels = displayMetrics.heightPixels;
        int widthPixels = displayMetrics.widthPixels;
        String strModel = getDeviceModel();
        String strManufacturer = Build.MANUFACTURER;
        String strDevice = Build.DEVICE;
        String strBoard = Build.BOARD;
        int sdk_int = Build.VERSION.SDK_INT;
        String release = Build.VERSION.RELEASE;

        String realUserAgent = String.format("Instagram 10.15.0 Android (%s/%s; %sdpi; %sx%s; %s; %s; %s; %s; en_US)", sdk_int, release, densityDpi, widthPixels, heightPixels, strManufacturer, strModel, strDevice, strBoard);


        if (strModel == null || strModel.isEmpty() || strManufacturer == null || strManufacturer.isEmpty() || Build.VERSION.RELEASE == null || strDevice.isEmpty() || strBoard == null || strBoard.isEmpty() || realUserAgent.contains("unknown"))
        {
            return USER_AGENT;
        }
        else
        {
            return realUserAgent;
        }
    }

    public static boolean getBooleanUserAgent()
    {
        Display defaultDisplay = ((Activity) Instagram.context).getWindowManager()
                                                               .getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int densityDpi = Instagram.context.getResources()
                                          .getDisplayMetrics().densityDpi;
        int heightPixels = displayMetrics.heightPixels;
        int widthPixels = displayMetrics.widthPixels;

        String strModel = getDeviceModel();

        String strManufacturer = Build.MANUFACTURER;
        String strDevice = Build.DEVICE;
        String strBoard = Build.BOARD;
        int sdk_int = Build.VERSION.SDK_INT;
        String release = Build.VERSION.RELEASE;
        String strUserAgent = String.format("Instagram 10.15.0 Android (%s/%s; %sdpi; %sx%s; %s; %s; %s; %s; en_US)", sdk_int, release, densityDpi, widthPixels, heightPixels, strManufacturer, strModel, strDevice, strBoard);

        if (strModel == null || strModel.isEmpty() || strManufacturer == null || strManufacturer.isEmpty() || Build.VERSION.RELEASE == null || strDevice.isEmpty() || strBoard == null || strBoard.isEmpty() || strUserAgent.contains("unknown"))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public static String getDeviceManufacturer()
    {
        if (getBooleanUserAgent())
        {

            return Build.MANUFACTURER;

        }
        else
        {
            return DEVICE_MANUFACTURER;
        }
    }

    public static String getDeviceModel()
    {
        SharedPreferences sharedPreferences = Instagram.context.getSharedPreferences(INSTAGRAM_API_SETTINGS, Context.MODE_PRIVATE);
        String random_key = sharedPreferences.getString(INSTAGRAM_RANDOM_KEY, null);

        if (getBooleanUserAgent())
        {
            if (random_key != null)
            {
                return Build.MODEL + random_key;
            }
            else
            {
                return Build.MODEL;
            }
        }
        else
        {
            if (random_key != null)
            {

                return DEVICE_MODEL + random_key;
            }
            else
            {
                return DEVICE_MODEL;
            }

        }
    }


    public static String getDeviceAndroidVersion()
    {
        if (getBooleanUserAgent())
        {
            return String.valueOf(Build.VERSION.SDK_INT);
        }
        else
        {
            return DEVICE_ANDROID_VERSION;
        }
    }

    public static String getDeviceAndroidRelease()
    {
        if (getBooleanUserAgent())
        {
            return Build.VERSION.RELEASE;
        }
        else
        {
            return DEVICE_ANDROID_RELEASE;
        }
    }
}
