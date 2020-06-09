package com.ariodev.instagram.requests.payload;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by root on 08/06/17.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramLoginResult extends StatusResult implements Serializable
{
    private InstagramLoggedUser logged_in_user;
}
