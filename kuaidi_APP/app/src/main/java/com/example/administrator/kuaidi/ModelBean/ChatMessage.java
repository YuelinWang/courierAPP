package com.example.administrator.kuaidi.ModelBean;

/**
 * Created by himanshusoni on 06/09/15.
 */
public class ChatMessage {
    private boolean isImage, isMine;
    private String content;
    private String username;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    private String createTime;

    public ChatMessage(String message, boolean mine, boolean image, String username, String createTime) {
        content = message;
        isMine = mine;
        isImage = image;
        this.username = username;
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setIsMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isImage() {
        return isImage;
    }

    public void setIsImage(boolean isImage) {
        this.isImage = isImage;
    }
}
