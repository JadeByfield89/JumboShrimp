package com.permoveo.apps.jumboshrimp.utils;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.permoveo.apps.jumboshrimp.database.GroceryDatabaseHelper;
import com.permoveo.apps.jumboshrimp.model.GroceryItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by byfieldj on 5/14/15.
 * This class contain all our CRUD methods for manipulating Grocery Lists
 */
public class GroceryListManager {

    private static final String TAG = GroceryListManager.class.getSimpleName();
    private GroceryDatabaseHelper mHelper;
    private Dao<GroceryItem, Integer> mGroceryItemDao;
    private List<GroceryItem> mGroceryList;
    private Context mContext;

    public GroceryListManager(Context context) {
        mContext = context;
        mHelper = new GroceryDatabaseHelper(context);
    }

    public void setGroceryList(List<GroceryItem> list) {
        mGroceryList = list;
    }

    public List<GroceryItem> getGroceryList() {
        return mGroceryList;
    }


    public void addToGroceryList(GroceryItem item) {
        mGroceryList.add(item);


        //Get the GroceryItem DAO
        try {
            mGroceryItemDao = mHelper.getDao();


            //Add the item to the DB
            mGroceryItemDao.update(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void removeFromGroceryList(GroceryItem item) {
        mGroceryList.remove(item);


        //Get the GroceryItem DAO
        try{
            mGroceryItemDao = mHelper.getDao();

            //Remove the item from the DB
            mGroceryItemDao.delete(item);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<GroceryItem> getStoredGroceryList(){
        List<GroceryItem> groceryList = new ArrayList<GroceryItem>();
        try{
           mGroceryItemDao = mHelper.getDao();

           groceryList = mGroceryItemDao.queryForAll();
        }catch(SQLException e){
            e.printStackTrace();
            Log.d(TAG, "Error getting grocery list from database!");
        }

        return groceryList;
    }


}
