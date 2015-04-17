package com.permoveo.apps.jumboshrimp.providers;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.VolleyError;
import com.permoveo.apps.jumboshrimp.constants.DataSource;
import com.permoveo.apps.jumboshrimp.listeners.OnApiRequestCompletedListener;
import com.permoveo.apps.jumboshrimp.model.BaseItem;


/**
 * Created by byfieldj on 4/14/15.
 */


/*The idea behind DataSourceProvider
is that it acts as a base for all sources of data

There shouldn't be any need to change implementation logic if a data source for recipes changes or a new one gets added;

For example, if we start with the Yummly API for getting recipe data, and at some later time we decide to
add RecipePal as an additional provider, all we have to do is create a child class such as RecipePalDataProvider, and provide its API specific
logic there(api keys, secret, endpoint definitions, etc)
 */

public abstract class DataSourceProvider {

    protected String TAG = getClass().getSimpleName();
    private static String API_KEY;
    private static String API_SECRET = "";
    private static String BASE_ENDPOINT = "";
    private DataSource mDataSource;
    private int API_CALL_LIMIT;
    public List<String> mStringSearchTerms;
    public List<Integer> mIntegerSearchTerms;
    public OnApiRequestCompletedListener mListener;


    public void setListener(OnApiRequestCompletedListener listener) {
        mListener = listener;
    }

    public void setApiKey(String key) {
        API_KEY = key;
    }

    public String getApiKey() {
        return API_KEY;
    }


    public void setDataSource(DataSource source) {
        mDataSource = source;
    }

    public DataSource getDataSource() {
        return mDataSource;
    }


    public abstract void constructUrl(ArrayList<String> urlParams);

    public void setApiCallLimit(int limit) {
        API_CALL_LIMIT = limit;
    }

    public int getApiCallLimit() {
        return API_CALL_LIMIT;
    }


}
