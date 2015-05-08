package com.permoveo.apps.jumboshrimp.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.permoveo.apps.jumboshrimp.R;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.permoveo.apps.jumboshrimp.activities.ActivityBarcodeScan;
import com.permoveo.apps.jumboshrimp.listeners.OnApiRequestCompletedListener;
import com.permoveo.apps.jumboshrimp.model.Recipe;
import com.permoveo.apps.jumboshrimp.providers.BigOvenDataSourceProvider;
import com.permoveo.apps.jumboshrimp.providers.DataSourceProvider;
import com.permoveo.apps.jumboshrimp.providers.FatSecretDataSourceProvider;
import com.permoveo.apps.jumboshrimp.utils.FragmentUtil;

import android.speech.RecognitionListener;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class RecipeSearchFragment extends Fragment implements OnApiRequestCompletedListener, RecognitionListener {

    public static final String TAG = RecipeSearchFragment.class.getSimpleName();

    @InjectView(R.id.etSearchField)
    EditText mSearchField;
    @InjectView(R.id.bSearch)
    Button mSearchButton;
    @InjectView(R.id.bClearSearch)
    Button mClearSearchButton;
    @InjectView(R.id.bVoiceCommand)
    Button mVoiceCommand;
    @InjectView(R.id.tvResultText)
    TextView mResultText;

    @InjectView(R.id.bBarCodeScan)
    Button mScanBarcode;


    private OnRecipesLoadedListener mOnRecipesLoadedListener;
    BigOvenDataSourceProvider mProvider;
    private boolean mVoiceMode = false;
    private SpeechRecognizer mRecognizer;


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
        mRecognizer.cancel();
        mRecognizer.destroy();


    }

    @Override
    public void onPause() {
        super.onPause();
        mRecognizer.stopListening();
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
        mResultText.setVisibility(View.INVISIBLE);


        mRecognizer = SpeechRecognizer.createSpeechRecognizer(getActivity());
        mRecognizer.setRecognitionListener(this);


        //TESTING FAT SECRET API
        //FatSecretDataSourceProvider provider = new FatSecretDataSourceProvider(getActivity());
        //provider.searchForFoodByTitle("banana");

        // Inflate the layout for this fragment
        return v;
    }


    @OnClick(R.id.bClearSearch)
    public void clearSearch() {

        mSearchField.setText("");
        mResultText.setText("");
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
        if (!mVoiceMode) {
            mVoiceMode = true;
            mVoiceCommand.setText("Listening...");
            startSpeechRecognizer();
        } else {
            mVoiceMode = false;
            mVoiceCommand.setText("Voice Command");
        }
    }

    @OnClick(R.id.bBarCodeScan)
    public void scanBarcode() {

        FragmentManager manager = getChildFragmentManager();
        BarcodeScannerFragment fragment = new BarcodeScannerFragment();
        manager.beginTransaction().add(fragment, "barcode").addToBackStack("barcode").commit();

        //Intent scan = new Intent(getActivity(), ActivityBarcodeScan.class);
        //startActivity(scan);


    }


    private void startSpeechRecognizer() {
        {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, "voice.recognition.test");

            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
            mRecognizer.startListening(intent);
            Log.i("111111", "11111111");
        }
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


    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int error) {

    }

    @Override
    public void onResults(Bundle results) {

        String str = new String();
        Log.d("RecipeSearchFragment", "onResults " + results);
        ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        for (int i = 0; i < data.size(); i++) {
            Log.d("RecipeSearchFragment", "result " + data.get(i));
            str += data.get(i);
        }
        mResultText.setVisibility(View.VISIBLE);
        mResultText.setText(data.get(0));
        mSearchField.setText(data.get(0));

        performSearch(data.get(0));


    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }


}
