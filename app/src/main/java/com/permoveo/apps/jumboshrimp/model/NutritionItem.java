package com.permoveo.apps.jumboshrimp.model;

/**
 * Created by byfieldj on 4/13/15.
 */
public class NutritionItem extends BaseItem {


    private String mDisplayValue;
    private Nutrition.TYPE mType;

    public void setType(Nutrition.TYPE t){
        mType = t;
    }

    public Nutrition.TYPE getType(){
        return mType;
    }
}
