package android.bignerdranch.com.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ShoppingActivity extends AppCompatActivity {

    // GUI variables
    private Button listItems;
    private TextView items;
    // Model: Database of items
    private ItemsDB itemsDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemsDB= new ItemsDB();
        itemsDB.fillItemsDB();
        final TextView items= (TextView) findViewById(R.id.textview_id);
        Button listItems = (Button) findViewById(R.id.button_list_all);
        Button newButton = (Button) findViewById(R.id.button_add_new);
        final EditText whatField = (EditText) findViewById(R.id.input_what);
        final EditText whereField = (EditText) findViewById(R.id.input_where);
        listItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.setBackgroundColor(Color.parseColor("#FFFFFF"));
                items.setText("Shopping List:" + itemsDB.listItems());
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
                }

            }
        });
    }
}
