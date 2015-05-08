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
import com.permoveo.apps.jumboshrimp.utils.FragmentUtil;

import java.util.List;

/**
 * Created by byfieldj on 4/14/15.
 */
public class MainActivity extends FragmentActivity implements RecipeSearchFragment.OnRecipesLoadedListener {

   private RecipeSearchFragment mRecipeSearchFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecipeSearchFragment = new RecipeSearchFragment();
        mRecipeSearchFragment.setOnRecipesLoadedListener(this);

        FragmentUtil.addFragmentToLayout(this, R.id.activity_main_container, getSupportFragmentManager(),
                mRecipeSearchFragment, RecipeSearchFragment.TAG);

        Log.d("MainActivity", "onCreate");
    }

    @Override
    public void onRecipesLoaded(List<Recipe> recipes) {
        Log.d("MainActivity", "onRecipesLoaded");

        RecipeSearchResultsFragment fragment = new RecipeSearchResultsFragment();
        fragment.setResults(recipes);

        FragmentUtil.replaceFragment(getSupportFragmentManager(), R.id.activity_main_container, fragment);
    }
}
