package com.permoveo.apps.jumboshrimp.parser;


import com.permoveo.apps.jumboshrimp.model.Ingredient;
import com.permoveo.apps.jumboshrimp.model.Photo;
import com.permoveo.apps.jumboshrimp.model.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by byfieldj on 4/14/15.
 */

//
public class BigOvenParser implements  Parser {


    public static final String JSON_ARRAY_RESULTS = "Results";
    public static final String JSON_OBJECT_TITLE  = "Title";
    public static final String JSON_OBJECT_IMAGE_URL_120 = "ImageURL120";
    public static final String JSON_OBJECT_IMAGE_URL = "ImageURL";
    public static final String JSON_OBJECT_RECIPE_ID = "RecipeID";
    public static final String JSON_OBJECT_DESCRIPTION = "Description";
    public static final String JSON_OBJECT_INSTRUCTIONS = "Instructions";
    public static final String JSON_OBJECT_INGREDIENTS = "Ingredients";
    public static final String JSON_OBJECT_NAME = "Name";
    public static final String JSON_OBJECT_DISPLAY_QUANTITY = "DisplayQuantity";
    public static final String JSON_OBJECT_PREPARATION_NOTES = "PreparationNotes";
    private static final String TAG = "BigOvenParser";

    @Override
    public Recipe parseRecipe(JSONObject object){
        Recipe recipe = null;


               try {
                   String recipeTitle = object.getString(JSON_OBJECT_TITLE);
                   String recipeImageUrl = object.getString(JSON_OBJECT_IMAGE_URL);
                   if (object.has(JSON_OBJECT_IMAGE_URL_120)) {
                       recipeImageUrl = object.getString(JSON_OBJECT_IMAGE_URL_120);
                   }
                   int recipeID = object.getInt(JSON_OBJECT_RECIPE_ID);

                   recipe = new Recipe(recipeID, recipeTitle, recipeImageUrl);

                   // get description
                   String description = "";
                   if (object.has(JSON_OBJECT_DESCRIPTION)) {
                       description = object.getString(JSON_OBJECT_DESCRIPTION);
                   }
                   recipe.setDescription(description);

                   String instructions = "";
                   if (object.has(JSON_OBJECT_INSTRUCTIONS)) {
                       instructions = object.getString(JSON_OBJECT_INSTRUCTIONS);
                   }
                   recipe.setInstructions(instructions);

                   ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
                   if (object.has(JSON_OBJECT_INGREDIENTS)) {
                       JSONArray jsonIngredients= object.getJSONArray(JSON_OBJECT_INGREDIENTS);
                       for (int i = 0; i < jsonIngredients.length(); i++) {
                           JSONObject jsonIngredient = jsonIngredients.getJSONObject(i);
                           Ingredient ingredient = parseIngredient(jsonIngredient);
                           ingredients.add(ingredient);
                       }
                   }
                   recipe.setIngredients(ingredients);

               }catch(JSONException e){
                   e.printStackTrace();
               }

        return recipe;
    }

    @Override
    public Ingredient parseIngredient(JSONObject object) {
        Ingredient ingredient = null;


        try {
            String name = object.getString(JSON_OBJECT_NAME);
            String displayQuantity = "";
            if (object.has(JSON_OBJECT_DISPLAY_QUANTITY)) {
                displayQuantity = object.getString(JSON_OBJECT_DISPLAY_QUANTITY);
            }
            String preparationNotes = "";
            if (object.has(JSON_OBJECT_PREPARATION_NOTES)) {
                preparationNotes = object.getString(JSON_OBJECT_PREPARATION_NOTES);
                if (preparationNotes.equals("null")) {
                    preparationNotes = "";
                }
            }

            ingredient = new Ingredient(null);
            ingredient.setName(name);
            ingredient.setDisplayQuantity(displayQuantity);
            ingredient.setPreparationNotes(preparationNotes);

        }catch(JSONException e){
            e.printStackTrace();
        }

        return ingredient;
    }

    @Override
    public Photo parsePhoto(JSONObject object) {
        return null;
    }


    //parseIngreditent





}
