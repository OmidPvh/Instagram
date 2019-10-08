//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ariodev.instagram.requests.payload;


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


    public String getStatus()
    {
        return this.status;
    }

    public String getMessage()
    {
        return this.message;
    }

    public boolean isSpam()
    {
        return this.spam;
    }

    public boolean isLock()
    {
        return this.lock;
    }

    public String getFeedback_title()
    {
        return this.feedback_title;
    }

    public String getFeedback_message()
    {
        return this.feedback_message;
    }

    public String getError_type()
    {
        return this.error_type;
    }

    public String getCheckpoint_url()
    {
        return this.checkpoint_url;
    }

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

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setSpam(boolean spam)
    {
        this.spam = spam;
    }

    public void setLock(boolean lock)
    {
        this.lock = lock;
    }

    public void setFeedback_title(String feedback_title)
    {
        this.feedback_title = feedback_title;
    }

    public void setFeedback_message(String feedback_message)
    {
        this.feedback_message = feedback_message;
    }

    public void setError_type(String error_type)
    {
        this.error_type = error_type;
    }

    public void setCheckpoint_url(String checkpoint_url)
    {
        this.checkpoint_url = checkpoint_url;
    }

    public String toString()
    {
        return "StatusResult(status=" + this.getStatus() + ", message=" + this.getMessage() + ", spam=" + this.isSpam() + ", lock=" + this.isLock() + ", feedback_title=" + this.getFeedback_title() + ", feedback_message=" + this.getFeedback_message() + ", error_type=" + this.getError_type() + ", checkpoint_url=" + this.getCheckpoint_url() + ")";
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
