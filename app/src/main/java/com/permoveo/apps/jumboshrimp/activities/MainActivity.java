package com.permoveo.apps.jumboshrimp.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.permoveo.apps.jumboshrimp.R;

import com.permoveo.apps.jumboshrimp.fragments.SearchFragment;

/**
 * Created by byfieldj on 4/14/15.
 */
public class MainActivity extends FragmentActivity {


   private SearchFragment mSearchFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSearchFragment = new SearchFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity_main_container, mSearchFragment).commit();

        Log.d("MainActivity", "onCreate");






    }


}
