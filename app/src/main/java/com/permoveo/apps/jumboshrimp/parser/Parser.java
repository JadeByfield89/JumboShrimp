package com.permoveo.apps.jumboshrimp.parser;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by byfieldj on 4/14/15.
 */
public interface Parser {


    public abstract void construct(JSONObject obj);

    public abstract void construct(JSONArray array);
}
