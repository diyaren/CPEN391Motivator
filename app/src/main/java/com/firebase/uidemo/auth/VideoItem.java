package com.firebase.uidemo.auth;

/**
 * Created by Navjashan on 05/04/2017.
 */

public class VideoItem {

    private String title;
    private String description;
    private String thumbnailURL;
    private String id;
    private String channelTitle;
    private String channelID;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannelTitle(){
        return channelTitle ;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }
}