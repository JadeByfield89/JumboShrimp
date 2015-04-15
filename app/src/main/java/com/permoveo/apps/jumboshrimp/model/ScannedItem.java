package com.permoveo.apps.jumboshrimp.model;

import com.permoveo.apps.jumboshrimp.interfaces.Scannable;

/**
 * Created by byfieldj on 4/13/15.
 */
public class ScannedItem extends BaseItem implements Scannable {

    private String mNumericBarcode = "";
    private boolean scanCompleted;

    private Photo mPhoto;


    @Override
    public void saveToInventory() {
        //TODO Save item to inventory database set its numeric barcode value
    }

    @Override
    public void scanCompleted(boolean finished) {

    }

    @Override
    public void startScanningBarcode() {

    }

    @Override
    public void setNumericBarcode(String barcode) {
        mNumericBarcode = barcode;
    }

    @Override
    public String getNumericBarcode() {
        return mNumericBarcode;
    }

    @Override
    public String getProductUrl() {
        return null;
    }
}
