package com.permoveo.apps.jumboshrimp.providers;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.permoveo.apps.jumboshrimp.application.CoreApplication;
import com.permoveo.apps.jumboshrimp.model.FoodItem;
import com.permoveo.apps.jumboshrimp.model.Ingredient;
import com.permoveo.apps.jumboshrimp.model.Recipe;
import com.permoveo.apps.jumboshrimp.oauth.OAuthUrlSigner;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import oauth.signpost.exception.OAuthCommunicationException;

/**
 * Created by byfieldj on 4/27/15.
 * <p/>
 * This class provides details for interacting specifically with the FatSecret API
 */


public class FatSecretDataSourceProvider extends DataSourceProvider {

    private static final String API_KEY = "c9e35b4202cf4907b12d254e9a31c795";
    //private static final String SHARED_SECRET = "80191e92635e43038263f2c7137d725b";
    private String ENDPOINT_FOOD_SEARCH = "http://platform.fatsecret.com/rest/server.api?method=foods.search&format=json";
    private static final String CONSUMER_KEY = API_KEY;
    private static final String CONSUMER_SECRET = "80191e92635e43038263f2c7137d725b";
    private List<FoodItem> mFoodItems;
    private Context mContext;

    @Override
    public void constructUrl(ArrayList<String> urlParams) {

    }

    @Override
    public void constructUrl(String urlParam) {

    }


    public FatSecretDataSourceProvider(Context context) {
        mContext = context;
    }


    public void searchForFoodByTitle(String foodName) {
        mFoodItems = new ArrayList<FoodItem>();

        //Construct the url and sign with the HMAC-SHA1 signature
        //TODO: test
        ArrayList<String> params = new ArrayList<String>();
        params.add(foodName);

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(ENDPOINT_FOOD_SEARCH);
        urlBuilder.append("&search_expression=" + params.get(0));

        try {
            OAuthUrlSigner signer = new OAuthUrlSigner(urlBuilder.toString(), CONSUMER_KEY, CONSUMER_SECRET);


            ENDPOINT_FOOD_SEARCH = signer.sign();
            Log.d(TAG, "Executing food search with url: " + ENDPOINT_FOOD_SEARCH);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "Error performing ingredient search!");
        }


        //Construct and execute the json object request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, ENDPOINT_FOOD_SEARCH, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG, "Food Search Response: -> " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        CoreApplication.getInstance().addToRequestQueue(request);


    }
}
