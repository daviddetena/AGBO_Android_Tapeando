package com.daviddetena.tapeando.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daviddetena.tapeando.R;
import com.daviddetena.tapeando.activity.TableActivity;
import com.daviddetena.tapeando.activity.TablePagerActivity;
import com.daviddetena.tapeando.model.Table;
import com.daviddetena.tapeando.model.Tables;

import org.w3c.dom.Text;

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

    /**
     * In general, onResume() is the safest place to update a fragment's view
     */
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        Tables tableList = Tables.getInstance(getActivity());
        List<Table> tables = tableList.getTables();

        // Init and set our adapter to the RecyclerView, with the model to display (tables)
        if(mAdapter == null){
            // No adapter yet
            mAdapter = new TableAdapter(tables);
            mTableRecyclerView.setAdapter(mAdapter);
        }
        else{
            // Adapter already existed. Notify it to refetch data
            // TODO: adapt to refresh only the item updated
            mAdapter.notifyDataSetChanged();
        }
    }


    /**
         * Inner class to hold the View for each row in the list of tables. Now the itemView is a
         * view inflated with the  layout
         */
        private class TableHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private Table mTable;

            private ImageView mTableIconImageView;
            private TextView mTitleTextView;
            private TextView mCourseCounterTextView;
            private TextView mBillTextView;

            /**
             * Get references for UI widgets
             * @param itemView
             */
            public TableHolder(View itemView){
                super(itemView);

                // Self-assigned as the listener for click events on items
                itemView.setOnClickListener(this);

                // Reference for widgets
                mTableIconImageView = (ImageView)itemView.findViewById(R.id.list_item_table_icon_image_view);
                mTitleTextView = (TextView)itemView.findViewById(R.id.list_item_table_name_text_view);
                mCourseCounterTextView = (TextView)itemView.findViewById(R.id.list_item_table_course_number_text_view);
                mBillTextView = (TextView)itemView.findViewById(R.id.list_item_table_bill_text_view);
            }

            /**
             * Set up UI widgets
             * @param table
             */
            public void bindTable(Table table){
                mTable = table;

                // Wire up widgets
                mTableIconImageView.setImageResource(R.drawable.icon_table);
                mTitleTextView.setText(mTable.toString());
                //mCourseCounterTextView.setText(String.format("%d platos", mTable.getCourses().size()));
                mCourseCounterTextView.setText(mTable.getNotes());
                mBillTextView.setText(String.format("%.2f €", mTable.getBill()));
            }

            /**
             * Response when user taps an item in the list => Display TablePagerActivity via intent
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent intent = TablePagerActivity.newIntent(getActivity(), mTable.getTableNumber());
                startActivity(intent);
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
                                .inflate(R.layout.list_item_table, parent, false);
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

                // Call to the method to set up the UI
                holder.bindTable(table);
            }

            @Override
            public int getItemCount() {
                return mTables.size();
            }
        }
}
