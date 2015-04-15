package com.permoveo.apps.jumboshrimp.model;

import com.permoveo.apps.jumboshrimp.constants.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by byfieldj on 4/13/15.
 */
public abstract class BaseItem {


    private int mItemId;
    public final String TAG = this.getClass().getSimpleName();
    private DataSource mDataSource;


    public void setItemId(int id){
        mItemId = id;
    }

    //Unique identifier for each item
    public int getItemId(){

        return mItemId;
    }

    public void setDataSource(DataSource source){
        mDataSource = source;
    }
    
    public DataSource getDataSource(){
        return mDataSource;
    }


}
