package com.permoveo.apps.jumboshrimp.fragments;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.permoveo.apps.jumboshrimp.R;


/**
 * Created by byfieldj on 4/28/15.
 */
public class BarcodeScannerFragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_barcode_scan, container, false);




        //Set up barcode scanner
        startBarcodeScan();
        return v;
    }


    private void startBarcodeScan(){
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setCaptureLayout(R.layout.layout_barcode_scan);
        //integrator.setLegacyCaptureLayout(R.layout.custom_legacy_capture_layout);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);
        integrator.autoWide();
        integrator.setOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        integrator.setCameraId(Camera.CameraInfo.CAMERA_FACING_BACK);

        IntentIntegrator.forSupportFragment(this);
        integrator.initiateScan();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("BarcodeScannerFragment", "onActivityResult: result NULL");
            } else {
                Log.d("BarcodeScannerFragment", "onActivityResult: result NOT NULL " + result.getContents());
            }


        }
    }
}