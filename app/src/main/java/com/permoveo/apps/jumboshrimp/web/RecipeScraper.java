package com.permoveo.apps.jumboshrimp.web;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.permoveo.apps.jumboshrimp.listeners.OnApiRequestCompletedListener;
import com.permoveo.apps.jumboshrimp.model.Recipe;
import com.permoveo.apps.jumboshrimp.web.task.RecipeScraperTask;

/**
 * Created by byfieldj on 5/12/15.
 */

//This class is responsible for parsing a websites HTML using Jsoup, and return a robust
//Recipe object with all our necessary data
public class RecipeScraper {

    private String mSourceUrl;
    private Context mContext;
    private FragmentActivity mFragmentActivity;

    private OnApiRequestCompletedListener mListener;

    public void setListener(OnApiRequestCompletedListener listener) {
        mListener = listener;
    }

    public RecipeScraper(String url){

        mSourceUrl = url;

    }


    public void parseHtmlToRecipe(){
        RecipeScraperTask task = new RecipeScraperTask();
        task.setListener(mListener);
        task.execute(mSourceUrl);
    }


}
