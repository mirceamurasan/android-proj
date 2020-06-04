package android.bignerdranch.com.shopping;

import android.app.AlertDialog;
import android.bignerdranch.com.shopping.database.ItemDbSchema;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static androidx.recyclerview.widget.RecyclerView.*;


public class ListFragment extends Fragment implements Observer {

    private OnFragmentInteractionListener mListener;

    private static ItemsDB itemsDB;

    private Button popupButton, backButton, randomMealButton;
    private RecyclerView recycleList;
    private ItemAdapter mAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void openDialog() {
        FragmentManager manager = getFragmentManager();
        ShopDialog dialog = new ShopDialog();
        dialog.show(manager, "dialog string");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_list, container, false);
//        deleteButton = v.findViewById(R.id.delete_button);
        backButton = v.findViewById(R.id.back_button);
        popupButton = v.findViewById(R.id.select_shop_button);
        randomMealButton = v.findViewById(R.id.go_to_random_meal);

        popupButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                openDialog();


                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.dialog_spinner, null);

                final Spinner mSpinner = (Spinner) mView.findViewById(R.id.my_spinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.numbers));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter);



                mBuilder.setTitle("Select shop")
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                updateUI(itemsDB.getItemsByShop(mSpinner.getSelectedItem().toString()));

//                                itemsDB.queryItems(
//                                        ItemDbSchema.ItemsTable.Cols.WHERE_TO + " = ?",
//                                        new String[] { "Netto" }
//                                );
//                    if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("")){
////                        Toast.makeText()
//                    }

                            }
                        });

                mBuilder.setView(mView);

                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }
        });

                itemsDB= ItemsDB.get(getActivity());
        itemsDB.addObserver(this);

        recycleList = v.findViewById(R.id.shopping_recycler_view);   // I HAVE TO CREATE THIS item_recycler_view probably in fragment_list

//        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleList.setLayoutManager(new LinearLayoutManager(getActivity()));


//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = getActivity().getIntent();
//                itemsDB.deleteLastItem();
//                startActivity(intent);
//            }
//        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShoppingActivity.class);
                startActivity(intent);
            }
        });

        randomMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RandomMealActivity.class);
                startActivity(intent);
            }
        });

//        ItemsDB itemsDB = ItemsDB.get(getActivity());

        updateUI(null);
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
        updateUI(null);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void updateUI(List<Item> mItems) {
        // suggested in the course
//        mItemAdapter.notifyDataSetChanged();

//        ItemsDB itemsDB = ItemsDB.get(getActivity());



        List<Item> items = null;
//        List<Item> items = itemsDB.getItemsDB();
        if (mItems == null ) {
            items = itemsDB.getItems();
        } else {
            items = mItems;
        }



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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        menu.clear();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            menu.clear();
            super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.fragment_shopping, menu);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.shopping_new:
                Intent intent = new Intent(getActivity(), ShoppingActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
