package com.permoveo.apps.jumboshrimp.model;

import java.util.List;

/**
 * Created by byfieldj on 4/13/15.
 */
public class GroceryList {


    List<GroceryItem> mGroceryItems;


    public GroceryList(List<GroceryItem> items){
        mGroceryItems = items;
    }

    public List<GroceryItem> getItems(){
        return mGroceryItems;
    }

    public void removeItem(GroceryItem item){

        if(!mGroceryItems.isEmpty()){
            mGroceryItems.remove(item);
        }
    }

    public void addItem(GroceryItem item){

        if(mGroceryItems != null){
            mGroceryItems.add(item);
        }
    }

    public void clearList(){
        if(mGroceryItems.isEmpty()){
            return;
        }

        else{
            mGroceryItems.clear();
        }
    }
}
