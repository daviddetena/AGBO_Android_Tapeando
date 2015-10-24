package com.daviddetena.tapeando.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.daviddetena.tapeando.R;


/**
 * This abstract class allows inflating layouts from generic fragment, since that every class that
 * inherits from this class needs to implements createFragment() on its own way to return a specific
 * Fragment in each case.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the activity's view from this layout
        setContentView(R.layout.activity_fragment);

        // Get FragmentManager to begin a transaction and include a new Fragment got from its id
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = createFragment();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
