package com.permoveo.apps.jumboshrimp.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by byfieldj on 4/13/15.
 */
public class Recipe extends BaseItem implements Serializable{


    private List<Ingredient> mIngredients;
    private String mRecipeName;
    private int mServingSize;
    private int mDuration;
    private Photo mPhoto;

    public Recipe(String name, List<Ingredient> ingredients, int servingSize, int duration){
        mRecipeName = name;
        mIngredients = ingredients;
        mServingSize = servingSize;
        mDuration = duration;
    }

    public void setPhoto(Photo photo){
        mPhoto = photo;
    }

    public Photo getPhoto(){
        return mPhoto;
    }
}
