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
import com.daviddetena.tapeando.model.Table;
import com.daviddetena.tapeando.model.Tables;

public class TableFragment extends Fragment{

    // ARGUMENT TABLE NUMBER
    private static final String ARG_TABLE_NUMBER = "table_number";

    // Model
    private Table mTable;

    // UI Widgets
    private TextView mNotesTextView;


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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the table number from the arguments to inflate the view with the Table of the number
        // passed
        int tableNumber = (int)getArguments().getSerializable(ARG_TABLE_NUMBER);
        mTable = Tables.getInstance(getActivity()).getTable(tableNumber);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_table, container, false);

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



        return view;
    }
}
