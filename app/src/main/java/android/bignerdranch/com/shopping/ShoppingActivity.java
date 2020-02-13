package android.bignerdranch.com.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ShoppingActivity extends AppCompatActivity {

    private static ItemsDB itemsDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemsDB = ItemsDB.get(this);
        Button listItems = (Button) findViewById(R.id.button_list_all);
        Button newButton = (Button) findViewById(R.id.button_add_new);
        final EditText whatField = (EditText) findViewById(R.id.input_what);
        final EditText whereField = (EditText) findViewById(R.id.input_where);


        listItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShoppingActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whatString = whatField.getText().toString();
                String whereString = whereField.getText().toString();
                if (!whatString.isEmpty() && !whereString.isEmpty()) {
                    Item newItem = new Item(whatField.getText().toString(), whereField.getText().toString());
                    itemsDB.addItem(newItem);
                    Intent intent = new Intent(ShoppingActivity.this, ListActivity.class);
                    startActivity(intent);
//                    hideKeyboardFrom(ShoppingActivity.this);
                    whatField.setText("");
                    whereField.setText("");
                }

            }
        });
    }

    public static void hideKeyboardFrom(Activity activity) {
        InputMethodManager imm=
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
