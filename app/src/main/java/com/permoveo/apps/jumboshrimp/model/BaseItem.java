package com.permoveo.apps.jumboshrimp.model;

/**
 * Created by byfieldj on 4/13/15.
 */
public abstract class BaseItem {


    private int mItemId;
    public final String TAG = this.getClass().getSimpleName();


    public void setItemId(int id){
        mItemId = id;
    }

    //Unique identifier for each item
    public int getItemId(){

        return mItemId;
    }
}
