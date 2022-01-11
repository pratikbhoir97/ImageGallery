package com.example.galleryapplication;

public class ImageModel {
    String thumbUrl;
    String imageUrl;
    public ImageModel(String thumbUrl,String imageUrl){
        this.thumbUrl = thumbUrl;
        this.imageUrl = imageUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
