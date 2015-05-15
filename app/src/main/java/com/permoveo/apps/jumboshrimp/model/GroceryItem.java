package com.permoveo.apps.jumboshrimp.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by byfieldj on 4/13/15.
 */

//Grocery items can be an Ingredient or just a regular item bought at the grocery store
public class GroceryItem extends BaseItem implements Serializable {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String mItemName;

    @DatabaseField
    private int mQuantity;

    @DatabaseField
    private Date mDateCreated;

    @DatabaseField
    private Date mDateModified;

    @DatabaseField
    private String mUPCCode;

    @DatabaseField
    private String mImageUrl;

    @DatabaseField
    private double mPrice;

    private Photo mPhoto;
    private Recipe mRecipe;

    public GroceryItem(String name) {
        mItemName = name;
    }

    public GroceryItem(){

    }


    public void setQuantity(int quant) {
        mQuantity = quant;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setDateCreated(Date date) {
        mDateCreated = date;
    }

    public Date getDateCreated() {
        return mDateCreated;
    }

    public void setDateModified(Date date) {
        mDateModified = date;
    }

    public Date getDateModified() {
        return mDateModified;
    }

    public void setUPCCode(String upc){
        mUPCCode = upc;
    }

    public String getUPCCode(){
        return mUPCCode;
    }

    public void setImageUrl(String url){
        mImageUrl = url;
    }

    public String getImageUrl(){
        return mImageUrl;
    }

    public void setPrice(double price){
        mPrice = price;
    }

    public double getPrice(){
        return mPrice;
    }

    public void setRecipe(Recipe recipe) {
        mRecipe = recipe;
    }

    public Recipe getRecipe() {
        return mRecipe;
    }

    public void setPhoto(Photo photo) {
        mPhoto = photo;
    }

    public Photo getPhoto() {
        return mPhoto;
    }
}
