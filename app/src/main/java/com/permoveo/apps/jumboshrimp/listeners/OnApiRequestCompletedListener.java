package com.permoveo.apps.jumboshrimp.listeners;

/**
 * Created by byfieldj on 4/15/15.
 */
public interface OnApiRequestCompletedListener {


    public abstract void onApiRequestSuccess();
    public abstract void onApiRequestError();


    //Once the api request has completed
    //We need to ensure that we are saving

    //they keys in the appropriate columns, or they will get lost in translation

}



