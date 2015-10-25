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
import com.daviddetena.tapeando.fragment.CourseFragment;
import com.daviddetena.tapeando.model.Course;
import com.daviddetena.tapeando.model.Table;
import com.daviddetena.tapeando.model.Tables;

import java.util.List;

public class TableCoursePagerActivity extends FragmentActivity{

    // EXTRAS to let the ViewPager's adapter know which Table object it needs to display
    private static final String EXTRA_TABLE_NUMBER = "com.daviddetena.tapeando.table_number";
    private static final String EXTRA_COURSE_ID = "com.daviddetena.tapeando.course_id";

    private ViewPager mViewPager;
    private List<Course> mCourses;


    /**
     * Static method to get an intent and pass an argument in to communicate with
     * other activities this one could be called from
     * @param packageContext
     * @param courseId
     * @return Intent
     */
    public static Intent newIntent(Context packageContext, int tableNumber, int courseId){
        // Create a new intent with the EXTRA param with the crimeId
        Intent intent = new Intent(packageContext, TableCoursePagerActivity.class);
        intent.putExtra(EXTRA_TABLE_NUMBER, tableNumber);
        intent.putExtra(EXTRA_COURSE_ID, courseId);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflates the view with the view pager for the courses
        setContentView(R.layout.activity_table_course_pager);

        // Gets the number of the course to display, received as an extra
        final int tableNumber = (int)getIntent().getSerializableExtra(EXTRA_TABLE_NUMBER);
        int courseId = (int)getIntent().getSerializableExtra(EXTRA_COURSE_ID);

        // Set up model
        Table table = Tables.getInstance(this).getTable(tableNumber);
        mCourses = table.getCourses();

        // Get View Pager resource from layout
        mViewPager = (ViewPager) findViewById(R.id.activity_table_course_pager_view_pager);

        // Set up Fragment Manager and PagerAdapter via FragmentStatePagerAdapter inner class
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Adapter for the View Pager
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Course course = mCourses.get(position);
                return CourseFragment.newInstance(tableNumber, course.getId());
            }

            @Override
            public int getCount() {
                return mCourses.size();
            }
        });

        // Select the course to be displayed by the ViewPager
        for(int i=0; i<mCourses.size(); i++){
            if (mCourses.get(i).getId() == courseId){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
