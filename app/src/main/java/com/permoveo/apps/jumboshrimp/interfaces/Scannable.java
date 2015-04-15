package com.permoveo.apps.jumboshrimp.interfaces;

/**
 * Created by byfieldj on 4/13/15.
 */
public interface Scannable {


    public abstract void scanCompleted(boolean finished);

    public abstract void saveToInventory();

    public abstract void startScanningBarcode();

    public abstract String getNumericBarcode();

    public abstract void setNumericBarcode(String barcode);

    public abstract String getProductUrl();


}
