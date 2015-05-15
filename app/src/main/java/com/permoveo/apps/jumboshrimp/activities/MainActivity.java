package com.permoveo.apps.jumboshrimp.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.permoveo.apps.jumboshrimp.R;

import com.permoveo.apps.jumboshrimp.activities.base.BaseDrawerActivity;
import com.permoveo.apps.jumboshrimp.database.GroceryDatabaseHelper;
import com.permoveo.apps.jumboshrimp.fragments.RecipeSearchFragment;
import com.permoveo.apps.jumboshrimp.fragments.RecipeSearchResultsFragment;
import com.permoveo.apps.jumboshrimp.model.GroceryItem;
import com.permoveo.apps.jumboshrimp.model.Recipe;
import com.permoveo.apps.jumboshrimp.utils.FragmentUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.permoveo.apps.jumboshrimp.utils.GroceryListManager;
import com.permoveo.apps.jumboshrimp.web.RecipeScraper;

/**
 * Created by byfieldj on 4/14/15.
 */
public class MainActivity extends BaseDrawerActivity implements RecipeSearchFragment.OnRecipesLoadedListener {

    private RecipeSearchFragment mRecipeSearchFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);


        mRecipeSearchFragment = new RecipeSearchFragment();

        mRecipeSearchFragment.setOnRecipesLoadedListener(this);


        FragmentUtil.addFragmentToLayout(this, R.id.content_frame, getSupportFragmentManager(),
                mRecipeSearchFragment, RecipeSearchFragment.TAG);



        Log.d("MainActivity", "onCreate");
    }


    @Override
    public void onRecipesLoaded(List<Recipe> recipes) {
        Log.d("MainActivity", "onRecipesLoaded");

        RecipeSearchResultsFragment fragment = new RecipeSearchResultsFragment();
        fragment.setResults(recipes);
        fragment.setSearchTerm(mRecipeSearchFragment.getSearchTerm());

        FragmentUtil.replaceFragment(getSupportFragmentManager(), R.id.content_frame, fragment);
    }

    @Override
    protected void onResume() {

        super.onResume();

        //Testing ORMlITE
        GroceryDatabaseHelper helper = new GroceryDatabaseHelper(this);
        try {
            Dao<GroceryItem, Integer> groceryDao = helper.getDao();
            Log.d("MainActivity", "Grocery List-> " + groceryDao.queryForAll());
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
