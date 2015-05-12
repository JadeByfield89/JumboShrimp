package com.permoveo.apps.jumboshrimp.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.permoveo.apps.jumboshrimp.R;
import com.permoveo.apps.jumboshrimp.listeners.OnVoiceRecognitionListener;

import java.util.ArrayList;
import java.util.concurrent.Delayed;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class VoiceDialogFragment extends DialogFragment implements RecognitionListener {

    public static final String TAG = VoiceDialogFragment.class.getSimpleName();

    private static final int DELAY = 1000;

    private OnVoiceRecognitionListener mListener;

    @InjectView(R.id.tvVoiceResult)
    TextView mResult;

    @InjectView(R.id.bListen)
    Button mListen;

    private boolean mVoiceMode = false;
    private SpeechRecognizer mRecognizer;
    private Intent mRecognizerIntent;

    public VoiceDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.fragment_base_voice, null);
        ButterKnife.inject(this, view);

        builder.setView(view);

        mResult.setVisibility(View.INVISIBLE);

        mRecognizer = SpeechRecognizer.createSpeechRecognizer(getActivity());
        mRecognizer.setRecognitionListener(this);

        return builder.create();
    }

    @Override
    public void onPause() {
        super.onPause();

        mVoiceMode = false;
        mListen.setText("Listen");
        stopSpeechRecognizer();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        mRecognizer.cancel();

        try {
            mRecognizer.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @OnClick(R.id.bListen)
    public void onListenClicked() {
        if (!mVoiceMode) {
            mVoiceMode = true;
            mListen.setText("Listening...");
            startSpeechRecognizer();
        } else {
            mVoiceMode = false;
            mListen.setText("Listen");
            stopSpeechRecognizer();
        }
    }

    public void setListener(OnVoiceRecognitionListener listener) {
        mListener = listener;
    }

    private void startSpeechRecognizer() {
        {
            if (mRecognizerIntent == null) {
                mRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                mRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                mRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, "voice.recognition.test");
                mRecognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
            }

            mRecognizer.startListening(mRecognizerIntent);
            Log.i("111111", "11111111");
        }
    }

    private void stopSpeechRecognizer() {
        mRecognizer.stopListening();
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
        final ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        for (int i = 0; i < data.size(); i++) {
            Log.d("RecipeSearchFragment", "result " + data.get(i));
            str += data.get(i);
        }
        mResult.setVisibility(View.VISIBLE);
        mResult.setText(data.get(0));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
                if (mListener != null) {
                    mListener.onSpeechResultSuccess(data.get(0));
                }
            }
        }, DELAY);
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}
