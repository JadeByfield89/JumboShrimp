package com.permoveo.apps.jumboshrimp.model;

import java.util.Date;
import java.util.List;

/**
 * Created by byfieldj on 4/13/15.
 */


//An Inventory represents all the objects a user has currently scanned into the app and has stored in their kitchen
//A user may or may not scan every single item into their inventory(most likely not, since some grocery items are not scannable, or data is just not available on
 //particular item

//However, this is still a good reference point for the app to be able to keep track of what the user
//HAS decided to scan in. We will be updating the Inventory each time the user makes a meal(if their inventory is not empty)
public class Inventory {


    private List<GroceryItem> mGroceryItems;
    private List<ScannedItem> mScannedItems;
    private Date mDateLastUpdated;


    //An inventory can contain a list of both scannable and non-scannable items
    public Inventory(List<GroceryItem> groceryItems, List<ScannedItem> scannedItems){

        mGroceryItems = groceryItems;
        mScannedItems = scannedItems;
    }

    public List<GroceryItem> getGroceryItems(){
        return mGroceryItems;
    }

    public Date getDateLastUpdated(){
        return mDateLastUpdated;
    }

    public void setDate(Date date){
        mDateLastUpdated = date;
    }

    public List<ScannedItem> getScannedItems(){
        return mScannedItems;
    }



}
