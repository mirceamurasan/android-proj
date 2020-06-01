package android.bignerdranch.com.shopping;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static androidx.recyclerview.widget.RecyclerView.*;


public class ListFragment extends Fragment implements Observer {

    private OnFragmentInteractionListener mListener;

    private static ItemsDB itemsDB;

    private Button deleteButton, backButton;
    private RecyclerView recycleList;
    private ItemAdapter mAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_list, container, false);
        deleteButton = v.findViewById(R.id.delete_button);
        backButton = v.findViewById(R.id.back_button);

        itemsDB= ItemsDB.get(getActivity());
        itemsDB.addObserver(this);

        recycleList = v.findViewById(R.id.shopping_recycler_view);   // I HAVE TO CREATE THIS item_recycler_view probably in fragment_list

//        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleList.setLayoutManager(new LinearLayoutManager(getActivity()));


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getActivity().getIntent();
                itemsDB.deleteLastItem();
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShoppingActivity.class);
                startActivity(intent);
            }
        });

//        ItemsDB itemsDB = ItemsDB.get(getActivity());

        updateUI();
        return v;

    }
    private class ShoppingHolder extends ViewHolder {

        private TextView mTitleTextView;
        private TextView mDateTextView;
//        private Item mItem;


        public ShoppingHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.list_item_shopping, parent, false)); // create this list_item_crime; check book
//            super(inflater.inflate(R.layout.list_item_crime, parent, false));

            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
        }

        public void bind(Item item) {
//            mItem = item;
            mTitleTextView.setText(item.getWhat());
            mDateTextView.setText(item.getWhere());

        }

    }

    private class ItemAdapter extends RecyclerView.Adapter<ShoppingHolder> {
        private List<Item> mItems;

        public ItemAdapter(List<Item> crimes) {
            mItems = crimes;
        }


        @NonNull
        @Override
        public ShoppingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ShoppingHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ShoppingHolder holder, int position) {
//            Crime crime = mCrimes.get(position);
            Item item= mItems.get(position);
            holder.bind(item);
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        public void setItems(List<Item> items) {
            mItems = items;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void update(Observable observable, Object o) {
//        listThings.setText("Shopping List"+itemsDB.listItems());
        updateUI();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void updateUI() {
        // suggested in the course
//        mItemAdapter.notifyDataSetChanged();

//        ItemsDB itemsDB = ItemsDB.get(getActivity());




//        List<Item> items = itemsDB.getItemsDB();
        List<Item> items = itemsDB.getItems();



        //        mAdapter = new ItemAdapter(items);
//        recycleList.setAdapter(mAdapter);

        if (mAdapter == null) {
            mAdapter = new ItemAdapter(items);
            recycleList.setAdapter(mAdapter);
        } else {
            mAdapter.setItems(items);
            mAdapter.notifyDataSetChanged();
        }

    }

}
