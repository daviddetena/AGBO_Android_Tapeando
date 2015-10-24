package com.daviddetena.tapeando.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tables {

    private static Tables sInstance;
    private List<Table> mTables;


    /**
     * Static method to get the singleton of Tables
     * @param context
     * @return
     */
    public static Tables getInstance(Context context){
        if(sInstance == null){
            sInstance = new Tables(context);
        }
        return sInstance;
    }

    /**
     * Private constructor to hold the singleton. Init the list of tables
     * @param context
     */
    private Tables(Context context){
        mTables = new ArrayList<>();
        // Dummy data: 12 tables
        for(int i=0; i<=11; i++){
            mTables.add(new Table(i));
        }
        //Collections.sort(mTables);
    }


    /**
     * Get the list of all the tables
     * @return
     */
    public List<Table> getTables(){
        return mTables;
    }


    /**
     * Get the table specified by its table number
     * @param tableNumber
     * @return
     */
    public Table getTable(int tableNumber){
        for(Table table:mTables){
            if(table.getTableNumber() == tableNumber){
                return table;
            }
        }
        return null;
    }


    /**
     * Clean all the courses for a table
     * @param tableNumber
     */
    public void cleanTable(int tableNumber){

        for (int i = 0; i < mTables.size(); i++) {
            Table table = mTables.get(i);
            if (table.getTableNumber() == tableNumber) {
                table.cleanTable();
                mTables.set(i, table);
                //sendDataSetChangedIntent();
            }
        }
    }
}
