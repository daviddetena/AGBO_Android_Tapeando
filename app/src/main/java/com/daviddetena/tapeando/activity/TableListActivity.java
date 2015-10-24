package com.daviddetena.tapeando.activity;

import android.support.v4.app.Fragment;

import com.daviddetena.tapeando.fragment.TableListFragment;

public class TableListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new TableListFragment();
    }
}
