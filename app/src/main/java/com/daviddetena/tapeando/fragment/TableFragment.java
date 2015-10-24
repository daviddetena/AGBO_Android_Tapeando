package com.daviddetena.tapeando.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daviddetena.tapeando.R;
import com.daviddetena.tapeando.model.Course;

public class TableFragment extends Fragment{

    // Model
    private Course mCourse;

    // UI widgets
    private TextView mNameTextView;
    private TextView mPriceTextView;
    private TextView mNotesTextView;
    private TextView mDescriptionTextView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCourse = new Course();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table, container, false);



        /*
        // Wire up widgets
        mNameTextView = (TextView) view.findViewById(R.id.course_name);
        mDescriptionTextView = (TextView) view.findViewById(R.id.course_description);
        mPriceTextView = (TextView) view.findViewById(R.id.course_price);
        mNotesTextView = (TextView) view.findViewById(R.id.course_notes);

        mNameTextView.setText(mCourse.getName());
        mDescriptionTextView.setText(mCourse.getDescription());
        mPriceTextView.setText(String.format("%.2f euros", mCourse.getPrice()));
        mNotesTextView.setText(mCourse.getNotes());

        // Listener to save notes input
        mNotesTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO: let the activity know about changes on notes
                mCourse.setNotes(mNotesTextView.getText().toString());
            }
        });
        */
        return view;
    }
}
