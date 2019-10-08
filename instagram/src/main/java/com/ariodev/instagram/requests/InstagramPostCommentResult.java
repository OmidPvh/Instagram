package com.ariodev.instagram.requests;


import com.ariodev.instagram.requests.payload.InstagramComment;
import com.ariodev.instagram.requests.payload.InstagramUser;
import com.ariodev.instagram.requests.payload.StatusResult;

import java.util.ArrayList;


public class InstagramPostCommentResult extends StatusResult
{

    private InstagramComment comment;
    private ArrayList<InstagramUser> users;

}
