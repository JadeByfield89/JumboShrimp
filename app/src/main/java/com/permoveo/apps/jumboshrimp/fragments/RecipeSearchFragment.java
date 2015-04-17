package com.permoveo.apps.jumboshrimp.fragments;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import com.permoveo.apps.jumboshrimp.listeners.OnApiRequestCompletedListener;
import com.permoveo.apps.jumboshrimp.model.Recipe;
import com.permoveo.apps.jumboshrimp.providers.BigOvenDataSourceProvider;
import com.permoveo.apps.jumboshrimp.providers.DataSourceProvider;

import edu.cmu.pocketsphinx.Assets;
import edu.cmu.pocketsphinx.Hypothesis;
import edu.cmu.pocketsphinx.RecognitionListener;
import edu.cmu.pocketsphinx.SpeechRecognizer;
import static edu.cmu.pocketsphinx.SpeechRecognizerSetup.defaultSetup;



public class RecipeSearchFragment extends Fragment implements View.OnClickListener, OnApiRequestCompletedListener, RecognitionListener {

    private EditText mSearchField;
    private Button mSearchButton;
    private Button mClearSearchButton;
    private Button mVoiceCommand;
    private TextView mResultText;
    private ArrayList<String> mSearchTerms = new ArrayList<String>();
    private onRecipesLoadedListener mListener;
    BigOvenDataSourceProvider mProvider;
    private boolean mVoiceMode = false;
    private SpeechRecognizer mRecognizer;
    private static final String KWS_SEARCH = "";
    private static final String KEYPHRASE = "";


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
        mRecognizer.shutdown();
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
        mVoiceCommand = (Button)v.findViewById(R.id.bVoiceCommand);
        mResultText = (TextView)v.findViewById(R.id.tvResultText);
        mResultText.setVisibility(View.INVISIBLE);

        mSearchButton.setOnClickListener(this);
        mClearSearchButton.setOnClickListener(this);
        mSearchField.setOnClickListener(this);
        mVoiceCommand.setOnClickListener(this);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bClearSearch:

                mResultText.setText("");
                break;

            case R.id.bSearch:
                extractSearchTerms();
                performSearch(mSearchTerms);
                break;

            case R.id.etSearchField:
                Log.d("SearchFragment", "search field touched");
                break;

            case R.id.bVoiceCommand:
                if(!mVoiceMode){
                    mVoiceMode = true;
                    mVoiceCommand.setText("Listening...");
                    startSpeechRecognizer();
                }

                else{
                    mVoiceMode = false;
                    mVoiceCommand.setText("VoiceCommand");
                }
                break;
        }
    }


    private void startSpeechRecognizer(){
        new RecognizerTask().execute();
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


    private class RecognizerTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Assets assets = new Assets(getActivity());
                File assetDir = assets.syncAssets();
                setupRecognizer(assetDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    private void setupRecognizer(File assetsDir) throws IOException {
        // The recognizer can be configured to perform multiple searches
        // of different kind and switch between them
        Log.d("RecipeSearchFragment", "setupRecognizer");
        mRecognizer = defaultSetup()
                .setAcousticModel(new File(assetsDir, "en-us-ptm"))
                .setDictionary(new File(assetsDir, "cmudict-en-us.dict"))

                        // To disable logging of raw audio comment out this call (takes a lot of space on the device)
                .setRawLogDir(assetsDir)

                        // Threshold to tune for keyphrase to balance between false alarms and misses
                .setKeywordThreshold(1e-45f)

                        // Use context-independent phonetic search, context-dependent is too slow for mobile
                .setBoolean("-allphone_ci", true)

                .getRecognizer();
        mRecognizer.addListener(this);

        /** In your application you might not need to add all those searches.
         * They are added here for demonstration. You can leave just one.
         */

        // Create keyword-activation search.
        //mRecognizer.addKeyphraseSearch(KWS_SEARCH, KEYPHRASE);
        mRecognizer.addKeywordSearch(KWS_SEARCH, new File(assetsDir, "cmudict-en-us.dict"));

        //mRecognizer.addGrammarSearch("be", new File(assetsDir, "digits.gram"));
        mRecognizer.startListening("be");

    }


    @Override
    public void onBeginningOfSpeech() {

        //Toast.makeText(getActivity(), "Speaking", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEndOfSpeech() {
        //Toast.makeText(getActivity(), "Stopped Speaking", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPartialResult(Hypothesis hypothesis) {
        Log.d("RecipeSearchFragment", "onPartialResult");
        mResultText.setVisibility(View.VISIBLE);

        if(hypothesis != null) {
            mResultText.setVisibility(View.VISIBLE);
            mResultText.setText(hypothesis.getHypstr());


            mSearchField.setText(hypothesis.getHypstr());


            mSearchTerms.add(hypothesis.getHypstr());
            performSearch(mSearchTerms);

            mVoiceMode = false;
            mVoiceCommand.setText("Voice Command");

            mRecognizer.stop();

            Log.d("RecipeSearchFragment", "onResult -> Result -> " + hypothesis.getHypstr());
        }

    }

    @Override
    public void onResult(Hypothesis hypothesis) {


    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onTimeout() {

    }
}
