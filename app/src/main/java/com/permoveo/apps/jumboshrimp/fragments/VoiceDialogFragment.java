package com.permoveo.apps.jumboshrimp.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.permoveo.apps.jumboshrimp.R;
import com.permoveo.apps.jumboshrimp.listeners.OnVoiceRecognitionListener;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class VoiceDialogFragment extends DialogFragment {


    private OnVoiceRecognitionListener mListener;

    @InjectView(R.id.tvVoiceResult)
    TextView mResult;


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


        builder.setView(view);

        return builder.create();
    }


   /* @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (OnVoiceRecognitionListener) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new ClassCastException("Activity must implement OnVoiceRecognitionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (mListener != null) {
            mListener = null;
        }
    }*/
}
