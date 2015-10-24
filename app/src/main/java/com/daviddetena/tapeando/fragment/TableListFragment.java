package com.daviddetena.tapeando.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daviddetena.tapeando.R;
import com.daviddetena.tapeando.model.Table;
import com.daviddetena.tapeando.model.Tables;

import java.util.List;

public class TableListFragment extends Fragment {

    private RecyclerView mTableRecyclerView;
    private TableAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the view with the layout that contains the list of tables
        View view = inflater.inflate(R.layout.fragment_table_list, container, false);

        // Set RecyclerView and Layout Manager
        mTableRecyclerView = (RecyclerView) view.findViewById(R.id.table_recycler_view);
        mTableRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        Tables tableList = Tables.getInstance(getActivity());
        List<Table> tables = tableList.getTables();

        // Init and set our adapter to the RecyclerView, with the model to display (tables)
        mAdapter = new TableAdapter(tables);
        mTableRecyclerView.setAdapter(mAdapter);
    }


    /**
         * Inner class to hold the View for each row in the list of tables. Now the itemView is a
         * view inflated with the  layout
         */
        private class TableHolder extends RecyclerView.ViewHolder{

            public TextView mTitleTextView;

            public TableHolder(View itemView){
                super(itemView);

                mTitleTextView = (TextView)itemView;
            }
        }

        /**
         * The RecyclerView will communicate with this adapter when a ViewHolder needs to be created
         * or connected with a Table object.
         */
        private class TableAdapter extends RecyclerView.Adapter<TableHolder>{

            // List of tables to display
            private List<Table> mTables;

            /**
             * The Adapter is initialized with the list of tables
             * @param tables
             */
            public TableAdapter(List<Table> tables){
                mTables = tables;
            }

            /**
             * Called by the RecyclerView when it needs a new view to display an item.
             * Create a View (Android list item type with a single TextView) and wrap it in a ViewHolder.
             * @param parent
             * @param viewType
             * @return
             */
            @Override
            public TableHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View view = layoutInflater
                                .inflate(android.R.layout.simple_list_item_1, parent, false);
                return new TableHolder(view);
            }

            /**
             * Bind a ViewHolder's View with the Table model
             * @param holder
             * @param position
             */
            @Override
            public void onBindViewHolder(TableHolder holder, int position) {
                Table table = mTables.get(position);
                holder.mTitleTextView.setText(table.toString());
            }

            @Override
            public int getItemCount() {
                return mTables.size();
            }
        }
}
