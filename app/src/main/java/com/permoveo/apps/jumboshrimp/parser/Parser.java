package com.permoveo.apps.jumboshrimp.parser;

import com.permoveo.apps.jumboshrimp.model.GroceryItem;
import com.permoveo.apps.jumboshrimp.model.Ingredient;
import com.permoveo.apps.jumboshrimp.model.Photo;
import com.permoveo.apps.jumboshrimp.model.Recipe;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by byfieldj on 4/14/15.
 */
public interface Parser {


    public Recipe parseRecipe(JSONObject object);

    public Ingredient parseIngredient(JSONObject object);

    public Photo parsePhoto(JSONObject object);

}
