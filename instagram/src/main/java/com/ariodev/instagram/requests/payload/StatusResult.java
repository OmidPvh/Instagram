//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ariodev.instagram.requests.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public void setStatus(String status)
    {
        if (status == null)
        {
            throw new NullPointerException("status");
        }
        else
        {
            this.status = status;
        }
    }

    public StatusResult(String status)
    {
        if (status == null)
        {
            throw new NullPointerException("status");
        }
        else
        {
            this.status = status;
        }
    }

    public StatusResult()
    {
    }
}
