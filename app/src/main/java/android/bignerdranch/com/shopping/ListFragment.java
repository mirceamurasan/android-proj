package android.bignerdranch.com.shopping;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ListFragment extends Fragment {
//    public class UIFragment extends Fragment implements Observer {

    private OnFragmentInteractionListener mListener;

    private static ItemsDB itemsDB;
    private TextView listItems;
    private Button deleteButton, backButton;

    public ListFragment() {
    }

//    public void update() {
//        listThings.setText("Shopping List"+itemsDB.listItems());
//    }


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
        listItems = v.findViewById(R.id.list_textview_id);
        if (!itemsDB.listItems().isEmpty()) {
            listItems.setText("Shopping List:" + itemsDB.listItems());

        }

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

//        return inflater.inflate(R.layout.fragment_list, container, false);
        return v;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
