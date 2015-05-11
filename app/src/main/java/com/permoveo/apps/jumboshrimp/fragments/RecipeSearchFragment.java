package com.permoveo.apps.jumboshrimp.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.permoveo.apps.jumboshrimp.R;

import java.util.List;

import com.permoveo.apps.jumboshrimp.listeners.OnApiRequestCompletedListener;
import com.permoveo.apps.jumboshrimp.listeners.OnVoiceRecognitionListener;
import com.permoveo.apps.jumboshrimp.model.Recipe;
import com.permoveo.apps.jumboshrimp.providers.BigOvenDataSourceProvider;
import com.permoveo.apps.jumboshrimp.providers.FatSecretDataSourceProvider;
import com.permoveo.apps.jumboshrimp.utils.FragmentUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class RecipeSearchFragment extends Fragment implements OnApiRequestCompletedListener {

    public static final String TAG = RecipeSearchFragment.class.getSimpleName();

    @InjectView(R.id.etSearchField)
    EditText mSearchField;
    @InjectView(R.id.bSearch)
    Button mSearchButton;
    @InjectView(R.id.bClearSearch)
    Button mClearSearchButton;
    @InjectView(R.id.bVoiceCommand)
    Button mVoiceCommand;
    @InjectView(R.id.bBarCodeScan)
    Button mScanBarcode;


    private OnRecipesLoadedListener mOnRecipesLoadedListener;
    BigOvenDataSourceProvider mProvider;


    public RecipeSearchFragment() {
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

        View v = inflater.inflate(R.layout.fragment_search, container, false);
        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.rlContainer);


        ButterKnife.inject(this, v);

        // Inflate the layout for this fragment
        return v;
    }


    @OnClick(R.id.bClearSearch)
    public void clearSearch() {

        mSearchField.setText("");
    }

    @OnClick(R.id.bSearch)
    public void search() {
        String mSearchTerm = mSearchField.getText().toString();
        if (!mSearchTerm.isEmpty()) {
            performSearch(mSearchTerm);
        }
    }

    @OnClick(R.id.bVoiceCommand)
    public void voiceSearch() {
        VoiceDialogFragment fragment = new VoiceDialogFragment();
        FragmentUtil.showDialogFragment(fragment, VoiceDialogFragment.TAG, getFragmentManager());

        fragment.setListener(new OnVoiceRecognitionListener() {
            @Override
            public void onSpeechResultSuccess(String recognizedText) {
                if (!recognizedText.isEmpty()) {
                    performSearch(recognizedText);
                }
            }

            @Override
            public void onSpeechResultError() {

            }
        });
    }

    @OnClick(R.id.bBarCodeScan)
    public void scanBarcode() {

        FragmentManager manager = getChildFragmentManager();
        BarcodeScannerFragment fragment = new BarcodeScannerFragment();
        manager.beginTransaction().add(fragment, "barcode").addToBackStack("barcode").commit();

        //Intent scan = new Intent(getActivity(), ActivityBarcodeScan.class);
        //startActivity(scan);


    }


    private void performSearch(String term) {

        // Show progress dialog
        FragmentUtil.showProgressDialog(getActivity());

        mProvider = new BigOvenDataSourceProvider(getActivity());
        mProvider.setListener(this);
        mProvider.searchForRecipesList(term, false);

    }

    public interface OnRecipesLoadedListener {
        public abstract void onRecipesLoaded(List<Recipe> recipes);
    }

    public void setOnRecipesLoadedListener(OnRecipesLoadedListener listener) {
        this.mOnRecipesLoadedListener = listener;
    }

    @Override
    public void onApiRequestSuccess() {

        // Clear progress dialog
        FragmentUtil.clearProgressDialog(getActivity());

        //Clear the search list
        mSearchField.setText("");

        //Log.d("SearchFragment", "API RESPONSE! -> " + object.toString());
        Toast.makeText(getActivity(), "API RESPONSE", Toast.LENGTH_SHORT).show();

        if (mOnRecipesLoadedListener != null) {
            mOnRecipesLoadedListener.onRecipesLoaded(mProvider.getRecipes());
        }
    }

    @Override
    public void onApiRequestError() {
        // Clear progress dialog
        FragmentUtil.clearProgressDialog(getActivity());
     }
}