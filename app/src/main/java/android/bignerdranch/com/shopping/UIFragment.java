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


public class UIFragment extends Fragment {
    private Button newButton, listItems, deleteItem;
    private TextView whatField, whereField;
    private static ItemsDB itemsDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemsDB= ItemsDB.get(getActivity());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_ui, container, false);

        itemsDB = itemsDB.get(getActivity());

        whatField= v.findViewById(R.id.input_what);
        whereField= v.findViewById(R.id.input_where);
        newButton= v.findViewById(R.id.button_add_new);



        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whatString = whatField.getText().toString();
                String whereString = whereField.getText().toString();
                if (!whatString.isEmpty() && !whereString.isEmpty()) {
                    Item newItem = new Item(whatField.getText().toString(), whereField.getText().toString());
                    itemsDB.addItem(newItem);
                    Intent intent = new Intent(getActivity(), ListActivity.class);
                    startActivity(intent);
                    whatField.setText("");
                    whereField.setText("");
                }

            }
        });

        return v;
    }

    private OnFragmentInteractionListener mListener;

    public UIFragment() {
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
