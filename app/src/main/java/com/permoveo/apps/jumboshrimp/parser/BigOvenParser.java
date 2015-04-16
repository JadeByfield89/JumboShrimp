package com.permoveo.apps.jumboshrimp.parser;

import android.util.Log;

import com.permoveo.apps.jumboshrimp.model.Ingredient;
import com.permoveo.apps.jumboshrimp.model.Photo;
import com.permoveo.apps.jumboshrimp.model.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by byfieldj on 4/14/15.
 */

//
public class BigOvenParser implements  Parser {


    public static final String JSON_ARRAY_RESULTS = "Results";
    public static final String JSON_OBJECT_TITLE  = "Title";
    public static final String JSON_OBJECT_IMAGE_URL = "ImageURL120";
    private static final String TAG = "BigOvenParser";

    @Override
    public Recipe parseRecipe(JSONObject object){
        Recipe recipe = null;


               try {
                   String recipeTitle = object.getString(JSON_OBJECT_TITLE);
                   String recipeImageUrl = object.getString(JSON_OBJECT_IMAGE_URL);


                   recipe = new Recipe(recipeTitle, recipeImageUrl);
               }catch(JSONException e){
                   e.printStackTrace();
               }




        return recipe;
    }

    @Override
    public Ingredient parseIngredient(JSONObject object) {
        return null;
    }

    @Override
    public Photo parsePhoto(JSONObject object) {
        return null;
    }


    //parseIngreditent





}
