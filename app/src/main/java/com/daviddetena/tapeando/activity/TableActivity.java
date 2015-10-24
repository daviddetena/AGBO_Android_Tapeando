package com.daviddetena.tapeando.activity;

import android.support.v4.app.Fragment;

import com.daviddetena.tapeando.fragment.TableFragment;

public class TableActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TableFragment();
    }
}
