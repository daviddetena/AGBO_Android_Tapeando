package com.daviddetena.tapeando.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daviddetena.tapeando.R;
import com.daviddetena.tapeando.activity.TableCoursePagerActivity;
import com.daviddetena.tapeando.model.Course;
import com.daviddetena.tapeando.model.Table;
import com.daviddetena.tapeando.model.Tables;

import java.util.List;

public class TableFragment extends Fragment{

    // ARGUMENT TABLE NUMBER
    private static final String ARG_TABLE_NUMBER = "table_number";

    // Model
    private Table mTable;

    // UI Widgets
    private TextView mNotesTextView;

    // RecyclerView and adapter for managing the list of plates
    private RecyclerView mCourseRecyclerView;
    private CourseAdapter mAdapter;

    /**
     * Get a TableFragment object with arguments from a parameter passed in
     * @param tableNumber
     * @return
     */
    public static TableFragment newInstance(int tableNumber){

        // Create a new Bundle for arguments and set them to the new fragment being returned back
        Bundle args = new Bundle();
        args.putSerializable(ARG_TABLE_NUMBER, tableNumber);

        TableFragment fragment = new TableFragment();
        fragment.setArguments(args);
        return fragment;
    }


    /**
     * Get the table number from the arguments to inflate the view with the Table of the number
     * passed
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int tableNumber = (int)getArguments().getSerializable(ARG_TABLE_NUMBER);
        mTable = Tables.getInstance(getActivity()).getTable(tableNumber);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_table, container, false);

        // Set RecyclerView and LayoutManager
        mCourseRecyclerView = (RecyclerView)view.findViewById(R.id.table_courses_recycler_view);
        mCourseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Wire up
        mNotesTextView = (TextView) view.findViewById(R.id.table_number);
        mNotesTextView.setText(mTable.getNotes());

        mNotesTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Notes did change
                mTable.setNotes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        updateUI();
        return view;
    }


    /**
     * In general, onResume() is the safest place to update a fragment's view. It's refreshed, for
     * instance, when pressing the back button and is back to the previous view
     */
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        List<Course> courses = mTable.getCourses();

        // Init and set our adapter to the RecyclerView, with the model to display (courses)
        if(mAdapter == null){
            // No adapter yet
            mAdapter = new CourseAdapter(courses);
            mCourseRecyclerView.setAdapter(mAdapter);
        }
        else{
            // Adapter already existed. Notify it to refetch data
            mAdapter.notifyDataSetChanged();

            // Uncomment if only the selected table is needed to be refreshed
            //mAdapter.notifyItemChanged(tableSelectedIndex);
        }
    }


    /**
     * Inner class to hold the View for each row in the list of courses. Now the itemView is a
     * view inflated with the layout
     */
    private class CourseHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // Course for each item in the list
        private Course mCourse;

        // UI widgets
        private ImageView mCoursePhotoImageView;
        private TextView mNameTextView;
        private TextView mPriceTextView;
        private TextView mAllergensTextView;


        /**
         * Constructor is responsible for getting references for UI widgets and self-assign as the
         * listener of the itemView
         * @param itemView
         */
        public CourseHolder(View itemView){
            super(itemView);

            // Self-assigned as the listener for click events on items
            itemView.setOnClickListener(this);

            // Reference for UI widgets
            mCoursePhotoImageView = (ImageView)itemView.findViewById(R.id.list_item_table_course_icon_image_view);
            mNameTextView = (TextView)itemView.findViewById(R.id.list_item_table_course_name_text_view);
            mPriceTextView = (TextView)itemView.findViewById(R.id.list_item_table_course_price_text_view);
            mAllergensTextView = (TextView)itemView.findViewById(R.id.list_item_table_course_allergens_text_view);
        }

        /**
         * Set up UI widgets
         * @param course
         */
        public void bindCourse(Course course){
            mCourse = course;

            // Wire up widgets
            mCoursePhotoImageView.setImageResource(R.drawable.icon_plates);
            mNameTextView.setText(mCourse.getName());
            mPriceTextView.setText(String.format("%.2f €", mCourse.getPrice()));
            mAllergensTextView.setText(String.format("%.2f €", mCourse.getPrice()));
        }


        @Override
        public void onClick(View v) {
            // Start TableCoursePagerActivity
            Intent intent = TableCoursePagerActivity
                    .newIntent(getActivity(), mCourse.getTableNumber() , mCourse.getId());
            startActivity(intent);
        }
    }

    /**
     * The RecyclerView will communicate with this adapter when a ViewHolder needs to be created
     * or connected with a Table object.
     */
    private class CourseAdapter extends RecyclerView.Adapter<CourseHolder>{

        // Current table's list of courses
        private List<Course> mCourses;

        /**
         * The Adapter is initialized with the list of course for this table
         * @param courses
         */
        public CourseAdapter(List<Course> courses){
            mCourses = courses;
        }


        /**
         * Called by the RecyclerView when it needs a new view to display an item.
         * Create a View (Android list item type with a single TextView) and wrap it in a ViewHolder.
         * @param parent
         * @param viewType
         * @return
         */
        @Override
        public CourseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_table_course, parent, false);
            return new CourseHolder(view);
        }

        /**
         * Bind a ViewHolder's View with the Table model
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(CourseHolder holder, int position) {
            Course course = mCourses.get(position);

            // Call to the method to set up the UI
            holder.bindCourse(course);
        }

        @Override
        public int getItemCount() {
            return mCourses.size();
        }
    }
}
