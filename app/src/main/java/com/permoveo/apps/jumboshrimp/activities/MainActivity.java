package com.permoveo.apps.jumboshrimp.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.permoveo.apps.jumboshrimp.R;

import com.permoveo.apps.jumboshrimp.fragments.RecipeSearchFragment;
import com.permoveo.apps.jumboshrimp.fragments.RecipeSearchResultsFragment;
import com.permoveo.apps.jumboshrimp.model.Recipe;

import java.util.List;

/**
 * Created by byfieldj on 4/14/15.
 */
public class MainActivity extends FragmentActivity implements RecipeSearchFragment.onRecipesLoadedListener {


   private RecipeSearchFragment mRecipeSearchFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecipeSearchFragment = new RecipeSearchFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity_main_container, mRecipeSearchFragment).commit();

        Log.d("MainActivity", "onCreate");
    }

    @Override
    public void onRecipesLoaded(List<Recipe> recipes) {
        Log.d("MainActivity", "onRecipesLoaded");

        RecipeSearchResultsFragment fragment = new RecipeSearchResultsFragment();
        fragment.setResults(recipes);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_container, fragment).addToBackStack("results").commit();
    }
}
