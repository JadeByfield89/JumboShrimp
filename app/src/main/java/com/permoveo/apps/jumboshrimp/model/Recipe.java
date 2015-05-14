package com.permoveo.apps.jumboshrimp.model;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by byfieldj on 4/13/15.
 */
public class Recipe extends BaseItem implements Serializable{

    private int mRecipeID;
    private List<Ingredient> mIngredients;
    private String mRecipeName;
    private String mPhotoUrl;
    private int mServingSize;
    private int mDuration;
    private Photo mPhoto;
    private String mDescription;
    private String mInstructions;

    public Recipe() {
        mRecipeID = 0;
        mRecipeName = "";
        mPhotoUrl = "";
        mDescription = "";
        mInstructions = "";
        mIngredients = new ArrayList<Ingredient>();
    }

    public Recipe(int recipeID, String name, String photoUrl){
        mRecipeID = recipeID;
        mRecipeName = name;
        mPhotoUrl = photoUrl;


    }

    public Recipe(String name, List<Ingredient> ingredients, int servingSize, int duration){
        mRecipeName = name;
        mIngredients = ingredients;
        mServingSize = servingSize;
        mDuration = duration;


    }

    public void setRecipeID(int recipeID) {
        mRecipeID = recipeID;
    }

    public int getRecipeID() {
        return mRecipeID;
    }

    public void setRecipeName(String name){
        mRecipeName = name;
    }

    public String getRecipeName(){
        return mRecipeName;
    }

    public void setRecipePhotoUrl(String url){
        mPhotoUrl = url;
    }

    public String getRecipePhotoUrl(){
        return mPhotoUrl;
    }

    public void setPhoto(Photo photo){
        mPhoto = photo;
    }

    public Photo getPhoto(){
        return mPhoto;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.mIngredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return mIngredients;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setInstructions(String instructions) {
        mInstructions = instructions;
    }

    public String getInstructions() {
        return mInstructions;
    }

}
