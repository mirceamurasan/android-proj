package android.bignerdranch.com.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {

    private static ItemsDB itemsDB;
    private TextView listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Button deleteButton = (Button) findViewById(R.id.delete_button);
        Button backButton = (Button) findViewById(R.id.back_button);

        itemsDB= ItemsDB.get(this);
        listItems= (TextView) findViewById(R.id.list_textview_id);
        if (!itemsDB.listItems().isEmpty()) {
            listItems.setText("Shopping List:" + itemsDB.listItems());
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemsDB.deleteLastItem();
                finish();
                startActivity(getIntent());
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, ShoppingActivity.class);
                startActivity(intent);
            }
        });

    }


}
