package com.permoveo.apps.jumboshrimp.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.permoveo.apps.jumboshrimp.R;
import com.permoveo.apps.jumboshrimp.adapters.RecipeAdapter;
import com.permoveo.apps.jumboshrimp.model.Recipe;

import java.util.List;


public class RecipeSearchResultsFragment extends Fragment {

    private GridView mRecipesGrid;
    private RecipeAdapter mRecipeAdapter;
    private List<Recipe> mRecipes;


    public static RecipeSearchResultsFragment newInstance(List<Recipe> recipes) {
        RecipeSearchResultsFragment fragment = new RecipeSearchResultsFragment();
        Bundle args = new Bundle();


        return fragment;
    }

    public RecipeSearchResultsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recipe_search_results, container, false);

        mRecipesGrid = (GridView) v.findViewById(R.id.gvRecipesList);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mRecipeAdapter  = new RecipeAdapter(getActivity(), mRecipes);
        mRecipesGrid.setAdapter(mRecipeAdapter);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public void setResults(List<Recipe> recipes){
        mRecipes = recipes;
    }

    public List<Recipe> getRecipes(){

        return mRecipes;
    }



}