package com.permoveo.apps.jumboshrimp.model;

import java.io.Serializable;

/**
 * Created by byfieldj on 4/13/15.
 */
public class Ingredient extends BaseItem implements Serializable{

    private Recipe mRecipe;
    private Photo mPhoto;

    //TODO - Add support for ingredient allergies, nutrition


    public Ingredient(Recipe recipe){
        mRecipe = recipe;
    }

    public void setPhoto(Photo photo){
        mPhoto = photo;
    }

    public Photo getPhoto(){
        return mPhoto;
    }
}
