package com.permoveo.apps.jumboshrimp.providers;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.permoveo.apps.jumboshrimp.application.CoreApplication;
import com.permoveo.apps.jumboshrimp.constants.DataSource;
import com.permoveo.apps.jumboshrimp.model.BaseItem;
import com.permoveo.apps.jumboshrimp.model.Recipe;

/**
 * Created by byfieldj on 4/14/15.
 */


//DataSourceProvider for interacting with the BigOven api
public class BigOvenDataSourceProvider extends DataSourceProvider {


    private static final String API_KEY = "dvx21GevXUl0UVtPVjgzs05tT9cfcd0Z";
    private static int API_CALLS_MADE = 0;
    private Context mContext;
    private String RECIPE_ENDPOINT = "http://api.bigoven.com/recipes?" + API_KEY;
    private JSONObject mResult;
    private List<String> mUrlParams;
    private static final int RESULTS_PER_PAGE = 25;
    private int CURRENT_PAGE = 1;
    private boolean mSearchByTitle;
    private Recipe mRecipe;


    public BigOvenDataSourceProvider(Context context) {
        mContext = context;
        setDataSource(DataSource.BigOven);
        setApiKey(API_KEY);

    }

    @Override
    public JSONObject loadDataFromNetwork() {
        return null;
    }

    //TODO: Take JSON that was returned from recipe search and construct a Recipe object out of it
    @Override
    public BaseItem parseObject(JSONObject object) {

        Recipe  recipeItem = null;


        return recipeItem;
    }


    //TODO Refactor to return a list of Recipe objects instead of JsonObject
    public Recipe searchForRecipe(List<String> searchTerms, boolean byTitle) {

        mUrlParams = searchTerms;
        mSearchByTitle = byTitle;

        if (mUrlParams != null && !mUrlParams.isEmpty()) {
            constructUrl(mUrlParams);
        } else {
            throw new NullPointerException("Search query list must not be empty!");
        }


        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, RECIPE_ENDPOINT, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                mRecipe = (Recipe)parseObject(response);
                Log.d(TAG, "onResponse: Response -> " + response.toString());
            }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "onErrorResponse: " + error.getMessage());
            }


        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Accept", "application/json");

                return params;
            }

        };

             CoreApplication.getInstance().addToRequestQueue(objectRequest);


        return mRecipe;
    }


    @Override
    public void constructUrl(List<String> searchTerms) {


        //TODO: Make reusable
        //first, add our static params to the URL
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(RECIPE_ENDPOINT);
        urlBuilder.append("&pg=" + CURRENT_PAGE);
        urlBuilder.append("&rpp" + RESULTS_PER_PAGE);


        //If this is a search by recipe title, then the "any_kw" param is not needed, but the "title_kw" is
        if (mSearchByTitle) {
            urlBuilder.append("&title_kw=" + searchTerms.get(0));
        } else {

            urlBuilder.append("&any_kw=");
            //Then, iterate our list or search terms the user has entered and append those as well
            for (String term : searchTerms) {
                urlBuilder.append("," + term);
            }


        }

        RECIPE_ENDPOINT = urlBuilder.toString();
        Log.d(TAG, "constructUrl, Search URL -> " + RECIPE_ENDPOINT);


    }


}
