package com.permoveo.apps.jumboshrimp.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.permoveo.apps.jumboshrimp.R;
import com.permoveo.apps.jumboshrimp.model.GroceryItem;

import java.util.List;


public class GroceryListFragment extends Fragment {

    private List<GroceryItem> mGroceryList;

    public static GroceryListFragment newInstance() {
        GroceryListFragment fragment = new GroceryListFragment();

        return fragment;
    }

    public GroceryListFragment() {
        // Required empty public constructor
    }

    public void setGroceryList(List<GroceryItem> groceryList){
        mGroceryList = groceryList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grocery_list, container, false);
    }





}
