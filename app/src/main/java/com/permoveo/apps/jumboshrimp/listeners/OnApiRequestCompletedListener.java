package com.permoveo.apps.jumboshrimp.listeners;

import com.permoveo.apps.jumboshrimp.model.Recipe;

/**
 * Created by byfieldj on 4/15/15.
 */
public interface OnApiRequestCompletedListener {


    public abstract void onApiRequestSuccess(Recipe recipe);
    public abstract void onApiRequestError();





}



