package com.permoveo.apps.jumboshrimp.model;

import java.io.Serializable;

/**
 * Created by byfieldj on 4/13/15.
 */
public class Ingredient extends BaseItem implements Serializable{

    private Recipe mRecipe;
    private Photo mPhoto;

    //TODO - Add support for ingredient allergies, nutrition
    private String mName;
    private String mDisplayQuantity;
    private String mPreparationNotes;
    private String mUnit;

    public Ingredient(Recipe recipe){
        mRecipe = recipe;
        mName = "";
        mDisplayQuantity = "";
        mPreparationNotes = "";
        mUnit = "";
    }

    public void setPhoto(Photo photo){
        mPhoto = photo;
    }

    public Photo getPhoto(){
        return mPhoto;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setDisplayQuantity(String displayQuantity) {
        mDisplayQuantity = displayQuantity;
    }

    public String getDisplayQuantity() {
        return mDisplayQuantity;
    }

    public void setPreparationNotes(String preparationNotes) {
        mPreparationNotes = preparationNotes;
    }

    public String getPreparationNotes() {
        return mPreparationNotes;
    }

    public void setUnit(String unit) {
        mUnit = unit;
    }

    public String getUnit() {
        return mUnit;
    }
}
