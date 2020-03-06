//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ariodev.instagram.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class StatusResult
{
    private String status;
    private String message;
    private boolean spam;
    private boolean lock;
    private String feedback_title;
    private String feedback_message;
    private String error_type;
    private String checkpoint_url;
}
