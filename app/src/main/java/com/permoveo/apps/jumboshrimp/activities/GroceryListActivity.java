package com.permoveo.apps.jumboshrimp.activities;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.j256.ormlite.dao.Dao;
import com.permoveo.apps.jumboshrimp.R;
import com.permoveo.apps.jumboshrimp.activities.base.BaseDrawerActivity;
import com.permoveo.apps.jumboshrimp.database.GroceryDatabaseHelper;
import com.permoveo.apps.jumboshrimp.fragments.GroceryListFragment;
import com.permoveo.apps.jumboshrimp.model.GroceryItem;
import com.permoveo.apps.jumboshrimp.utils.FragmentUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroceryListActivity extends BaseDrawerActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_grocery_list);


        setupGroceryList();
    }


    private void setupGroceryList() {

        List<GroceryItem> groceryList = new ArrayList<GroceryItem>();

        //Get the grocery list from the DB and pass it to the fragment
        try {
            GroceryDatabaseHelper helper = new GroceryDatabaseHelper(this);
            Dao<GroceryItem, Integer> groceryDao = helper.getDao();

            //Get the grocery list
            groceryList = groceryDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        GroceryListFragment fragment = GroceryListFragment.newInstance();
        fragment.setGroceryList(groceryList);

        FragmentManager manager = getSupportFragmentManager();
        FragmentUtil.addFragmentToLayout(this, R.id.content_frame, manager, fragment, "grocery list");




    }


}
