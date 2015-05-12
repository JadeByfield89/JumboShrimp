package com.permoveo.apps.jumboshrimp.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import com.permoveo.apps.jumboshrimp.R;
import com.permoveo.apps.jumboshrimp.adapters.RecipeAdapter;
import com.permoveo.apps.jumboshrimp.listeners.OnApiRequestCompletedListener;
import com.permoveo.apps.jumboshrimp.model.Recipe;
import com.permoveo.apps.jumboshrimp.providers.BigOvenDataSourceProvider;
import com.permoveo.apps.jumboshrimp.utils.FragmentUtil;

import java.util.List;


public class RecipeSearchResultsFragment extends Fragment implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    private GridView mRecipesGrid;
    private RecipeAdapter mRecipeAdapter;
    private List<Recipe> mRecipes;
    private String mSearchTerm;
    private BigOvenDataSourceProvider mProvider;
    private int currentPage = 1;
    private boolean enableLoadMore = true;

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

        mRecipeAdapter  = new RecipeAdapter(getActivity());
        mRecipesGrid.setAdapter(mRecipeAdapter);
        mRecipesGrid.setOnScrollListener(this);
        mRecipesGrid.setOnItemClickListener(this);
        mRecipeAdapter.addRecipes(mRecipes);
        currentPage = 1;
        if (mRecipes == null || mRecipes.size() < BigOvenDataSourceProvider.RESULTS_PER_PAGE) {
            enableLoadMore = false;
        }
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onItemClick(android.widget.AdapterView<?> adapterView, android.view.View view, int i, long l) {
        Recipe recipe = (Recipe)mRecipeAdapter.getItem(i);

        RecipeDetailFragment fragment = RecipeDetailFragment.newInstance(recipe);
        FragmentUtil.replaceFragment(getActivity().getSupportFragmentManager(), R.id.content_frame, fragment);
    }

    @Override
    public void onScrollStateChanged(android.widget.AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(android.widget.AbsListView absListView, int i, int i1, int i2) {
        if (enableLoadMore && mRecipesGrid.getLastVisiblePosition() + 1 == currentPage * BigOvenDataSourceProvider.RESULTS_PER_PAGE) {
            currentPage++;

            FragmentUtil.showProgressDialog(getActivity());
            mProvider = new BigOvenDataSourceProvider(getActivity());
            mProvider.setCurrentPage(currentPage);
            mProvider.setListener(new OnApiRequestCompletedListener() {
                @Override
                public void onApiRequestSuccess() {
                    FragmentUtil.clearProgressDialog(getActivity().getSupportFragmentManager());
                    List<Recipe> recipes = mProvider.getRecipes();
                    if (recipes == null ||recipes.size() < BigOvenDataSourceProvider.RESULTS_PER_PAGE) {
                        enableLoadMore = false;
                    }
                    mRecipeAdapter.addRecipes(recipes);
                }

                @Override
                public void onApiRequestError() {
                    FragmentUtil.clearProgressDialog(getActivity().getSupportFragmentManager());
                }
            });
            mProvider.searchForRecipesList(mSearchTerm, false);
        }
    }

    public void setResults(List<Recipe> recipes){
        mRecipes = recipes;
    }

    public void setSearchTerm(String searchTerm) {
        mSearchTerm = searchTerm;
    }

    public List<Recipe> getRecipes(){

        return mRecipes;
    }

}