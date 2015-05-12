package com.permoveo.apps.jumboshrimp.providers;

import android.content.Context;
import android.database.CursorIndexOutOfBoundsException;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.toolbox.Volley;
import com.permoveo.apps.jumboshrimp.application.CoreApplication;
import com.permoveo.apps.jumboshrimp.constants.DataSource;
import com.permoveo.apps.jumboshrimp.factory.ParserFactory;
import com.permoveo.apps.jumboshrimp.model.BaseItem;
import com.permoveo.apps.jumboshrimp.model.Recipe;
import com.permoveo.apps.jumboshrimp.parser.BigOvenParser;

/**
 * This class specifies how exactly to interact with the BigOven recipe API
 * It details BigOvens API Keys, endpoints, rate limits, pagination info and
 * any and all other info about that specific data source should go here.
 */


//DataSourceProvider for interacting with the BigOven api
public class BigOvenDataSourceProvider extends DataSourceProvider {


    private static final String API_KEY = "dvx21GevXUl0UVtPVjgzs05tT9cfcd0Z";
    private static int API_CALLS_MADE = 0;
    private Context mContext;

    //Important to distinguish recipe/recipes here
    private String RECIPES_ENDPOINT = "http://api.bigoven.com/recipes?";
    private String RECIPE_ENDPOINT = "http://api.bigoven.com/recipe/%s?";

    private JSONObject mResult;
    private List<String> mUrlParams;
    public static final int RESULTS_PER_PAGE = 24;
    private int CURRENT_PAGE = 1;
    private boolean mSearchByTitle;
    private Recipe mRecipe;
    private List<Recipe> mRecipesList;


    public BigOvenDataSourceProvider(Context context) {
        mContext = context;
        setDataSource(DataSource.BigOven);
        setApiKey(API_KEY);

    }

    public void getRecipe(int recipeID) {
        String url = new StringBuilder(String.format(RECIPE_ENDPOINT, recipeID))
                .append("api_key=" + API_KEY).toString();

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                parseObjectToRecipe(response);
                Log.d(TAG, "onResponse: Response -> " + response.toString());
                mListener.onApiRequestSuccess();
            }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "onErrorResponse: " + error.getMessage());
                mListener.onApiRequestError();
            }


        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = super.getHeaders();
                if (params == null || params.equals(Collections.emptyMap())) {
                    params = new HashMap<String, String>();
                }
                params.put("Accept", "application/json");

                return params;
            }
        };

        //Add the request to our RequestQueue
        CoreApplication.getInstance().addToRequestQueue(objectRequest);
    }

    public void searchForRecipesList(String searchTerm, boolean byTitle) {

        mSearchByTitle = byTitle;


        if (searchTerm != null && !searchTerm.isEmpty()) {
            constructUrl(searchTerm);
        } else {
            throw new NullPointerException("Search query list must not be empty!");
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, RECIPES_ENDPOINT, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                parseObjectToList(response);
                Log.d(TAG, "onResponse: Response -> " + response.toString());
                mListener.onApiRequestSuccess();
            }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "onErrorResponse: " + error.getMessage());
                mListener.onApiRequestError();
            }


        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = super.getHeaders();
                if (params == null || params.equals(Collections.emptyMap())) {
                    params = new HashMap<String, String>();
                }
                params.put("Accept", "application/json");

                return params;
            }


        };

        //Add the request to our RequestQueue
        CoreApplication.getInstance().addToRequestQueue(objectRequest);


    }


    @Override
    public void constructUrl(ArrayList<String> searchTerms) {


        //TODO: Make reusable
        //via a URLManager class that will execute this same behavior for all DataSourceProviders
        //first, add our static params to the URL
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(RECIPES_ENDPOINT);
        urlBuilder.append("&api_key=" + API_KEY);
        urlBuilder.append("&pg=" + CURRENT_PAGE);
        urlBuilder.append("&rpp=" + RESULTS_PER_PAGE);


        //If this is a search by recipe title, then the "any_kw" param is not needed, but the "title_kw" is
        if (mSearchByTitle) {
            urlBuilder.append("&title_kw=" + searchTerms.get(0));
        } else {

            urlBuilder.append("&any_kw=");
            //Then, iterate our list or search terms the user has entered and append those as well
            for (String term : searchTerms) {
                urlBuilder.append(term + ",");
            }


        }

        RECIPES_ENDPOINT = urlBuilder.toString();
        Log.d(TAG, "constructUrl, Search URL -> " + RECIPES_ENDPOINT);


    }

    @Override
    public void constructUrl(String searchTerm) {


        //TODO: Make reusable
        //via a URLManager class that will execute this same behavior for all DataSourceProviders
        //first, add our static params to the URL
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(RECIPES_ENDPOINT);
        urlBuilder.append("&api_key=" + API_KEY);
        urlBuilder.append("&pg=" + CURRENT_PAGE);
        urlBuilder.append("&rpp=" + RESULTS_PER_PAGE);


        //If this is a search by recipe title, then the "any_kw" param is not needed, but the "title_kw" is
        if (mSearchByTitle) {
            urlBuilder.append("&title_kw=" + searchTerm);
        } else {
            urlBuilder.append("&any_kw=" + searchTerm);
        }

        RECIPES_ENDPOINT = urlBuilder.toString();
        Log.d(TAG, "constructUrl, Search URL -> " + RECIPES_ENDPOINT);


    }


    public void parseObjectToList(JSONObject object) {

        mRecipesList = new ArrayList<Recipe>();
        try {
            JSONArray array = object.getJSONArray(BigOvenParser.JSON_ARRAY_RESULTS);


            for (int i = 0; i < RESULTS_PER_PAGE; i++) {
                JSONObject obj = array.getJSONObject(i);

                BigOvenParser parser = (BigOvenParser) ParserFactory.getParser(getDataSource());
                Recipe recipe = parser.parseRecipe(obj);
                mRecipesList.add(recipe);

                Log.d(TAG, "Recipes list size -> " + mRecipesList.size());
                Log.d(TAG, "Recipe name -> " + mRecipesList.get(i).getRecipeName());
                Log.d(TAG, "Recipe photo url -> " + mRecipesList.get(i).getRecipePhotoUrl());


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void parseObjectToRecipe(JSONObject object) {

//        try {

        BigOvenParser parser = (BigOvenParser) ParserFactory.getParser(getDataSource());
        mRecipe = parser.parseRecipe(object);

//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }


    public List<Recipe> getRecipes() {

        return mRecipesList;
    }

    public Recipe getRecipe() {
        return mRecipe;
    }

    public void setCurrentPage(int currentPage) {
        CURRENT_PAGE = currentPage;
    }
}
