package com.pacewisdom.module2;

import com.google.gson.annotations.SerializedName;

public class PosModel {

    @SerializedName("title")
    private String title;

    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;

    @SerializedName("albumId")
    private String albumId;

    @SerializedName("id")
    private String id;

    @SerializedName("url")
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
