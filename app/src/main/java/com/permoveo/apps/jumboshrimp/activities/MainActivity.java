package com.permoveo.apps.jumboshrimp.activities;

import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
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
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity_main_container, mRecipeSearchFragment).addToBackStack("search").commit();

        Log.d("MainActivity", "onCreate");






    }


    @Override
    public void onRecipesLoaded(List<Recipe> recipes) {
        Log.d("MainActivity", "onRecipesLoaded");

        RecipeSearchResultsFragment fragment = new RecipeSearchResultsFragment();
        fragment.setResults(recipes);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_container, fragment).addToBackStack("results").commit();
    }

    public static void showVoiceDialog(){

    }
}
