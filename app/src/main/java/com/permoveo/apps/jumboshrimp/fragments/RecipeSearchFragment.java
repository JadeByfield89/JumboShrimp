package com.permoveo.apps.jumboshrimp.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.permoveo.apps.jumboshrimp.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.permoveo.apps.jumboshrimp.listeners.OnApiRequestCompletedListener;
import com.permoveo.apps.jumboshrimp.model.Recipe;
import com.permoveo.apps.jumboshrimp.providers.BigOvenDataSourceProvider;
import com.permoveo.apps.jumboshrimp.providers.DataSourceProvider;


public class RecipeSearchFragment extends Fragment implements View.OnClickListener, OnApiRequestCompletedListener {

    private EditText mSearchField;
    private Button mSearchButton;
    private Button mClearSearchButton;
    private ArrayList<String> mSearchTerms = new ArrayList<String>();
    private onRecipesLoadedListener mListener;
    BigOvenDataSourceProvider mProvider;


    public RecipeSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mListener = (onRecipesLoadedListener) activity;
        }catch(ClassCastException e){
            Log.d("RecipeSearchFragment", "Activity must implement onRecipesLoadedListener!");
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_search, container, false);
        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.rlContainer);

        mSearchField = (EditText) layout.findViewById(R.id.etSearchField);
        mSearchButton = (Button) v.findViewById(R.id.bSearch);
        mClearSearchButton = (Button) v.findViewById(R.id.bClearSearch);

        mSearchButton.setOnClickListener(this);
        mClearSearchButton.setOnClickListener(this);
        mSearchField.setOnClickListener(this);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bClearSearch:

                break;

            case R.id.bSearch:
                extractSearchTerms();
                performSearch(mSearchTerms);
                break;

            case R.id.etSearchField:
                Log.d("SearchFragment", "search field touched");
                break;
        }
    }

    private void extractSearchTerms() {

        //Extracts individual search terms from the EditText field and composes a list out of them
        String queryEntered = mSearchField.getText().toString();
        Log.d("SearchFragment", "Query -> " + queryEntered);
        mSearchTerms.add(queryEntered);
        Log.d("SearchFragment", "List size -> " + mSearchTerms.size());

    }

    private void performSearch(ArrayList<String> terms) {
        mProvider = new BigOvenDataSourceProvider(getActivity());
        mProvider.setListener(this);
        mProvider.searchForRecipes(terms, false);



    }

    public interface onRecipesLoadedListener{
        public abstract void onRecipesLoaded(List<Recipe> recipes);
    }


    @Override
    public void onApiRequestSuccess() {

        //Log.d("SearchFragment", "API RESPONSE! -> " + object.toString());
        Toast.makeText(getActivity(), "API RESPONSE", Toast.LENGTH_SHORT).show();

        mListener.onRecipesLoaded(mProvider.getRecipes());
    }

    @Override
    public void onApiRequestError() {

    }
}
