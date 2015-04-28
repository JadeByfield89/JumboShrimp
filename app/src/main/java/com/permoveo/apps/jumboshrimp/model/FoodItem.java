package com.permoveo.apps.jumboshrimp.model;

/**
 * Created by byfieldj on 4/27/15.
 */
public class FoodItem extends BaseItem {

    private String mName;
    private String mDescription;
    private String mType;
    private Nutrition mNutrition;
    private Photo mPhoto;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    public Nutrition getNutrition() {
        return mNutrition;
    }

    public void setNutrition(Nutrition mNutrition) {
        this.mNutrition = mNutrition;
    }

    public Photo getPhoto() {
        return mPhoto;
    }

    public void setPhoto(Photo mPhoto) {
        this.mPhoto = mPhoto;
    }




}
