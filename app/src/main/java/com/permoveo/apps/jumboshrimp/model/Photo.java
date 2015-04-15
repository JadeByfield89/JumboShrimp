package com.permoveo.apps.jumboshrimp.model;

import java.io.Serializable;

/**
 * Created by byfieldj on 4/13/15.
 */
public abstract class Photo implements Serializable {


    public int mPhotoId;
    private TYPE mPhotoType;
    private int mHeight;
    private int mWidth;
    private String mSourceUrl;


    //Each photo can be of type recipe, ingredient, user, or misc
    public static enum TYPE {

        RECIPE, INGREDIENT, USER, MISC;
    }


    public void setType(TYPE t) {
        mPhotoType = t;
    }

    public TYPE getType() {
        return mPhotoType;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setSourceUrl(String url){
        mSourceUrl = url;
    }

    public String getUrl(){
        return mSourceUrl;
    }

}
