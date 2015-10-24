package com.daviddetena.tapeando.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.daviddetena.tapeando.R;
import com.daviddetena.tapeando.fragment.TableFragment;
import com.daviddetena.tapeando.model.Table;
import com.daviddetena.tapeando.model.Tables;

import java.util.List;

public class TablePagerActivity extends FragmentActivity {

    // EXTRA to let the ViewPager's adapter know which Table object it needs to display
    private static final String EXTRA_TABLE_NUMBER = "com.daviddetena.tapeando.table_number";

    private ViewPager mViewPager;
    private List<Table> mTables;

    /**
     * Static method to get an intent and pass an argument in to communicate with
     * other activities this one could be called from
     * @param packageContext
     * @param tableNumber
     * @return Intent
     */
    public static Intent newIntent(Context packageContext, int tableNumber){
        // Create a new intent with the EXTRA param with the crimeId
        Intent intent = new Intent(packageContext, TablePagerActivity.class);
        intent.putExtra(EXTRA_TABLE_NUMBER, tableNumber);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflates the view with the view pager for the tables
        setContentView(R.layout.activity_table_pager);

        // Gets the number of the table to display received as an extra
        int tableNumber = (int)getIntent().getSerializableExtra(EXTRA_TABLE_NUMBER);

        // Set up model
        mTables = Tables.getInstance(this).getTables();

        // Get View Pager resource from layout
        mViewPager = (ViewPager) findViewById(R.id.activity_table_pager_view_pager);

        // Set up Fragment Manager and PagerAdapter via FragmentStatePagerAdapter inner class
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Adapter for the View Pager
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            /**
             * Current table to display
             * @param position
             * @return
             */
            @Override
            public Fragment getItem(int position) {
                Table table = mTables.get(position);
                return TableFragment.newInstance(table.getTableNumber());
            }

            /**
             * Total number of tables
             * @return
             */
            @Override
            public int getCount() {
                return mTables.size();
            }
        });

        // Select the table to be displayed by the ViewPager
        for(int i=0; i<mTables.size(); i++){
            if (mTables.get(i).getTableNumber() == tableNumber){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
