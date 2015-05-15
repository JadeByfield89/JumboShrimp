package com.permoveo.apps.jumboshrimp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.permoveo.apps.jumboshrimp.model.GroceryItem;

import java.sql.SQLException;

/**
 * Created by byfieldj on 5/14/15.
 */
public class GroceryDatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = GroceryDatabaseHelper.class.getSimpleName();


    // the DAO object we use to access the SimpleData table
    private Dao<GroceryItem, Integer> mGroceryItemDao = null;
    private RuntimeExceptionDao<GroceryItem, Integer> groceryItemRuntimeDao = null;


    // name of the database file
    private static final String DATABASE_NAME = "grocerylist.db";

    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 2;

    public GroceryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            Log.i(TAG, "onCreate");
            TableUtils.createTable(connectionSource, GroceryItem.class);
        } catch (SQLException e) {
            Log.e(TAG, "Can't create database", e);
            throw new RuntimeException(e);
        }

        // here we try inserting data in the on-create as a test
        RuntimeExceptionDao<GroceryItem, Integer> dao = getSimpleDataDao();
        long millis = System.currentTimeMillis();
        // create some entries in the onCreate
        GroceryItem simple = new GroceryItem("Apple");
        simple.setQuantity(2);
        simple.setPrice(2.99);
        dao.create(simple);


        simple = new GroceryItem("Orange");
        simple.setQuantity(1);
        simple.setPrice(1.99);
        dao.create(simple);



        simple = new GroceryItem("Kiwi");
        simple.setQuantity(4);
        simple.setPrice(.99);
        dao.create(simple);


        simple = new GroceryItem("Pineapple");
        simple.setQuantity(1);
        simple.setPrice(.94);
        dao.create(simple);



        simple = new GroceryItem("Strawberries");
        simple.setQuantity(4);
        simple.setPrice(2.99);
        dao.create(simple);


        simple = new GroceryItem("Canteloupe");
        simple.setQuantity(7);
        simple.setPrice(5.99);
        dao.create(simple);

        simple = new GroceryItem("Mango");
        simple.setQuantity(6);
        simple.setPrice(3.99);
        dao.create(simple);


        Log.i(GroceryDatabaseHelper.class.getName(), "created new entries in onCreate: " + millis);


    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            Log.i(GroceryDatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, GroceryItem.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(GroceryDatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }

    }

    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
     * value.
     */
    public Dao<GroceryItem, Integer> getDao() throws SQLException {
        if (mGroceryItemDao == null) {
            mGroceryItemDao = getDao(GroceryItem.class);
        }
        return mGroceryItemDao;
    }


    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our GroceryItem class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */
    public RuntimeExceptionDao<GroceryItem, Integer> getSimpleDataDao() {
        if (groceryItemRuntimeDao == null) {
            groceryItemRuntimeDao = getRuntimeExceptionDao(GroceryItem.class);
        }
        return groceryItemRuntimeDao;
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        mGroceryItemDao = null;
        groceryItemRuntimeDao = null;
    }
}
