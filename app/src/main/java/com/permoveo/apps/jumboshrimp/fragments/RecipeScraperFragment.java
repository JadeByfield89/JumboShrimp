package com.permoveo.apps.jumboshrimp.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.permoveo.apps.jumboshrimp.R;
import com.permoveo.apps.jumboshrimp.listeners.OnApiRequestCompletedListener;
import com.permoveo.apps.jumboshrimp.listeners.OnVoiceRecognitionListener;
import com.permoveo.apps.jumboshrimp.model.Recipe;
import com.permoveo.apps.jumboshrimp.providers.BigOvenDataSourceProvider;
import com.permoveo.apps.jumboshrimp.utils.FragmentUtil;
import com.permoveo.apps.jumboshrimp.web.RecipeScraper;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class RecipeScraperFragment extends Fragment {

    public static final String TAG = RecipeScraperFragment.class.getSimpleName();

    @InjectView(R.id.etRecipeUrl)
    EditText mRecipeUrl;

    @InjectView(R.id.bGetRecipe)
    Button mButton;

    public RecipeScraperFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_recipe_scraper, container, false);
        ButterKnife.inject(this, v);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

       Log.d(TAG, "onResume");
    }

    @OnClick(R.id.bGetRecipe)
    public void getRecipe() {
        String recipeUrl = mRecipeUrl.getText().toString();
        if (!recipeUrl.isEmpty()) {
            getRecipe(recipeUrl);
        }
    }

    private void getRecipe(String recipeUrl) {
        FragmentUtil.showProgressDialog(getActivity());

        RecipeScraper scraper = new RecipeScraper(recipeUrl);
        scraper.setListener(new OnApiRequestCompletedListener() {
            @Override
            public void onApiRequestSuccess(Recipe recipe) {
                FragmentUtil.clearProgressDialog(getActivity());
                RecipeDetailFragment fragment = RecipeDetailFragment.newInstance(recipe);
                FragmentUtil.replaceFragment(getActivity().getSupportFragmentManager(), R.id.content_frame, fragment);
            }

            @Override
            public void onApiRequestError() {
                FragmentUtil.clearProgressDialog(getActivity());
            }
        });
        scraper.parseHtmlToRecipe();
    }
}