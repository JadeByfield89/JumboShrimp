package com.permoveo.apps.jumboshrimp.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.permoveo.apps.jumboshrimp.R;
import com.permoveo.apps.jumboshrimp.adapters.GroceryListAdapter;
import com.permoveo.apps.jumboshrimp.model.GroceryItem;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class GroceryListFragment extends Fragment {

    @InjectView(R.id.lvGroceryList)
    ListView mGroceryListView;

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
        View v = inflater.inflate(R.layout.fragment_grocery_list, container, false);

        ButterKnife.inject(this, v);

        //set up the adapter
        GroceryListAdapter adapter = new GroceryListAdapter(getActivity(), mGroceryList);

        if(adapter == null){
            Log.d("GroceryListFragment", "Adapter is null");
        }

        if(mGroceryListView == null){
            Log.d("GroceryListFragment", "ListView is null");

        }
        mGroceryListView.setAdapter(adapter);



        return v;
    }





}
