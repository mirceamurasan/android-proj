package android.bignerdranch.com.shopping;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

public class ShopDialog extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

//        Spinner spinner = findViewById(R.id.spinner1);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View mView = getLayoutInflater().inflate(R.layout.dialog_spinner, null);
//
//        final Spinner mSpinner = (Spinner) mView.findViewById(R.id.my_spinner);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.numbers));
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mSpinner.setAdapter(adapter);


        builder.setTitle("Select shop")
            .setMessage("Message")
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
//                    if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("")){
////                        Toast.makeText()
//                    }

                }
            });

//        builder.setView(mView);



        return builder.create();
    }
}
