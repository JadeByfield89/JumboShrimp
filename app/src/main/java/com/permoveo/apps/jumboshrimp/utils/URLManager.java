package com.permoveo.apps.jumboshrimp.utils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by byfieldj on 4/15/15.
 */
public class URLManager {

    private String mBaseUrl;
    private StringBuilder mStringBuilder;


    public URLManager(){
        mStringBuilder  = new StringBuilder();
    }

    public void setEndpont(String base){
        mBaseUrl = base;
        mStringBuilder.append(base);
    }

    public void appendParams(HashMap<String, String> params){

      //TODO: Append name and value pairs from hashmap to StringBuilder
    }
}
