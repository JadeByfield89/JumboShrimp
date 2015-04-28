package com.permoveo.apps.jumboshrimp.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.permoveo.apps.jumboshrimp.R;


public class ActivityBarcodeScan extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scan);


        //scanBarcodeCustomLayout();


    }

    public void scanBarcode() {
        new IntentIntegrator(this).initiateScan();
    }

    public void scanBarcodeCustomLayout() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureLayout(R.layout.layout_barcode_scan);
        //integrator.setLegacyCaptureLayout(R.layout.custom_legacy_capture_layout);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.autoWide();
        integrator.setOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        integrator.setCameraId(Camera.CameraInfo.CAMERA_FACING_BACK);

        integrator.initiateScan();
    }

    public void scanBarcodeFrontCamera() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCameraId(Camera.CameraInfo.CAMERA_FACING_FRONT);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
