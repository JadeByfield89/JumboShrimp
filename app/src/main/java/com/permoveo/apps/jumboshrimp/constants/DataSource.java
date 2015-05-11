package com.permoveo.apps.jumboshrimp.constants;

/**
 * Created by byfieldj on 4/14/15.
 * A DataSource is just a simple way of keep tracking of our sources
 * The application pulls its data from various apis/web services
 * so we need a consistent way to define which data source is being used where
 */


public enum DataSource {


    BigOven,  //Has over 350,000 recipes. Returns full recipe data/instructions
    FatSecret, //Ingredient/Item Nutrition search
    Edamam,    //Has over 1.5 million recipes, need to scrape URLs returned for instructions
    KitchenManager, //Has 3,000 recipes. Returns full recipe data/instructions
    Food2Fork //Has 200,000 recipes. Needs URL scraping for instructions


}
