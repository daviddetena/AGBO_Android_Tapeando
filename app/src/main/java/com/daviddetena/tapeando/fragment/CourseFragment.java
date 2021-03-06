package com.daviddetena.tapeando.fragment;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.daviddetena.tapeando.R;
import com.daviddetena.tapeando.model.Allergen;
import com.daviddetena.tapeando.model.Course;
import com.daviddetena.tapeando.model.Table;
import com.daviddetena.tapeando.model.Tables;

import java.util.List;

public class CourseFragment extends Fragment{

    // ARGUMENTS NEEDED
    private static final String ARG_TABLE_NUMBER = "table_number";
    private static final String ARG_COURSE_ID = "course_id";

    // Model
    private Course mCourse;

    // UI Widgets
    private TextView mNameTextView;
    private ImageView mPhotoImageView;
    private TextView mAllergensTextView;
    private TextView mPriceTextView;
    private TextView mDescriptionTextView;
    private EditText mNotesTextView;
    private ImageView mIconPeanutsImageView;
    private ImageView mIconMustardImageView;
    private ImageView mIconGlutenImageView;
    private ImageView mIconFishImageView;
    private ImageView mIconShellfishImageView;
    private ImageView mIconEggImageView;
    private ImageView mIconMilkImageView;


    public static CourseFragment newInstance(int tableNumber, int courseId){

        // Create a new Bundle for arguments and set them to the new fragment being returned back
        Bundle args = new Bundle();
        args.putSerializable(ARG_TABLE_NUMBER, tableNumber);
        args.putSerializable(ARG_COURSE_ID, courseId);

        CourseFragment fragment = new CourseFragment();
        fragment.setArguments(args);
        return fragment;

    }

    /**
     * Get the course id from the arguments to inflate the view with the Course of the given id
     * passed
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the table number from the arguments to inflate the view with the Table of the number
        // passed
        int tableNumber = (int)getArguments().getSerializable(ARG_TABLE_NUMBER);
        Table table = Tables.getInstance(getActivity()).getTable(tableNumber);
        int courseId = (int)getArguments().getSerializable(ARG_COURSE_ID);

        for(Course course:table.getCourses()){
            if(course.getId() == courseId){
                mCourse = course;
                break;
            }
        }
    }


    /**
     * Set up the UI
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_course, container, false);

        // Wire up
        mNameTextView = (TextView)view.findViewById(R.id.fragment_course_name_text_view);
        mPhotoImageView = (ImageView)view.findViewById(R.id.fragment_course_photo_image_view);
        mAllergensTextView = (TextView)view.findViewById(R.id.fragment_course_allergens_text_view);

        mIconPeanutsImageView = (ImageView)view.findViewById(R.id.fragment_course_has_peanuts);
        mIconMustardImageView = (ImageView)view.findViewById(R.id.fragment_course_has_mustard);
        mIconGlutenImageView = (ImageView)view.findViewById(R.id.fragment_course_has_gluten);
        mIconFishImageView = (ImageView)view.findViewById(R.id.fragment_course_has_fish);
        mIconShellfishImageView = (ImageView)view.findViewById(R.id.fragment_course_has_shellfish);
        mIconEggImageView = (ImageView)view.findViewById(R.id.fragment_course_has_egg);
        mIconMilkImageView = (ImageView)view.findViewById(R.id.fragment_course_has_milk);


        // Set up
        setAllergenIcons();
        mPriceTextView = (TextView)view.findViewById(R.id.fragment_course_price_text_view);
        mDescriptionTextView = (TextView)view.findViewById(R.id.fragment_course_description_text_view);
        mNotesTextView = (EditText)view.findViewById(R.id.fragment_course_notes_edit_text);
        mNameTextView.setText(mCourse.getName());
        mAllergensTextView.setText(mCourse.getAllergensString());
        mPriceTextView.setText(String.format("%.2f", mCourse.getPrice()));
        mDescriptionTextView.setText(mCourse.getDescription());

        // Listener for notes to update it with the content typed by the user
        mNotesTextView.setText(mCourse.getNotes());
        mNotesTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            /**
             * Update Course model's notes with the text typed by the user
             * @param s
             * @param start
             * @param before
             * @param count
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCourse.setNotes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    /**
     * Wire allergens icons up
     */
    private void setAllergenIcons(){
        if(mCourse.getAllergens().size()>0){
            for (Allergen allergen:mCourse.getAllergens()) {
                switch (allergen.getName()){
                    case "Frutos Secos":
                        mIconPeanutsImageView.setImageResource(R.drawable.frutos_secos);
                        break;

                    case "Mostaza":
                        mIconMustardImageView.setImageResource(R.drawable.mostaza);
                        break;

                    case "Gluten":
                        mIconGlutenImageView.setImageResource(R.drawable.gluten);
                        break;

                    case "Pescado":
                        mIconFishImageView.setImageResource(R.drawable.pescado);
                        break;

                    case "Marisco":
                        mIconShellfishImageView.setImageResource(R.drawable.marisco);
                        break;

                    case "Huevo":
                        mIconEggImageView.setImageResource(R.drawable.huevo);
                        break;

                    case "Lactosa":
                        mIconMilkImageView.setImageResource(R.drawable.lactosa);
                        break;
                }
            }
        }
    }
}
