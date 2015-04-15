package com.permoveo.apps.jumboshrimp.model;

import java.io.Serializable;

/**
 * Created by byfieldj on 4/13/15.
 */

//Grocery items can be an Ingredient or just a regular item bought at the grocery store
public class GroceryItem extends BaseItem implements Serializable {


    private String mItemName;
    private Photo mPhoto;
    private Recipe mRecipe;
    private boolean isScannable;
    private boolean hasRecipe;

    public GroceryItem(String name, boolean hasRecipe){
        mItemName = name;
        this.hasRecipe = hasRecipe;
    }

    public void setRecipe(Recipe recipe){
        mRecipe = recipe;
    }

    public Recipe getRecipe(){
        return mRecipe;
    }

    public void setPhoto(Photo photo){
        mPhoto = photo;
    }

    public Photo getPhoto(){
        return mPhoto;
    }
}
