package com.daviddetena.tapeando.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.daviddetena.tapeando.fragment.TableFragment;

public class TableActivity extends SingleFragmentActivity {

    // EXTRA for displaying the proper Table
    private static final String EXTRA_TABLE_NUMBER = "com.daviddetena.tapeando.table_number";

    /**
     * Static method to get an intent and pass an argument in to communicate with
     * other activities this one could be called from
     * @param packageContext
     * @param tableNumber
     * @return Intent object
     */
    public static Intent newIntent(Context packageContext, int tableNumber){
        Intent intent = new Intent(packageContext, TableActivity.class);
        intent.putExtra(EXTRA_TABLE_NUMBER, tableNumber);

        return intent;
    }


    @Override
    protected Fragment createFragment() {
        // Gets the EXTRA param with the table number received and pass in to the Fragment to be created
        int tableNumber = (int)getIntent().getSerializableExtra(EXTRA_TABLE_NUMBER);
        return TableFragment.newInstance(tableNumber);
    }
}
